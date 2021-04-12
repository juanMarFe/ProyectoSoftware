package modelo;

public abstract class Usuario {
	protected String login;
	protected String password;
	protected String id;

	public Usuario(String login, String password, String id) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public abstract String getId();

	public abstract void setId(String id);

	abstract public String getLogin();

	public abstract void setLogin(String login);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract boolean checkLogin(String loginTry, String passwordTry);

	public abstract String getTipoUsuario();

}
