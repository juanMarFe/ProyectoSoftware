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
        usuarios.saveUsuario("admin", new Administrador("admin", "admin123"));
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
    public Usuario obtenerUsuario(String key) {
        return usuarios.getUsuario(decodificadorUsuario(key));
    }

    public String getAllTrabajadores() {
        return usuarios.getAllTrabajadores();
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

    public String R_TodasLasOfertas() {
        String temp = "";
        for (Object key : this.usuarios.getList().keySet()) {
            Empresa empresa = (Empresa) usuarios.getUsuario(key.toString());
            temp = empresa.verDatos();
        }
        return temp;
    }

    public String R_UnicaOferta(String codigo, String pointer) {
        Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
        Oferta oferta = empresa.getOfertaIndividual(codigo);

        return oferta.verDatos();
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

    // --------------------------------------------CRUD EMPRESAS------------------------------------------------
    public String C_Empresa(Empresa empresa, String key) {
        try {
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

    public String U_Empresa(String viejoPointer, Empresa empresa, String key) {
        try {
            Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
            if (u instanceof Administrador || u instanceof Empresa) {
                if (usuarios.getUsuario(empresa.getLogin()) == null || empresa.getLogin().equals(viejoPointer)) {
                    boolean c = usuarios.updateUsuario(viejoPointer, empresa.getLogin(), empresa);
                    if (c) {
                        return "Se ha actualizado la empresa correctamente";
                    } else {
                        return "Ha ocurrido un error procesando su transacci�n";
                    }
                } else {
                    return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
                }
            } else {
                return "No tiene permisos suficientes para realizar esta acci�n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ha ocurrido un error procesando su transacci�n";
        }
    }

    public String D_Empresa(String index, String key) {
        try {
            Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
            if (u instanceof Administrador || u instanceof Empresa) {
                boolean c = usuarios.deleteUsuario(index);
                ;
                if (c) {
                    return "Se ha borrado la empresa correctamente";
                } else {
                    return "Ha ocurrido un error procesando su transacci�n";
                }
            } else {
                return "No tiene permisos suficientes para realizar esta acci�n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ha ocurrido un error procesando su transacci�n";
        }
    }

    // --------------------------------------------CRUD TRABAJADORES------------------------------------------------
    public String C_Trabajador(Trabajador trabajador) {
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

    public String U_Trabajador(String viejoPointer, Trabajador trabajador, String key) {
        try {
            Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
            if (u instanceof Trabajador || u instanceof Administrador) {
                if (usuarios.getUsuario(trabajador.getLogin()) == null || trabajador.getLogin().equals(viejoPointer)) {
                    boolean c = usuarios.updateUsuario(viejoPointer, trabajador.getLogin(), trabajador);
                    if (c) {
                        return "Se ha actualizado la cuenta correctamente";
                    } else {
                        return "Ha ocurrido un error procesando su transacci�n";
                    }
                } else {
                    return "El usuario que ha querido actualizar ya existe, por favor escoja otro usuario";
                }
            } else {
                return "No tiene permisos suficientes para realizar esta acci�n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ha ocurrido un error procesando su transacci�n";
        }
    }

    public String D_Trabajador(String index, String key) {
        try {
            Usuario u = usuarios.getUsuario(decodificadorUsuario(key));
            if (u instanceof Administrador || u instanceof Trabajador) {
                boolean c = usuarios.deleteUsuario(index);

                if (c) {
                    return "Se ha borrado el usuario correctamente";
                } else {
                    return "Ha ocurrido un error procesando su transacci�n";
                }
            } else {
                return "No tiene permisos suficientes para realizar esta acci�n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ha ocurrido un error procesando su transacci�n";
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////TODO POR ENCIMA DE �STA LINEA EST� LISTO
    // --------------------------------------------CRUD PSICOLOGOS------------------------------------------------

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

    // --------------------------------------------CRUD AGRUPACIONES--------------------------------------------
    public void C_AgrupacionOferta(Agrupacion oferta, String pointer) {
        Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
        empresa.addAgrupacion(oferta);
    }

    public void C_AgrupacionEmpresa(String pointer, String pointer2) {
        Empresa empresa = (Empresa) usuarios.getUsuario(pointer);
        Empresa subEmpresa = (Empresa) usuarios.getUsuario(pointer2);
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
