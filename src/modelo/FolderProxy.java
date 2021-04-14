package modelo;

import java.util.HashMap;
import java.util.Map;

public class FolderProxy implements IFolder {

	Folder folder;
	Usuario user;
	Singleton s = Singleton.crearInstaSingleton();
	UsuarioFactory uf=s.getUsuarioFactory();
	HashMap map=uf.getList();
	private String password;
	private String usuario;
        
	public FolderProxy(String usuario, String password) {
		this.usuario=usuario;
		this.password=password;
	}

	@Override
	public String performOperation() {
            boolean b=false;
            Object point=null;
            for (Object key : map.keySet()) {
                if((uf.getUsuario(key.toString()).checkLogin(usuario, password))) {
                    point=key;
                    b=true;
                    break;
                }
	    }
		if (b) {
                    folder= new Folder();
                    System.out.println(folder.performOperation());
                    return point.toString();
		}else {
			return null;
		}
	}
}
