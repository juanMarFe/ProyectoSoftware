/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.jupiter.api.Test;

import modelo.PsicologoAdapter;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class PsicologoAdapterTest {
    
    public PsicologoAdapterTest() {
    }
    
        PsicologoAdapter psicologo1 = new PsicologoAdapter("psico", "1234", "Psicologo 1", "1");
	
	@Test
	public void testLogin() {
		String login = psicologo1.getLogin();
		assertEquals(login, "psico");
	}
	
	@Test
	public void testPassword() {
		String password = psicologo1.getPassword();
		assertEquals(password, "1234");
	}
        
        @Test
	public void testNombre() {
		String login = psicologo1.getNombre();
		assertEquals(login, "Psicologo 1");
	}
        
        @Test
	public void testDocumento() {
		String login = psicologo1.getDocumento();
		assertEquals(login, "1");
	}
	
	@Test
	public void testWrongLogin() {
		boolean wrongLogin = psicologo1.checkLogin("sico", "1234");
		assertFalse(wrongLogin);
	}
	
	@Test
	public void testWrongPassword() {
		boolean wrongPassword = psicologo1.checkLogin("psico", "123");
		assertFalse(wrongPassword);
	}
	
	@Test
	public void testSuccess() {
		boolean success = psicologo1.checkLogin("psico", "1234");
		assertTrue(success);
	}

	@Test
	public void testType() {
		String type = psicologo1.getTipoUsuario();
		assertEquals(type, "Psicologo");
	}
}
