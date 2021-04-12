package modelo;

public class Folder implements IFolder {

	@Override
	public boolean performOperation() {
		System.out.println("Performing operation on folder");
		return true;
	}

}
