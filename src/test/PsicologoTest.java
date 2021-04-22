/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.jupiter.api.Test;

import modelo.Psicologo;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class PsicologoTest {
    
    public PsicologoTest() {
    }
    
    Psicologo psicologo = new Psicologo("Gabriel", "548");
    
        @Test
	public void testGetName() {
		String nombre = psicologo.getName();
		assertEquals(nombre, "Gabriel");
	}
        
        @Test
	public void testGetDocument() {
		String documento = psicologo.getDoc();
		assertEquals(documento, "548");
	}
        
        @Test
	public void testUserType() {
		String tipo = psicologo.getTipoUser();
		assertEquals(tipo, "Psicologo");
	}
}
