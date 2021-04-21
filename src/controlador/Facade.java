package controlador;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import modelo.Administrador;
import modelo.Agrupacion;
import modelo.Empresa;
import modelo.Oferta;
import modelo.PsicologoAdapter;
import modelo.Trabajador;
import modelo.Usuario;
import modelo.UsuarioFactory;

public class Facade {

	static private Facade singleton = null;
	private UsuarioFactory usuarios;

	private Facade() {
		this.usuarios = new UsuarioFactory();
		this.usuarios.saveUsuario("admin", new Administrador("admin", "admin123"));
	}

	public static Facade crearInstaSingleton() {

		if (singleton == null) {
			singleton = new Facade();
		}
		return singleton;
	}

	public UsuarioFactory getUsuarioFactory() {
		return usuarios;
	}

//	//----------------------------------------------OTROS-----------------------------------------------------
	public Usuario obtenerUsuario(String key) {
		return usuarios.getUsuario(decodificadorUsuario(key));
	}

	public String getAllTrabajadores() {
		return usuarios.getAllTrabajadores();
	}

	public String R_TodasLasOfertas() {
		String temp = "";
		for (Usuario key : this.usuarios.getList().values()) {
			if (key.getTipoUsuario().equals("Empresa")) {
				Empresa empresa = (Empresa) usuarios.getUsuario(key.getLogin());
				temp = empresa.verDatos();
			}
		}
		return temp;
	}

	public String R_UnicaOferta(String codigo, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		Oferta oferta = empresa.getOfertaIndividual(codigo);

		return oferta.verDatos();
	}

	public Empresa BuscarEmpresas(String login) {
		try {
			Usuario u = usuarios.getUsuario(login);
			if (u instanceof Empresa) {
				return (Empresa) usuarios.getUsuario(login);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Trabajador BuscarTrabajadores(String login) {
		try {
			Usuario u = usuarios.getUsuario(login);
			if (u instanceof Trabajador) {
				return (Trabajador) usuarios.getUsuario(login);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PsicologoAdapter BuscarPsicologos(String login) {
		try {
			Usuario u = usuarios.getUsuario(login);
			if (u instanceof PsicologoAdapter) {
				return (PsicologoAdapter) usuarios.getUsuario(login);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean EliminarEmpresadeEmpresa(String sEmpresaPadre, String kEmpresaHija) {
		try {
			Empresa empresaPadre = BuscarEmpresas(sEmpresaPadre);
			Empresa empresaHija = R_Empresa(kEmpresaHija);
			boolean c = false;
			ArrayList<Agrupacion> agrupaciones = empresaPadre.getAgrupaciones();
			for (int i = 0; i < agrupaciones.size(); i++) {
				if (agrupaciones.get(i) instanceof Empresa) {
					Empresa temp = (Empresa) agrupaciones.get(i);
					if (temp.getPadre().equals(empresaHija.getLogin())) {
						agrupaciones.remove(i);
						empresaPadre.setAgrupaciones(agrupaciones);
						c = true;
					}
				}
			}
			if (c) {
				usuarios.updateUsuario(sEmpresaPadre, empresaPadre);
			}
			return c;
		} catch (Exception e) {
			return false;
		}
	}

	// --------------------------------------------CRUD
	// EMPRESAS------------------------------------------------
	public String C_Empresa(String login, String password, String NIT, String nombre, String direccion, String key) {
		try {
			Empresa empresa = new Empresa(login, password, NIT, nombre, direccion);
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
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
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Empresa) {
				return (Empresa) usuarios.getUsuario(decodificadorUsuario(key));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String U_Empresa(String viejoPointer, String login, String password, String NIT, String nombre,
			String direccion, String key) {
		try {
			Empresa empresa = new Empresa(login, password, NIT, nombre, direccion);
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Administrador || u instanceof Empresa) {
				if (!viejoPointer.equals(login)) {
					if (usuarios.getUsuario(empresa.getLogin()) == null || empresa.getLogin().equals(viejoPointer)) {
						boolean c = usuarios.updateUsuarioA(viejoPointer, empresa.getLogin(), empresa);
						if (c) {
							return "Se ha actualizado la empresa correctamente";
						} else {
							return "Ha ocurrido un error procesando su transacción";
						}
					} else {
						return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
					}
				} else {
					boolean c = usuarios.updateUsuario(empresa.getLogin(), empresa);
					if (c) {
						return "Se ha actualizado la empresa correctamente";
					} else {
						return "Ha ocurrido un error procesando su transacción";
					}
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
			if (u instanceof Administrador || u instanceof Empresa) {
				Empresa m = (Empresa) u;
				if (m.getPadre() != null) {
					EliminarEmpresadeEmpresa(m.getLogin(), decodificadorUsuario(key));
				}
				boolean c = usuarios.deleteUsuario(index);
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
	public String C_Trabajador(String login, String password, String nombre, String documento) {
		Trabajador trabajador = new Trabajador(login, password, nombre, documento);
		if (usuarios.getUsuario(trabajador.getLogin()) == null) {
			usuarios.saveUsuario(trabajador.getLogin(), trabajador);
			return "Se ha creado el usuario correctamente";
		} else {
			return "Ese usuario ya existe, porfavor escoja otro";
		}
	}

	public Trabajador R_Trabajador(String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Trabajador) {
				return (Trabajador) usuarios.getUsuario(decodificadorUsuario(key));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String U_Trabajador(String viejoPointer, String login, String password, String nombre, String documento,
			String key) {
		try {
			Trabajador trabajador = new Trabajador(login, password, nombre, documento);
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Trabajador || u instanceof Administrador) {
				if (!viejoPointer.equals(login)) {
					if (usuarios.getUsuario(trabajador.getLogin()) == null
							|| trabajador.getLogin().equals(viejoPointer)) {
						boolean c = usuarios.updateUsuarioA(viejoPointer, trabajador.getLogin(), trabajador);
						if (c) {
							return "Se ha actualizado la cuenta correctamente";
						} else {
							return "Ha ocurrido un error procesando su transacción";
						}
					} else {
						return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
					}
				} else {
					boolean c = usuarios.updateUsuario(trabajador.getLogin(), trabajador);
					if (c) {
						return "Se ha actualizado la cuenta correctamente";
					} else {
						return "Ha ocurrido un error procesando su transacción";
					}
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
			if (u instanceof Administrador || u instanceof Trabajador) {
				boolean c = usuarios.deleteUsuario(index);
				if (c) {
					return "Se ha borrado el trabajador correctamente";
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
	// PSICOLOGOS------------------------------------------------
	public String C_Psicologo(PsicologoAdapter psicologo) {
		if (usuarios.getUsuario(psicologo.getLogin()) == null) {
			usuarios.saveUsuario(psicologo.getLogin(), psicologo);
			return "Se ha creado el psicologo correctamente";
		} else {
			return "Ese usuario ya existe, porfavor escoja otro";
		}
	}

	public PsicologoAdapter R_Psicologo(String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof PsicologoAdapter) {
				return (PsicologoAdapter) usuarios.getUsuario(decodificadorUsuario(key));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String U_Psicologo(String viejoPointer, String login, String password, String nombre, String documento,
			String key) {
		try {
			PsicologoAdapter psicologo = new PsicologoAdapter(login, password, nombre, documento);
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof PsicologoAdapter || u instanceof Administrador) {
				if (!viejoPointer.equals(login)) {
					if (usuarios.getUsuario(psicologo.getLogin()) == null
							|| psicologo.getLogin().equals(viejoPointer)) {
						boolean c = usuarios.updateUsuarioA(viejoPointer, psicologo.getLogin(), psicologo);
						if (c) {
							return "Se ha actualizado la cuenta correctamente";
						} else {
							return "Ha ocurrido un error procesando su transacción";
						}
					} else {
						return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
					}
				} else {
					boolean c = usuarios.updateUsuario(psicologo.getLogin(), psicologo);
					if (c) {
						return "Se ha actualizado la cuenta correctamente";
					} else {
						return "Ha ocurrido un error procesando su transacción";
					}
				}
			} else {
				return "No tiene permisos suficientes para realizar esta acción";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Ha ocurrido un error procesando su transacción";
		}
	}

	public String D_Psicologo(String index, String key) {
		try {
			Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
			if (u instanceof Administrador || u instanceof PsicologoAdapter) {
				boolean c = usuarios.deleteUsuario(index);
				if (c) {
					return "Se ha borrado el psicologo correctamente";
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
	// AGRUPACIONES--------------------------------------------
	public void C_AgrupacionOferta(Agrupacion oferta, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		empresa.addAgrupacion(oferta);
	}

	public void C_AgrupacionEmpresa(String pointer, String pointer2) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		Empresa subEmpresa = (Empresa) usuarios.getUsuario(pointer2);
		if (subEmpresa instanceof Empresa) {
			subEmpresa.setPadre(pointer);
			usuarios.updateUsuario(pointer2, subEmpresa);
		}

		empresa.addAgrupacion(subEmpresa);
	}

	public void D_Oferta(String codigo, String pointer) {
		Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
		empresa.deleteOferta(codigo);
	}

	private String decodificadorUsuario(String text1) {

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
}