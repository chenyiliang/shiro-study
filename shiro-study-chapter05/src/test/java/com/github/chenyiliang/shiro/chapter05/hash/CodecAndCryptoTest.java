package com.github.chenyiliang.shiro.chapter05.hash;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

public class CodecAndCryptoTest {

	@Test
	public void testBase64() throws UnsupportedEncodingException {
		String str = "你好";
		String base64Encoded = Base64.encodeToString(str.getBytes("UTF-8"));
		String str2 = Base64.decodeToString(base64Encoded);
		Assert.assertEquals(str, str2);
	}

	@Test
	public void testHex() throws UnsupportedEncodingException {
		String str = "你好";
		String hexEncoded = Hex.encodeToString(str.getBytes("UTF-8"));
		String str2 = new String(Hex.decode(hexEncoded.getBytes("UTF-8")));
		Assert.assertEquals(str, str2);
	}

	@Test
	public void testCodecSupport() {
		String str = "你好";
		byte[] bytes = CodecSupport.toBytes(str, "UTF-8");
		String str2 = CodecSupport.toString(bytes, "UTF-8");
		Assert.assertEquals(str, str2);
	}

	@Test
	public void testRandom() throws UnsupportedEncodingException {
		// 生成随机数
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes("UTF-8"));
		System.out.println(randomNumberGenerator.nextBytes().toHex());
	}

	@Test
	public void testMd5() {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();
		System.out.println(md5);
	}

	@Test
	public void testSha1() {
		String str = "hello";
		String salt = "123";
		String sha1 = new Sha1Hash(str, salt).toString();
		System.out.println(sha1);
	}

	@Test
	public void testSha256() {
		String str = "hello";
		String salt = "123";
		String sha256 = new Sha256Hash(str, salt).toString();
		System.out.println(sha256);
	}

	@Test
	public void testSha384() {
		String str = "hello";
		String salt = "123";
		String sha384 = new Sha384Hash(str, salt).toString();
		System.out.println(sha384);
	}

	@Test
	public void testSha512() {
		String str = "hello";
		String salt = "123";
		String sha512 = new Sha512Hash(str, salt).toString();
		System.out.println(sha512);
	}

	@Test
	public void testSimpleHash() {
		String str = "hello";
		String salt = "123";
		// MessageDigest
		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
		System.out.println(simpleHash);
	}

	@Test
	public void testHashService() {
		DefaultHashService hashService = new DefaultHashService();// 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123"));// 私盐，默认无
		hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(1);// 生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2)
				.build();
		String hex = hashService.computeHash(request).toHex();
		System.out.println(hex);
	}

	@Test
	public void testAesCipherService() throws UnsupportedEncodingException {
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128);// 设置key长度

		// 生成key
		Key key = aesCipherService.generateNewKey();

		String text = "你好";

		// 加密
		String encrptText = aesCipherService.encrypt(text.getBytes("UTF-8"), key.getEncoded()).toHex();
		System.out.println("EncrptText:" + encrptText);
		// 解密
		String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes(),
				"UTF-8");

		Assert.assertEquals(text, text2);
	}

	@Test
	public void testBlowfishCipherService() throws UnsupportedEncodingException {
		BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
		blowfishCipherService.setKeySize(128);

		// 生成key
		Key key = blowfishCipherService.generateNewKey();

		String text = "你好";

		// 加密
		String encrptText = blowfishCipherService.encrypt(text.getBytes("UTF-8"), key.getEncoded()).toHex();
		System.out.println("EncrptText:" + encrptText);
		// 解密
		String text2 = new String(blowfishCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes(),
				"UTF-8");

		Assert.assertEquals(text, text2);
	}

	@Test
	public void testDefaultBlockCipherService() throws Exception {

		// 对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
		DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
		cipherService.setKeySize(128);

		// 生成key
		Key key = cipherService.generateNewKey();

		String text = "你好";

		// 加密
		String encrptText = cipherService.encrypt(text.getBytes("UTF-8"), key.getEncoded()).toHex();
		// 解密
		String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes(), "UTF-8");

		Assert.assertEquals(text, text2);
	}

	// 加密/解密相关知识可参考snowolf的博客 http://snowolf.iteye.com/category/68576

}
