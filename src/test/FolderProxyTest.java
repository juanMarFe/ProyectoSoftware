package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.FolderProxy;
import modelo.Trabajador;
import modelo.Usuario;

public class FolderProxyTest {

	Usuario rightTrabajador = new Trabajador("arpit", "arpit");
	Usuario wrongLoginTrabajador = new Trabajador("arpi", "arpit");
	Usuario wrongPasswordTrabajador = new Trabajador("arpit", "123");
	
	FolderProxy rightFolder = new FolderProxy(rightTrabajador);
	FolderProxy wrongFolder1 = new FolderProxy(wrongLoginTrabajador);
	FolderProxy wrongFolder2 = new FolderProxy(wrongPasswordTrabajador);

	@Test
	public void testSuccess() {
		boolean response = rightFolder.performOperation();
		assertEquals(response, true);
	}
	
	@Test
	public void testWrongLogin() {
		boolean response = wrongFolder1.performOperation();
		assertEquals(response, false);
	}
	
	@Test
	public void testWrongPassword() {
		boolean response = wrongFolder2.performOperation();
		assertEquals(response, false);
	}

}
