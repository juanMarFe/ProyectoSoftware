package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.FolderProxy;
import modelo.Trabajador;
import modelo.Usuario;

public class FolderProxyTest {

	Usuario rightTrabajador = new Trabajador("arpit", "arpit", "1");
	Usuario wrongLoginTrabajador = new Trabajador("arpi", "arpit","1");
	Usuario wrongPasswordTrabajador = new Trabajador("arpit", "123","1");
	
	FolderProxy rightFolder = new FolderProxy(rightTrabajador);
	FolderProxy wrongFolder1 = new FolderProxy(wrongLoginTrabajador);
	FolderProxy wrongFolder2 = new FolderProxy(wrongPasswordTrabajador);

	@Test
	public void testSuccess() {
		String response = rightFolder.performOperation();
		assertEquals(response, true);
		return "ESTO ESTA MAL Y TOCA CORREGIRLO";
	}
	
	@Test
	public void testWrongLogin() {
		String response = wrongFolder1.performOperation();
		assertEquals(response, null);
	}
	
	@Test
	public void testWrongPassword() {
		String response = wrongFolder2.performOperation();
		assertEquals(response, null);
	}

}
