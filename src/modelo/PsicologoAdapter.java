package modelo;

public class PsicologoAdapter extends Usuario {
	
	private Psicologo psicologo;
	
	public PsicologoAdapter(String login, String password) {
		super(login, password);
		this.psicologo = new Psicologo(login, password);
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

}
