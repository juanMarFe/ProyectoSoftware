package modelo;

import java.util.ArrayList;

public class Empresa extends Usuario implements Agrupacion{
	private String nombre;
	private String direccion;
	private ArrayList<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();

	public Empresa(String login, String password, String id, String nombre, String direccion) {
		super(login, password, id);
		this.direccion = direccion;
		this.nombre=nombre;
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
	public String verDatos() {
		String datos = "La empresa "+ nombre + " de id "+id+ " y direccion "+direccion+ " tiene:";
		for(Agrupacion a : agrupaciones) {
				datos+=a.verDatos();
		}
		return datos;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void addAgrupacion(Agrupacion a) {
		agrupaciones.add(a);
	}
	
	public ArrayList<Agrupacion> getOfertas(){
		return agrupaciones;
	}
	
	public void setAgrupaciones(ArrayList<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}


	@Override
	public boolean checkLogin(String loginTry, String passwordTry) {
		if(loginTry.equals(login) && passwordTry.equals(password)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String getTipoUsuario() {
		return "Empresa";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id=id;
		
	}
	
}
