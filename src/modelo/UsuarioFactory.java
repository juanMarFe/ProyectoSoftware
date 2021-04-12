package modelo;

import java.util.ArrayList;

public class UsuarioFactory {
	
	private ArrayList<Usuario> list = new ArrayList<>();
	
	public void saveUsuario(int index, Usuario user) {
		list.add(index, user);
	}
	
	public Usuario getUsuario(int index) {
		return (Usuario)list.get(index);
	}

}
