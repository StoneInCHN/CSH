package com.csh.utils.alipay.config;

/* *
 * 类名：AlipayConfig功能：基础配置类详细：设置帐户有关信息及返回路径版本：3.3日期：2012-08-10说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 
 * 提示：如何获取安全校验码和合作身份者ID1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 * 2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 * 
 * 安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？解决方法：1、检查浏览器配置，不让浏览器做弹框屏蔽设置2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

  // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
  // 合作身份者ID，以2088开头由16位纯数字组成的字符串
  // public static String partner = "2088021552530780";
  // // 商户的私钥
  // public static String private_key =
  // "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL4Lx8cgCyPVKxjdr3IBch3JG+/AreSbjhLqN0pM4HlPEkQGmoihzptSVe+o1ywALgkul/1e5fUljqAGAbtyhmxk9dRs3QddKposOzStoX9ReTWmMJPtC5cNXbclg7QbYKrzw25sUkxGHMP9KInZV7qAnhwFg5TDmk87Lab5JrmPAgMBAAECgYBJ0uWuEmKBbuMo66Slkq4zp9W0UpK6RTrxWg5UTHy+YtrjlfUdsk1BxMAhMuMy8nbvlivwfpaxnf9DZlHx8NEKSY+vpj/jZyir1Nscf9Dya7ak5EgvBPBE/5KtJqpx7WD1qJjAoaumTh0ljy9B5G88RcLFNA2U27NjchCO9SjmIQJBAPn/g8omfRGHJoBJHXsIBYripnCld7J1V/Ekyjb8r4rQtH8bS/NK30CR1ToPD0gjcEDTgYkLkONqUUmJ9EWBwOcCQQDCm80BkFTt0TxjxqLSpyEJcsZdp7dHFVUDaHEci/WVVwldcaPdw9THkHybB3KvwzIVzuClq2QYvRptZ+qN6qUZAkA+sHYp0PD33j4nWS5NVbueEivOf4++bnJ5A9K5ay/RzXgVj5DCF3pYRLmFb5VTb5+Mgf0vknjorhZoLHHWpCztAkEAosXuEwDGCKSZGlet0lpKJmIxPoAUXtmIFOftWzjKegqoqhbLmpoUTtBfmtVxu6A7Bl9BjSM3i7N+eMFWzAQJAIfUnYgTQ8RKBZ0gkK98FuGcsYU6aPvKzyGaPeczKdsp4xZgEdlppCzwxn/Kd9kKRrL2PKkgBzQ5Vaygvu8dqNg==";
  //
  // // 支付宝的公钥，无需修改该值
  // public static String ali_public_key =
  // "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

  // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


  // 调试用，创建TXT日志文件夹路径
  public static String log_path = "D:\\";

  // 字符编码格式 目前支持 gbk 或 utf-8
  public static String input_charset = "utf-8";

  // 签名方式 不需修改
  public static String sign_type = "RSA";

}
