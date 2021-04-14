package modelo;

public class Psicologo {

	private String usuario;
	private String contrasena;
        private String nombre;
        private String documento;
	
	public Psicologo(String usuario, String contrasena, String nombre, String documento) {
		this.usuario = usuario;
		this.contrasena = contrasena;
                this.nombre = nombre;
                this.documento = documento;
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
        
        public String getName() {
		return nombre;
	}
	
	public void setName(String nombre) {
		this.nombre = nombre;
	}
        
        public String getDoc() {
		return documento;
	}
	
	public void setDoc(String documento) {
		this.documento = documento;
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
