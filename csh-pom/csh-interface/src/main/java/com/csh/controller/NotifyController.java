package com.csh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderRelation;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.service.AdvanceDepositsService;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.service.OrderRelationService;
import com.csh.service.OrderService;
import com.csh.service.WalletService;
import com.csh.utils.ToolsUtils;
import com.csh.utils.alipay.util.AlipayNotify;
import com.csh.utils.wechat.WeixinUtil;



@Controller("notifyController")
@RequestMapping("/payNotify")
public class NotifyController extends MobileBaseController {

  @Resource(name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;

  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "walletServiceImpl")
  private WalletService walletService;

  @Resource(name = "advanceDepositsServiceImpl")
  private AdvanceDepositsService advanceDepositsService;

  @Resource(name = "orderServiceImpl")
  private OrderService orderService;

  @Resource(name = "orderRelationServiceImpl")
  private OrderRelationService orderRelationService;



  /**
   * 微信支付回调接口
   * 
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/notify_wechat", method = RequestMethod.POST)
  public @ResponseBody String notify_wechat(HttpServletRequest request) throws Exception {
    // 获取支付通知xml数据
    ServletInputStream inputStream = request.getInputStream();
    InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
    BufferedReader bufferReader = new BufferedReader(reader);
    String xml = null;
    StringBuffer buffer = new StringBuffer();
    while ((xml = bufferReader.readLine()) != null) {
      buffer.append(xml);
    }
    bufferReader.close();
    reader.close();
    inputStream.close();
    inputStream = null;
    // 解析xml数据
    Map<String, Object> xmlMap = new HashMap<String, Object>();
    // 解析XML数据
    Document doc = DocumentHelper.parseText(buffer.toString());
    Element root = doc.getRootElement();
    Iterator it = root.elementIterator();
    while (it.hasNext()) {
      Element temp = (Element) it.next();
      xmlMap.put(temp.getName(), temp.getStringValue());
    }
    if (LogUtil.isDebugEnabled(NotifyController.class)) {
      LogUtil.debug(NotifyController.class, "notify_wechat",
          "wechat pay notify callback method. response: %s", xmlMap);
    }
    String xmlReturn = "";
    if ("SUCCESS".equals(xmlMap.get("return_code"))) {
      // 验证签名
      String sign = xmlMap.get("sign") + "";
      xmlMap.put("sign", "");
      if (sign.equals(WeixinUtil.getSign(xmlMap))) {
        // 查询是否处理过
        // 处理回调结果
        if ("SUCCESS".equals(xmlMap.get("result_code"))) {
          // 系统订单编号
          String out_trade_no = xmlMap.get("out_trade_no").toString();
          // 支付的金额
          String total_fee = xmlMap.get("total_fee").toString();
          // 货币种类
          String fee_type = xmlMap.get("fee_type").toString();
          // 微信支付订单号
          String transaction_id = xmlMap.get("transaction_id").toString();

          String payment = "wx";


          String recordNo = out_trade_no.split("_")[0];
          BigDecimal amount = new BigDecimal(total_fee).divide(new BigDecimal(100));
          // 普通充值
          if (out_trade_no.startsWith("CI")) {
            String userId = out_trade_no.split("_")[1];
            if (LogUtil.isDebugEnabled(NotifyController.class)) {
              LogUtil.debug(NotifyController.class, "notify_wechat",
                  "User charge in call back for common charge. UserId: %s, ChargeAmount: %s,",
                  userId, amount);
            }
            walletService.updateWallet(new Long(userId), amount,
                ToolsUtils.generateRecordNoByParam(recordNo));
          }
          // 购买设备充值
          else if (out_trade_no.startsWith("PD")) {
            String userId = out_trade_no.split("_")[1];

            if (LogUtil.isDebugEnabled(NotifyController.class)) {
              LogUtil.debug(NotifyController.class, "notify_wechat",
                  "User charge in call back for purchase device. UserId: %s, ChargeAmount: %s,",
                  userId, amount);
            }
            advanceDepositsService.updateAdvanceDeposit(new Long(userId), amount,
                out_trade_no.split("_")[2], PaymentType.WECHAT,
                ToolsUtils.generateRecordNoByParam(recordNo));
          }
          // 购买商品
          else if (out_trade_no.startsWith("E")) {
            List<Filter> filters = new ArrayList<Filter>();
            Filter filter = new Filter("sn", Operator.eq, recordNo.substring(1));
            filters.add(filter);
            List<Order> records = orderService.findList(null, filters, null);
            if (!CollectionUtils.isEmpty(records) && records.size() == 1) {
              Order order = records.get(0);
              List<Filter> relationFilters = new ArrayList<Filter>();
              Filter rfilter = new Filter("mainOrderId", Operator.eq, order.getId());
              relationFilters.add(rfilter);
              List<OrderRelation> orderRelations =
                  orderRelationService.findList(null, filters, null);
              if (!CollectionUtils.isEmpty(orderRelations)) {// 有关联订单,一次购买涉及多个租户订单
                for (OrderRelation orderRelation : orderRelations) {
                  Order rOrder = orderService.find(orderRelation.getOrderId());
                  rOrder.setPaymentStatus(PaymentStatus.paid);
                  rOrder.setAmountPaid(rOrder.getAmount());
                  if (LogUtil.isDebugEnabled(NotifyController.class)) {
                    LogUtil
                        .debug(
                            NotifyController.class,
                            "notify_wechat",
                            "User purchase product call back for product order--multi tenant.UserName: %s, TenantId: %s, orderId: %s, amount: %s, paymentType: %s, paymentStatus: %s, sn: %s",
                            rOrder.getEndUser().getUserName(), rOrder.getTenantID(),
                            rOrder.getId(), rOrder.getAmount(), rOrder.getPaymentType(),
                            rOrder.getPaymentStatus());
                  }
                  orderService.updatePayStatus(rOrder);
                }
              } else {
                order.setPaymentStatus(PaymentStatus.paid);
                order.setAmountPaid(order.getAmount());
                if (LogUtil.isDebugEnabled(NotifyController.class)) {
                  LogUtil
                      .debug(
                          NotifyController.class,
                          "notify_wechat",
                          "User purchase product call back for product order--single tenant.UserName: %s, TenantId: %s, orderId: %s, amount: %s, paymentType: %s, paymentStatus: %s, sn: %s",
                          order.getEndUser().getUserName(), order.getTenantID(), order.getId(),
                          order.getAmount(), order.getPaymentType(), order.getPaymentStatus());
                }
                orderService.updatePayStatus(order);
              }

            }
          }
          // 购买服务
          else {
            List<Filter> filters = new ArrayList<Filter>();
            Filter filter = new Filter("recordNo", Operator.eq, recordNo);
            filters.add(filter);
            List<CarServiceRecord> records = carServiceRecordService.findList(null, filters, null);
            if (records != null && records.size() == 1) {
              CarServiceRecord carServiceRecord = records.get(0);
              carServiceRecord.setChargeStatus(ChargeStatus.PAID);
              carServiceRecord.setPaymentDate(new Date());
              if (LogUtil.isDebugEnabled(CarServiceController.class)) {
                LogUtil
                    .debug(
                        CarServiceController.class,
                        "notify_wechat",
                        "call back method for update Car Service pay status. recordNo: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
                        carServiceRecord.getRecordNo(), carServiceRecord.getTenantName(),
                        carServiceRecord.getCarService().getServiceName(), amount,
                        carServiceRecord.getPaymentType(), carServiceRecord.getChargeStatus());
              }
              carServiceRecordService.updatePayStatus(carServiceRecord);
            }
          }

        } else {
          if (LogUtil.isDebugEnabled(CarServiceController.class)) {
            LogUtil.debug(CarServiceController.class, "pay_notify",
                "WeChat pay fail. recordNo: %s", xmlMap.get("out_trade_no").toString());
          }
        }
      }
      // 返回处理结果xml
      xmlReturn =
          "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    } else {
      // 返回处理结果xml
      xmlReturn = "<xml><return_code>FAIL</return_code><return_msg>签名错误</return_msg></xml>";
    }

    return xmlReturn;
  }


  /**
   * 支付宝支付回调接口
   * 
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/notify_alipay", method = RequestMethod.POST)
  public @ResponseBody String notify_alipay(HttpServletRequest request) throws Exception {
    // 获取支付宝POST过来反馈信息
    Map<String, String> params = new HashMap<String, String>();
    Map requestParams = request.getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
      params.put(name, valueStr);
    }
    if (LogUtil.isDebugEnabled(NotifyController.class)) {
      LogUtil.debug(NotifyController.class, "notify_alipay",
          "Alipay pay notify callback method. response: %s", params);
    }

    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    // 商户订单号
    String out_trade_no =
        new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
    // 支付宝交易号
    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
    // 交易状态
    String trade_status =
        new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
    // 交易金额
    String total_fee =
        new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
    // 购买者id
    String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8");
    // 购买中邮箱
    String buyer_eamil =
        new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");
    if (AlipayNotify.verify(params)) {// 验证成功
      // ////////////////////////////////////////////////////////////////////////////////////////
      // 请在这里加上商户的业务逻辑程序代码
      // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
      if (trade_status.equals("TRADE_FINISHED")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        // 如果有做过处理，不执行商户的业务程序
        String payment = "alipay";
        alipayNotifySuccess(out_trade_no, total_fee, trade_status);
        // 注意：
        // 该种交易状态只在两种情况下出现
        // 1、开通了普通即时到账，买家付款成功后。
        // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
      } else if (trade_status.equals("TRADE_SUCCESS")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        // 如果有做过处理，不执行商户的业务程序
        String payment = "alipay";
        alipayNotifySuccess(out_trade_no, total_fee, trade_status);

        // 注意：
        // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
      }

      // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

      return "success"; // 请不要修改或删除

      // ////////////////////////////////////////////////////////////////////////////////////////
    } else {// 验证失败
      return "fail";
    }
  }

  private void alipayNotifySuccess(String out_trade_no, String total_fee, String trade_status) {
    String recordNo = out_trade_no.split("_")[0];
    BigDecimal amount = new BigDecimal(total_fee);
    // 普通充值
    if (out_trade_no.split("_").length == 3 && recordNo.equals("0")) {
      String userId = out_trade_no.split("_")[2];
      if (LogUtil.isDebugEnabled(NotifyController.class)) {
        LogUtil
            .debug(
                NotifyController.class,
                "notify_alipay",
                "User charge in call back for common charge.UserId: %s, ChargeAmount: %s, trade_status: %s",
                userId, amount, trade_status);
      }
      walletService.updateWallet(new Long(userId), amount,
          ToolsUtils.generateRecordNoByParam("CI" + out_trade_no.split("_")[1]));
    }
    // 购买设备充值
    else if (out_trade_no.split("_").length == 4 && recordNo.equals("1")) {
      String userId = out_trade_no.split("_")[2];
      if (LogUtil.isDebugEnabled(NotifyController.class)) {
        LogUtil
            .debug(
                NotifyController.class,
                "notify_alipay",
                "User charge in call back for purchase device. UserId: %s, ChargeAmount: %s, trade_status: %s",
                userId, amount, trade_status);
      }
      advanceDepositsService.updateAdvanceDeposit(new Long(userId), amount,
          out_trade_no.split("_")[3], PaymentType.ALIPAY,
          ToolsUtils.generateRecordNoByParam("PD" + out_trade_no.split("_")[1]));
    }
    // 购买商品
    else if (out_trade_no.split("_").length == 2 && recordNo.equals("2")) {
      String orderSn = out_trade_no.split("_")[1];
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("sn", Operator.eq, orderSn);
      filters.add(filter);
      List<Order> records = orderService.findList(null, filters, null);
      if (!CollectionUtils.isEmpty(records) && records.size() == 1) {
        Order order = records.get(0);
        List<Filter> relationFilters = new ArrayList<Filter>();
        Filter rfilter = new Filter("mainOrderId", Operator.eq, order.getId());
        relationFilters.add(rfilter);
        List<OrderRelation> orderRelations = orderRelationService.findList(null, filters, null);
        if (!CollectionUtils.isEmpty(orderRelations)) {// 有关联订单,一次购买涉及多个租户订单
          for (OrderRelation orderRelation : orderRelations) {
            Order rOrder = orderService.find(orderRelation.getOrderId());
            rOrder.setPaymentStatus(PaymentStatus.paid);
            rOrder.setAmountPaid(rOrder.getAmount());
            if (LogUtil.isDebugEnabled(NotifyController.class)) {
              LogUtil
                  .debug(
                      NotifyController.class,
                      "notify_alipay",
                      "User purchase product call back for product order--multi tenant.UserName: %s, TenantId: %s, orderId: %s, amount: %s, paymentType: %s, paymentStatus: %s, sn: %s",
                      rOrder.getEndUser().getUserName(), rOrder.getTenantID(), rOrder.getId(),
                      rOrder.getAmount(), rOrder.getPaymentType(), rOrder.getPaymentStatus());
            }
            orderService.updatePayStatus(rOrder);
          }
        } else {
          order.setPaymentStatus(PaymentStatus.paid);
          order.setAmountPaid(order.getAmount());
          if (LogUtil.isDebugEnabled(NotifyController.class)) {
            LogUtil
                .debug(
                    NotifyController.class,
                    "notify_alipay",
                    "User purchase product call back for product order--single tenant.UserName: %s, TenantId: %s, orderId: %s, amount: %s, paymentType: %s, paymentStatus: %s, sn: %s",
                    order.getEndUser().getUserName(), order.getTenantID(), order.getId(),
                    order.getAmount(), order.getPaymentType(), order.getPaymentStatus());
          }
          orderService.updatePayStatus(order);
        }

      }
    }
    // 购买服务
    else {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("recordNo", Operator.eq, recordNo);
      filters.add(filter);
      List<CarServiceRecord> records = carServiceRecordService.findList(null, filters, null);
      if (records != null && records.size() == 1) {
        CarServiceRecord carServiceRecord = records.get(0);
        carServiceRecord.setChargeStatus(ChargeStatus.PAID);
        carServiceRecord.setPaymentDate(new Date());
        if (LogUtil.isDebugEnabled(NotifyController.class)) {
          LogUtil
              .debug(
                  NotifyController.class,
                  "notify_alipay",
                  "call back method for update Car Service pay status. recordNo: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s,trade_status: %s",
                  carServiceRecord.getRecordNo(), carServiceRecord.getTenantName(),
                  carServiceRecord.getCarService().getServiceName(), amount,
                  carServiceRecord.getPaymentType(), carServiceRecord.getChargeStatus(),
                  trade_status);
        }
        carServiceRecordService.updatePayStatus(carServiceRecord);
      }
    }
  }
}
