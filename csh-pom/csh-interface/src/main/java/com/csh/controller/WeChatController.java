package com.csh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.utils.wechat.WeixinUtil;



@Controller("weChatController")
@RequestMapping("/wechat")
public class WeChatController extends MobileBaseController {

  @Resource(name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;

  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;



  /**
   * 微信支付回调接口
   * 
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/pay_notify", method = RequestMethod.POST)
  public String pay_notify(HttpServletRequest request) throws Exception {
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

          List<Filter> filters = new ArrayList<Filter>();
          Filter filter = new Filter("recordNo", Operator.eq, out_trade_no);
          filters.add(filter);
          List<CarServiceRecord> records = carServiceRecordService.findList(null, filters, null);
          if (records != null && records.size() == 1) {
            CarServiceRecord carServiceRecord = records.get(0);
            carServiceRecord.setChargeStatus(ChargeStatus.PAID);
            carServiceRecordService.update(carServiceRecord);
            if (LogUtil.isDebugEnabled(CarServiceController.class)) {
              LogUtil
                  .debug(
                      CarServiceController.class,
                      "Update",
                      "Update Car Service pay status. recordNo: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
                      carServiceRecord.getRecordNo(), carServiceRecord.getTenantName(),
                      carServiceRecord.getCarService().getServiceName(),
                      carServiceRecord.getPrice(), carServiceRecord.getPaymentType(),
                      carServiceRecord.getChargeStatus());
            }

          }
        } else {
          // 交易失败情况记录
        }
      }
      // 返回处理结果xml
      xmlReturn = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";
    } else {
      // 返回处理结果xml
      xmlReturn = "<xml><return_code>FAIL</return_code><return_msg>签名错误</return_msg></xml>";
    }

    return xmlReturn;
  }

}
