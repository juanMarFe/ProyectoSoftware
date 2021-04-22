package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Facade;
import controlador.FolderProxy;
import modelo.Empresa;

class FacadeTest {
	FolderProxy admin;
	Facade fac;
	public FacadeTest() {
		this.admin = new FolderProxy("admin", "admin123");
		this.fac = Facade.crearInstaSingleton();
		fac.C_Empresa("Santi", "123", "123", "Santi", "jasnkdm", this.admin.performOperation());
	}
	

	@Test
	void testC_Empresa() {
		fail("Not yet implemented");
	}

	@Test
	void testR_Empresa() {
		fail("Not yet implemented");
	}

	@Test
	void testU_Empresa() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Empresa() {
		fail("Not yet implemented");
	}

	@Test
	void testC_Trabajador() {
		fail("Not yet implemented");
	}

	@Test
	void testR_Trabajador() {
		fail("Not yet implemented");
	}

	@Test
	void testU_Trabajador() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Trabajador() {
		
		fail("Not yet implemented");
	}

	@Test
	void testC_Psicologo() {
		fail("Not yet implemented");
	}

	@Test
	void testR_Psicologo() {
		fail("Not yet implemented");
	}

	@Test
	void testU_Psicologo() {
		fail("Not yet implemented");
	}

	@Test
	void testD_Psicologo() {
		fail("Not yet implemented");
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
