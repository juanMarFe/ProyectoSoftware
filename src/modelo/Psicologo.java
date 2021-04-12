package modelo;

public class Psicologo {

	private String usuario;
	private String contrasena;
	
	public Psicologo(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
		
	}

	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
		
	}

	public boolean checkInfo(String usuarioTry, String contrasenaTry) {
		if(usuarioTry.equals(usuario) && contrasenaTry.equals(contrasena)) {
			return true;
		}else {
			return false;
		}
	}

	public String getTipoUser() {
		return "Psicologo";
	}
	
}
