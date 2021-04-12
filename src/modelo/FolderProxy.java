package modelo;

public class FolderProxy implements IFolder {

	Folder folder;
	Usuario user;
	Singleton s = Singleton.crearInstaSingleton();
	
	public FolderProxy(Usuario user) {
		this.user = user;
	}

	@Override
	public boolean performOperation() {
		// TODO Auto-generated method stub
		
		if(user.getLogin().equalsIgnoreCase("arpit") && user.getPassword().equalsIgnoreCase("arpit")) {
			folder = new Folder();
			folder.performOperation();
			return true;
		}else {
			System.out.println("You dont have access to this folder");
			return false;
		}
	}
}
