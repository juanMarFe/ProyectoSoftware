/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.jupiter.api.Test;

import modelo.Administrador;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class AdministradorTest {
    
    public AdministradorTest() {
    }
    
    Administrador admin1 = new Administrador("admin1", "1234");
		
        @Test
        public void testLogin() {
            String login = admin1.getLogin();
            assertEquals(login, "admin1");
        }
		
	@Test
        public void testPassword() {
            String password = admin1.getPassword();
            assertEquals(password, "1234");
        }
		
	@Test
	public void testWrongLogin() {
            boolean wrongLogin = admin1.checkLogin("admin1", "123");
            assertFalse(wrongLogin);
	}
		
	@Test
	public void testWrongPassword() {
            boolean wrongPassword = admin1.checkLogin("admin1", "123");
            assertFalse(wrongPassword);
	}
		
	@Test
	public void testSuccess() {
            boolean success = admin1.checkLogin("admin1", "1234");
            assertTrue(success);
	}

	@Test
	public void testType() {
            String type = admin1.getTipoUsuario();
            assertEquals(type, "Admin");
	}  
}
