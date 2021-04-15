package modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Singleton {
	static private Singleton singleton = null;
	private UsuarioFactory usuarios;

	private Singleton() {
		usuarios = new UsuarioFactory();
	}

	public static Singleton crearInstaSingleton() {

            if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	public UsuarioFactory getUsuarioFactory() {
		return usuarios;
	}

//	//----------------------------------------------OTROS-----------------------------------------------------
        
        public Usuario obtenerUsuario(String login) {
		return usuarios.getUsuario(login);
	}
                
	//--------------------------------------------CRUD EMPRESAS------------------------------------------------
	public void C_Empresa(Empresa empresa) {
		usuarios.saveUsuario(empresa.getLogin(), empresa);
	}

	public Usuario R_Empresa(String key) {
		return usuarios.getUsuario(key);
	}

	public boolean U_Empresa(String viejoPointer, Empresa empresa) {
		try {
			usuarios.updateUsuario(viejoPointer,empresa.getLogin(),empresa);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean D_Empresa(String index) {
		try {
			return usuarios.deleteUsuario(index);
		} catch (Exception e) {
			return false;
		}
	}
	
	//--------------------------------------------CRUD TRABAJADORES------------------------------------------------
	public void C_Trabajador(Trabajador trabajador) {
		usuarios.saveUsuario(trabajador.getLogin(), trabajador);
	}

	public Usuario R_Trabajador(String key) {
		return usuarios.getUsuario(key);
	}

	public boolean U_Trabajador(String viejoPointer, Trabajador trabajador) {
		try {
			usuarios.updateUsuario(viejoPointer,trabajador.getLogin(),trabajador);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean D_Trabajador(String index) {
		try {
			return usuarios.deleteUsuario(index);
		} catch (Exception e) {
			return false;
		}
	}
	
	//--------------------------------------------CRUD PSICOLOGOS------------------------------------------------
	public void C_Psicologo(PsicologoAdapter psicologo) {
		usuarios.saveUsuario(psicologo.getLogin(), psicologo);
	}

	public Usuario R_Psicologo(String key) {
		return usuarios.getUsuario(key);
	}

	public boolean U_Psicologo(String pointer, PsicologoAdapter psicologo) {
		try {
			usuarios.updateUsuario(pointer, psicologo.getLogin(), psicologo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean D_Psicologo(String index) {
		try {
			return usuarios.deleteUsuario(index);
		} catch (Exception e) {
			return false;
		}
	}
	
	
	//--------------------------------------------CRUD AGRUPACIONES--------------------------------------------
	public void C_AgrupacionOferta(Agrupacion oferta, String pointer) {
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
		empresa.addAgrupacion(oferta);
	}
	
	
	public void C_AgrupacionEmpresa(String pointer, String pointer2) {
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
		Empresa subEmpresa = (Empresa)usuarios.getUsuario(pointer2);
		empresa.addAgrupacion(subEmpresa);
	}
	
	public String R_TodasLasOfertas(String pointer) {
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
		return empresa.verDatos();
	}
	
	public String R_UnicaOferta(String codigo, String pointer) {
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
		Oferta oferta = empresa.getOfertaIndividual(codigo);
		
		return oferta.verDatos();
	}
        
        public void D_Oferta(String codigo, String pointer){
            Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
            empresa.deleteOferta(codigo);
        }
        
        public String decodificadorUsuario(String text1) {
        	
        	byte[] key;
        	SecretKeySpec secretKey = null;
        	MessageDigest sha = null;
        	try {
        		String myKey=text1.substring(0, 10);
            	String text= text1.substring(10,text1.length());
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
        		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        	    cipher.init(Cipher.DECRYPT_MODE, secretKey);
        	    String temp=new String(cipher.doFinal(Base64.getDecoder().decode(text)));
        	    String[] tempA=temp.split(",");
        	    return tempA[0];
        	} 
        	catch (Exception e) 
        	{
        	    System.out.println("Error while decrypting: " + e.toString());
        	    return null;
        	}
        }
        
	
	/*public void U_Agrupacion(Agrupacion agrupacion, Empresa empresa) {
		if(empresa.getId()==agrupacion.verDatos()) {
			
		}
	}
	
	public void D_Agrupacion(String pointer) {
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
	}

	public void addSueldo(Oferta oferta, String pointer, float sueldo) {
		//
		Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
		empresa.addAgrupacion(new SueldoMensual(oferta, sueldo));
	}
	*/
	


		
}



