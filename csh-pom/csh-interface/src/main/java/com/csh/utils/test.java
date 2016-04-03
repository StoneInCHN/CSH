package com.csh.utils;


public class test {

  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    // Setting setting = SettingUtils.get();
    // String serverPrivateKey = setting.getServerPrivateKey();
    // String serverPublicKey = setting.getServerPublicKey();
    // KeyPair keyPair = KeyGenerator.generateKeys();
    // System.out.println(RSAHelper.getKeyString(keyPair.getPublic()));
    // System.out.println(RSAHelper.getKeyString(keyPair.getPrivate()));
    // String pwd = KeyGenerator.encrypt("111111", RSAHelper.getPublicKey(serverPublicKey));
    // System.out.println(pwd);
    // System.out.println(KeyGenerator.decrypt(pwd, RSAHelper.getPrivateKey(serverPrivateKey)));
    // System.out.println(DigestUtils.md5Hex("111111"));
    // Date date1 = new Date();
    // Date date2 = new Date();
    // System.out.println(DateUtils.isSameDay(date1, date2));
    // String res = UcpaasUtil.SendCodeBySms("15902823856", "6598");
    // System.out.println(res);
    System.out.println(ToolsUtils.generateRecordNo("0001"));
  }

}
