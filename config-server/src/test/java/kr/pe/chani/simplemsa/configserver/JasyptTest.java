package kr.pe.chani.simplemsa.configserver;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

	@Value("${jasypt.encryptor.password}")
	private String encryptKey;

	@Test
    public void encryptDecryptTest() {
		String password = "git_password"; // Git에 접속하는 패스워드

		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("jasypt_key"); // Jasypt를 이용하여 암호화하는 키 (프로퍼티의 jasypt.encryptor.password)
		jasypt.setAlgorithm("PBEWithMD5AndDES"); // Jasypt를 이용한 암호화 알고리즘

		String encryptedText = jasypt.encrypt(password);
		System.out.println("enc : " + encryptedText);
		String decryptedText = jasypt.decrypt(encryptedText);
		System.out.println("dec : " + decryptedText);
    }
}