package modelo;

public class PsicologoAdapter extends Usuario {
	
	private Psicologo psicologo;
	
	public PsicologoAdapter(String login, String password, String nombre, String documento) {
		super(login, password);
		this.psicologo = new Psicologo(login, password, nombre, documento);
	}

	@Override
	public String getLogin() {
		return this.psicologo.getUsuario();
	}

	@Override
	public void setLogin(String login) {
		this.psicologo.setUsuario(login);	
	}

	@Override
	public String getPassword() {
		return this.psicologo.getContrasena();
	}

	@Override
	public void setPassword(String password) {
		this.psicologo.setContrasena(password);
		
	}

	@Override
	public boolean checkLogin(String loginTry, String passwordTry) {
		return this.psicologo.checkInfo(loginTry, passwordTry);
	}

	@Override
	public String getTipoUsuario() {
		return this.psicologo.getTipoUser();
	}
        
        public String getNombre() {
		return this.psicologo.getName();
	}
	
	public void setNombre(String nombre) {
		this.psicologo.setName(nombre);
	}
        
        public String getDocumento() {
		return this.psicologo.getDoc();
	}
	
	public void setDoc(String documento) {
		this.psicologo.setDoc(documento);
	}
        
        
}
