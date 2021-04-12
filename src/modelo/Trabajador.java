package modelo;

public class Trabajador extends Usuario {

	public Trabajador(String login, String password, String id) {
		super(login, password, id);
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;

	}

	@Override
	public boolean checkLogin(String loginTry, String passwordTry) {
		if (loginTry.equals(login) && passwordTry.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getTipoUsuario() {
		return "Trabajador";
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

}
