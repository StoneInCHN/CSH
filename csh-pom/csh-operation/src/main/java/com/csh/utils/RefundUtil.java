package com.csh.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;



import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Setting;
import com.csh.json.ResponseOne;
import com.csh.utils.wechat.MyX509TrustManager;
import com.tencent.common.MD5;


/**
 * 退款工具类，包括支付宝退款和微信退款
 *
 */
public class RefundUtil {

  public static Setting setting = SettingUtils.get();

  /**
   * 支付宝的配置参数
   */
  //支付宝服务URL
  public static final String alipay_serverUrl = setting.getAlipayServerUrl();
  //支付宝分配给开发者的应用ID
  public static final String alipay_appid = setting.getAlipayAppid();
  //商户的私钥
  public static final String alipay_privateKey = setting.getAlipayPrivateKey();
  //支付宝的公钥，无需修改该值
  public static final String alipay_publicKey = setting.getAlipayPublicKey();
  
  /**
   * 微信的配置参数
   */
  // 秘钥
  public static final String wechat_Key = setting.getWechatKey();
  // 微信分配的公众账号ID（企业号corpid即为此appId）
  public static final String wechat_appid = setting.getWechatAppid();
  // 商户ID
  public static final String wechat_mch_id = setting.getWechatMchId();
  // 微信申请退款接口 
  public static final String wechat_RefundUrl = setting.getWechatRefundUrl();


  /**
   * 支付宝申请退款 
   * 
   * @param out_trade_no 订单号
   * @param out_refund_no 退款单号
   * @param refund_amount 退款总金额
   * @return
   */
   public static ResponseOne<Map<String, Object>> alipayRefund(String out_trade_no, String out_refund_no,
       double refund_amount) {
        ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
     try {
         AlipayClient alipayClient = new DefaultAlipayClient
             (alipay_serverUrl, alipay_appid, alipay_privateKey, "json", "UTF-8", alipay_publicKey);
         
         AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
         JSONObject alipayRefundInfo = new JSONObject();
         alipayRefundInfo.append("out_trade_no", out_trade_no);//订单支付时传入的商户订单号,不能和 trade_no同时为空
         alipayRefundInfo.append("refund_amount", refund_amount);//需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
         alipayRefundInfo.append("refund_reason", "订单正常退款");//退款的原因说明
         alipayRefundInfo.append("out_request_no", out_refund_no);//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
         //alipayRefundInfo.append("trade_no", "");//支付宝交易号，和商户订单号不能同时为空
         request.setBizContent(alipayRefundInfo.toString());
         
         AlipayTradeRefundResponse alipayResponse = alipayClient.execute(request);
         if ("10000".equals(alipayResponse.getCode())) {
           response.setCode(CommonAttributes.SUCCESS);
         }else {
           response.setCode(CommonAttributes.FAIL_COMMON);
           response.setDesc(SpringUtils.getMessage("csh.refund.alipay.fail", 
               out_refund_no, alipayResponse.getSubMsg()));
         }
     } catch (Exception e) {
       e.printStackTrace();
     }
     return response;
   }



  /**
   * 微信申请退款 
   * 
   * @param out_trade_no 订单号
   * @param out_refund_no 退款单号
   * @param total_fee 订单总金额
   * @param refund_fee 退款总金额
   * @return
   * @throws DocumentException
   */
  public static ResponseOne<Map<String, Object>> wechatRefund(String out_trade_no, String out_refund_no,
      BigDecimal total_fee, BigDecimal refund_fee){

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    //随机字符串，不长于32位
    String nonce_str = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    //订单总金额，单位为分，只能为整数
    String total_fee_str = total_fee.multiply(new BigDecimal(100)).intValue() + "";
    //退款总金额，订单总金额，单位为分
    String refund_fee_str = refund_fee.multiply(new BigDecimal(100)).intValue() + "";
    //获取签名
    String sign = wechat_getSign(nonce_str, out_trade_no, 
        out_refund_no,total_fee_str, refund_fee_str);
    //获取 微信退款接口的xml参数
    String xmlParam = wechat_getXmlParam(nonce_str, out_trade_no, 
        out_refund_no,total_fee_str, refund_fee_str, sign);
    
    // 调微信退款接口
    String xmlRespons = httpsRequest(wechat_RefundUrl, "POST", xmlParam);
    // 解析返回xml
    Document dom;
    try {
      dom = DocumentHelper.parseText(xmlRespons);
      Element root = dom.getRootElement();
      String return_code = root.elementText("return_code");
      //当return_code为SUCCESS的时候将返回result_code等字段
      if (return_code.equals("SUCCESS") && 
             root.elementText("result_code").equals("SUCCESS")) {
          response.setCode(CommonAttributes.SUCCESS);
      } else {
          response.setCode(CommonAttributes.FAIL_COMMON);
          response.setDesc(SpringUtils.getMessage("csh.refund.wechat.fail", 
              out_refund_no, root.elementText("err_code_des")));
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    
    return response;
  }
  /**
   * 组装 微信退款接口的xml参数
   * @param nonce_str
   * @param out_trade_no
   * @param out_refund_no
   * @param total_fee_str
   * @param refund_fee_str
   * @param sign
   * @return
   */
  private static String wechat_getXmlParam(String nonce_str, String out_trade_no,
      String out_refund_no, String total_fee_str, String refund_fee_str, String sign) {
    StringBuffer xmlBuffer = new StringBuffer();
    xmlBuffer.append("<xml>");
    xmlBuffer.append("<appid>");xmlBuffer.append(wechat_appid);xmlBuffer.append("</appid>");
    xmlBuffer.append("<mch_id>");xmlBuffer.append(wechat_mch_id);xmlBuffer.append("</mch_id>");
    xmlBuffer.append("<nonce_str>");xmlBuffer.append(nonce_str);xmlBuffer.append("</nonce_str>");
    xmlBuffer.append("<sign>");xmlBuffer.append(sign);xmlBuffer.append("</sign>");
    xmlBuffer.append("<out_trade_no>");xmlBuffer.append(out_trade_no);xmlBuffer.append("</out_trade_no>");
    xmlBuffer.append("<out_refund_no>");xmlBuffer.append(out_refund_no);xmlBuffer.append("</out_refund_no>");
    xmlBuffer.append("<total_fee>");xmlBuffer.append(total_fee_str);xmlBuffer.append("</total_fee>");
    xmlBuffer.append("<refund_fee>");xmlBuffer.append(refund_fee_str);xmlBuffer.append("</refund_fee>");
    xmlBuffer.append("<op_user_id>");xmlBuffer.append(wechat_mch_id);xmlBuffer.append("</op_user_id>");
    xmlBuffer.append("</xml>");
    return xmlBuffer.toString();
  }
  /**
   * 获取微信退款接口 签名参数
   * 对参数按照key=value的格式，并按照参数名ASCII字典序排序,最后拼接API密钥,MD5加密后得到签名
   * @param params
   * @return
   */
  private static String wechat_getSign(String nonce_str, String out_trade_no, 
      String out_refund_no, String total_fee_str, String refund_fee_str) {
    
    SortedMap<String, String> packageParams = new TreeMap<String, String>();
    packageParams.put("appid", wechat_appid);//微信分配的公众账号ID（企业号corpid即为此appId）
    packageParams.put("mch_id", wechat_mch_id);//微信支付分配的商户号
    packageParams.put("nonce_str", nonce_str);//随机字符串，不长于32位
    packageParams.put("out_trade_no", out_trade_no);//商户侧传给微信的订单号
    packageParams.put("out_refund_no", out_refund_no);//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
    packageParams.put("total_fee", total_fee_str);//订单总金额，单位为分，只能为整数
    packageParams.put("refund_fee", refund_fee_str);//退款总金额，订单总金额，单位为分
    packageParams.put("op_user_id", wechat_mch_id);//操作员帐号, 默认为商户号    
    
    return getSign(packageParams);
  }
  /**
   * 公共方法，获取微信接口的参数签名
   * @param packageParams
   * @return
   */
  public static String getSign(Map<String, String> packageParams){
    StringBuffer signParam = new StringBuffer();
    Set es = packageParams.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        String k = (String) entry.getKey();
        String v = (String) entry.getValue();
        if (null != v && !"".equals(v) && !"sign".equals(k)
                && !"key".equals(k)) {
          signParam.append(k + "=" + v + "&");
        }
    }
    signParam.append("key=" + wechat_Key);
    System.out.println(signParam.toString());
    
    String sign = MD5.MD5Encode(signParam.toString()).toUpperCase();//获取签名
    System.out.println(sign);
    return sign;
  }
  /**
   * 发送https请求
   * 
   * @param requestUrl //提交的URL
   * @param requestMethod //提交方式
   * @param outputStr //ID
   * @return
   */
  public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
    String obj = null;
    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
    TrustManager[] tm = {new MyX509TrustManager()};
    // 安全套接字的上下文
    SSLContext sslContext;
    try {
      sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, tm, new java.security.SecureRandom());
      // 从上述SSLContext对象中得到SSLSocketFactory对象
      SSLSocketFactory ssf = sslContext.getSocketFactory();
      // 建立连接
      URL url = new URL(requestUrl);
      // 打开连接
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.setSSLSocketFactory(ssf);
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setUseCaches(false);
      // 设置请求方式
      conn.setRequestMethod(requestMethod);

      if (null != outputStr) {
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(outputStr.getBytes("UTF-8"));
        outputStream.close();
      }
      // 从输入流中获取返回的内容
      InputStream inputStream = conn.getInputStream();
      InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader bufferReader = new BufferedReader(reader);
      String str = null;
      StringBuffer buffer = new StringBuffer();
      while ((str = bufferReader.readLine()) != null) {
        buffer.append(str);
      }
      bufferReader.close();
      reader.close();
      inputStream.close();
      inputStream = null;

      conn.disconnect();
      System.out.println(buffer.toString());
      obj = buffer.toString();
      // jsonObject = JSONObject.fromObject(buffer.toString());
    } catch (KeyManagementException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * url编码
   * */
  public static String urlEncodeUTF8(String source) {
    String str = null;
    try {
      str = URLEncoder.encode(source, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return str;
  }
}
