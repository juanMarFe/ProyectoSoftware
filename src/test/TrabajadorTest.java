package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Trabajador;
import modelo.Usuario;

public class TrabajadorTest {
	
	Usuario trabajador1 = new Trabajador ("gabriel", "123", "98");

	@Test
	public void testLogin() {
		String login = trabajador1.getLogin();
		assertEquals(login, "gabriel");
	}
	
	@Test
	public void testPassword() {
		String password = trabajador1.getPassword();
		assertEquals(password, "123");
	}
	
	@Test
	public void testWrongLogin() {
		boolean wrongLogin = trabajador1.checkLogin("grabrel", "123");
		assertFalse(wrongLogin);
	}
	
	@Test
	public void testWrongPassword() {
		boolean wrongPassword = trabajador1.checkLogin("gabriel", "1234");
		assertFalse(wrongPassword);
	}
	
	@Test
	public void testSuccess() {
		boolean success = trabajador1.checkLogin("gabriel", "123");
		assertTrue(success);
	}
	
	@Test
	public void testType() {
		String type = trabajador1.getTipoUsuario();
		assertEquals(type, "Trabajador");
	}
	
	@Test
	public void testId() {
		String id = trabajador1.getId();
		assertEquals(id, "98");
	}

}
