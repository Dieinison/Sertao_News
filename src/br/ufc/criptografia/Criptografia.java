package br.ufc.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	public Criptografia() {}
	
	public String criptografar(String senha){
		String convertida = null;
		try {
			// Obtendo instância do algoritmo SHA-256
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			// Passando a senha como uma sequência de bytes para a criptografia
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			convertida = hexString.toString();	
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return convertida;
	}
}
