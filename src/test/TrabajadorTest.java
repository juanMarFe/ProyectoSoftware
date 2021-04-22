/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import org.junit.jupiter.api.Test;

import modelo.Trabajador;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class TrabajadorTest {
    
    public TrabajadorTest() {
    }
    
        Trabajador trabajador1 = new Trabajador("gabriel", "123", "Gabriel Nieves", "98");

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
	public void testDocumento() {
		String documento = trabajador1.getDocumento();
		assertEquals(documento, "98");
	}
        
        @Test
	public void testNombre() {
		String nombre = trabajador1.getNombre();
		assertEquals(nombre, "Gabriel Nieves");
	}
    
 
}
