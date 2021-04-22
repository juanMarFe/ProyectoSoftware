package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Facade;
import controlador.FolderProxy;
import modelo.Empresa;
import modelo.PsicologoAdapter;
import modelo.Trabajador;

class FacadeTest {
	FolderProxy admin;
	Facade fac;
	public FacadeTest() {
		this.admin = new FolderProxy("admin", "admin123");
		this.fac = Facade.crearInstaSingleton();
		fac.C_Empresa("Empresa1", "123", "123", "Empresa1", "direccion", this.admin.performOperation());
		fac.C_Trabajador("Trabajador1", "123", "Trabajador1", "123456");
		fac.C_Psicologo(new PsicologoAdapter("Psicologo1", "123", "Psicologo123", "123456"));
	}
	

	@Test
	void testC_Empresa() {
		FolderProxy usuario= new FolderProxy("Empresa1", "123");
		boolean esta = false;
		if (fac.R_Empresa(usuario.performOperation()) != null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testR_Empresa() {
		FolderProxy usuario= new FolderProxy("Empresa1", "123");
		Empresa temp= new Empresa("Empresa1", "123", "123", "Empresa1", "direccion");
		boolean igual = false;
		if ((temp.getLogin().equals(fac.R_Empresa(usuario.performOperation()).getLogin())) &&
				(temp.getPassword().equals(fac.R_Empresa(usuario.performOperation()).getPassword())) &&
				(temp.getNIT().equals(fac.R_Empresa(usuario.performOperation()).getNIT())) &&
				(temp.getNombre().equals(fac.R_Empresa(usuario.performOperation()).getNombre())) &&
				(temp.getDireccion().equals(fac.R_Empresa(usuario.performOperation()).getDireccion()))) {
			igual = true;	
		}
		System.out.println(igual);
		assertTrue(igual);
	}

	@Test
	void testU_Empresa() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Empresa() {
		FolderProxy usuario= new FolderProxy("Empresa1", "123");
		boolean esta = false;
		fac.D_Empresa(fac.R_Empresa(usuario.performOperation()).getLogin(), usuario.performOperation());
		if (fac.R_Empresa(usuario.performOperation()) == null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testC_Trabajador() {
		FolderProxy usuario= new FolderProxy("Trabajador1", "123");
		boolean esta = false;
		if (fac.R_Trabajador(usuario.performOperation()) != null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testR_Trabajador() {
		FolderProxy usuario= new FolderProxy("Trabajador1", "123");
		Trabajador temp= new Trabajador("Trabajador1", "123", "Trabajador1", "123456");
		boolean igual = false;
		if ((temp.getLogin().equals(fac.R_Trabajador(usuario.performOperation()).getLogin())) &&
				(temp.getPassword().equals(fac.R_Trabajador(usuario.performOperation()).getPassword())) &&
				(temp.getNombre().equals(fac.R_Trabajador(usuario.performOperation()).getNombre())) &&
				(temp.getDocumento().equals(fac.R_Trabajador(usuario.performOperation()).getDocumento()))) {
			igual = true;	
		}
		System.out.println(igual);
		assertTrue(igual);
	}

	@Test
	void testU_Trabajador() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Trabajador() {
		
		FolderProxy usuario= new FolderProxy("Trabajador1", "123");
		boolean esta = false;
		fac.D_Trabajador(fac.R_Trabajador(usuario.performOperation()).getLogin(), usuario.performOperation());
		if (fac.R_Empresa(usuario.performOperation()) == null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testC_Psicologo() {
		FolderProxy usuario= new FolderProxy("Psicologo1", "123");
		boolean esta = false;
		if (fac.R_Psicologo(usuario.performOperation()) != null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testR_Psicologo() {
		FolderProxy usuario= new FolderProxy("Psicologo1", "123");
		PsicologoAdapter temp= new PsicologoAdapter("Psicologo1", "123", "Psicologo123", "123456");
		boolean igual = false;
		if ((temp.getLogin().equals(fac.R_Psicologo(usuario.performOperation()).getLogin())) &&
				(temp.getPassword().equals(fac.R_Psicologo(usuario.performOperation()).getPassword())) &&
				(temp.getNombre().equals(fac.R_Psicologo(usuario.performOperation()).getNombre())) &&
				(temp.getDocumento().equals(fac.R_Psicologo(usuario.performOperation()).getDocumento()))) {
			igual = true;	
		}
		System.out.println(igual);
		assertTrue(igual);
	}

	@Test
	void testU_Psicologo() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Psicologo() {
		FolderProxy usuario= new FolderProxy("Psicologo1", "123");
		boolean esta = false;
		fac.D_Psicologo(fac.R_Psicologo(usuario.performOperation()).getLogin(), usuario.performOperation());
		if (fac.R_Empresa(usuario.performOperation()) == null) {
			esta=true;	
		}
		System.out.println(esta);
		assertTrue(esta);
	}

	@Test
	void testC_AgrupacionOferta() {
		fail("Not yet implemented");
	}

	@Test
	void testC_AgrupacionEmpresa() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Oferta() {
		fail("Not yet implemented");
	}

}
