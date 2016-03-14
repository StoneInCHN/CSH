package com.csh.utils;

import java.security.KeyPair;

import org.apache.commons.codec.digest.DigestUtils;

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
		KeyPair keyPair = KeyGenerator.generateKeys();
		System.out.println(RSAHelper.getKeyString(keyPair.getPublic()));
		System.out.println(RSAHelper.getKeyString(keyPair.getPrivate()));
		String pwd = KeyGenerator.encrypt("111111", RSAHelper.getPublicKey(serverPublicKey));
		System.out.println(pwd);
		System.out.println(KeyGenerator.decrypt(pwd, RSAHelper.getPrivateKey(serverPrivateKey)));
		System.out.println(DigestUtils.md5Hex("111111"));
	}

}
