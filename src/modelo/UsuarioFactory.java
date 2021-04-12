package modelo;

import java.util.HashMap;

public class UsuarioFactory {

	private HashMap list = new HashMap();
	
	

	public UsuarioFactory() {
	}

	public void saveUsuario(String index,Usuario user) {
		list.put(index, user);
	
	}

	public Usuario getUsuario(String index) {
		return (Usuario) list.get(index);
	}
	
	public boolean delUsuario(String index) {
		try {
			list.remove(index);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean updateUsuario(String viejoPointer,String nuevoPointer, Usuario user) {
		try {
			list.remove(viejoPointer);
			list.put(nuevoPointer, user);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public HashMap getList() {
		return list;
	}

}
