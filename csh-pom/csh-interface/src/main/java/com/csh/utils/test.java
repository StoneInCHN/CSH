package com.csh.utils;

import com.csh.beans.Setting;
import com.csh.utils.KeyGenerator;
import com.csh.utils.RSAHelper;
import com.csh.utils.SettingUtils;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Setting setting = SettingUtils.get();
		String serverPrivateKey = setting.getServerPrivateKey();
		String serverPublicKey = setting.getServerPublicKey();
		String pwd = KeyGenerator.encrypt("111111", RSAHelper.getPublicKey(serverPublicKey));
		System.out.println(pwd);
	}

}
