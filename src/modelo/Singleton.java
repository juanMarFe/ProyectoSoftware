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
		usuarios.saveUsuario("Admin", new Administrador("Admin", "Admin123"));
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

	// --------------------------------------------CRUD
	// EMPRESAS------------------------------------------------
	public String C_Empresa(Empresa empresa, String keys) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(keys));
			if (u instanceof Administrador) {
				if (usuarios.getUsuario(empresa.getLogin()) == null) {
					usuarios.saveUsuario(empresa.getLogin(), empresa);
					return "Se ha creado el usuario correctamente";
				} else {
					return "Ese usuario ya existe, porfavor escoja otro";
				}

			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}

	public Empresa R_Empresa(String key) {
		try {
			Usuario u = usuarios.getUsuario(key);
			if (u instanceof Empresa) {
				return (Empresa) usuarios.getUsuario(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String U_Empresa(String viejoPointer, Empresa empresa, String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Administrador) {
				if (usuarios.getUsuario(empresa.getLogin()) == null) {
					boolean c = usuarios.updateUsuario(viejoPointer, empresa.getLogin(), empresa);
					if (c) {
						return "Se ha actualizado la empresa correctamente";
					} else {
						return "Ha ocurrido un error procesando su transacción";
					}
				} else {
					return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
				}
			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}

	public String D_Empresa(String index, String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Administrador) {
				boolean c = usuarios.deleteUsuario(index);
				;
				if (c) {
					return "Se ha borrado la empresa correctamente";
				} else {
					return "Ha ocurrido un error procesando su transacción";
				}
			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}

	// --------------------------------------------CRUD
	// TRABAJADORES------------------------------------------------
	public String C_Trabajador(Trabajador trabajador) {
		if (usuarios.getUsuario(trabajador.getLogin()) == null) {
			usuarios.saveUsuario(trabajador.getLogin(), trabajador);
			return "Se ha creado el usuario correctamente";
		} else {
			return "Ese usuario ya existe, porfavor escoja otro";
		}
	}

	public Usuario R_Trabajador(String key) {
		try {
			Usuario u = usuarios.getUsuario(key);
			if (u instanceof Trabajador) {
				return (Trabajador) usuarios.getUsuario(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String U_Trabajador(String viejoPointer, Trabajador trabajador, String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Trabajador || u instanceof Administrador) {
				if (usuarios.getUsuario(trabajador.getLogin()) == null) {
					boolean c = usuarios.updateUsuario(viejoPointer, trabajador.getLogin(), trabajador);
					if (c) {
						return "Se ha actualizado la empresa correctamente";
					} else {
						return "Ha ocurrido un error procesando su transacción";
					}
				} else {
					return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
				}
			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}

	public String D_Trabajador(String index, String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Administrador|| u instanceof Trabajador) {
				boolean c = usuarios.deleteUsuario(index);
				
				if (c) {
					return "Se ha borrado el usuario correctamente";
				} else {
					return "Ha ocurrido un error procesando su transacción";
				}
			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////TODO POR ENCIMA DE ÉSTA LINEA ESTÁ LISTO
	// --------------------------------------------CRUD
	// PSICOLOGOS------------------------------------------------
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

	// --------------------------------------------CRUD
	// AGRUPACIONES--------------------------------------------
	public void C_AgrupacionOferta(Agrupacion oferta, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		empresa.addAgrupacion(oferta);
	}

	public void C_AgrupacionEmpresa(String pointer, String pointer2) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		Empresa subEmpresa = (Empresa) usuarios.getUsuario(pointer2);
		empresa.addAgrupacion(subEmpresa);
	}

	public String R_TodasLasOfertas(String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		return empresa.verDatos();
	}

	public String R_UnicaOferta(String codigo, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		Oferta oferta = empresa.getOfertaIndividual(codigo);

		return oferta.verDatos();
	}

	public void D_Oferta(String codigo, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		empresa.deleteOferta(codigo);
	}

	public String decodificadorUsuario(String text1) {

		byte[] key;
		SecretKeySpec secretKey = null;
		MessageDigest sha = null;
		try {
			String myKey = text1.substring(0, 10);
			String text = text1.substring(10, text1.length());
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
			String temp = new String(cipher.doFinal(Base64.getDecoder().decode(text)));
			String[] tempA = temp.split(",");
			return tempA[0];
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
			return null;
		}
	}

	/*
	 * public void U_Agrupacion(Agrupacion agrupacion, Empresa empresa) {
	 * if(empresa.getId()==agrupacion.verDatos()) {
	 * 
	 * } }
	 * 
	 * public void D_Agrupacion(String pointer) { Empresa empresa =
	 * (Empresa)usuarios.getUsuario(pointer); }
	 * 
	 * public void addSueldo(Oferta oferta, String pointer, float sueldo) { //
	 * Empresa empresa = (Empresa)usuarios.getUsuario(pointer);
	 * empresa.addAgrupacion(new SueldoMensual(oferta, sueldo)); }
	 */

}
