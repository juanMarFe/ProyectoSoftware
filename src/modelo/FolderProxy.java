package modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class FolderProxy implements IFolder {

	Folder folder;
	Singleton s = Singleton.crearInstaSingleton();
	UsuarioFactory uf = s.getUsuarioFactory();
	HashMap map = uf.getList();
	private String password;
	private String usuario;

	public FolderProxy(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	@Override
	public String performOperation() {
		boolean b = false;
		String usuario = null;
		String contrasena=null;
		for (Object key : map.keySet()) {
			if ((uf.getUsuario(key.toString()).checkLogin(this.usuario, this.password))) {
				usuario = uf.getUsuario(key.toString()).getLogin();
				contrasena=uf.getUsuario(key.toString()).getPassword();
				b = true;
				break;
			}
		}
		if (b) {
			folder = new Folder();
			folder.performOperation();
			String p=generateMyKey();
			return p+ generateEncryption(usuario+","+contrasena, p);
		} else {
			return null;
		}
	}

	private String generateEncryption(String text, String myKey) {
		byte[] key;
		SecretKeySpec secretKey = null;
		MessageDigest sha = null;
		try {
			/////////////////////////////////////////////////////////////////
			try {
				key = myKey.getBytes("UTF-8");
				sha = MessageDigest.getInstance("SHA-1");
				key = sha.digest(key);
				key = Arrays.copyOf(key, 16);
				secretKey = new SecretKeySpec(key, "AES");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			/////////////////////////////////////////////////////////////////////
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
			return null;
		}
	}
	
	private String generateMyKey() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}

}


