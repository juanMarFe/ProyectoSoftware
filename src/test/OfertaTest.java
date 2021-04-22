/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.jupiter.api.Test;

import modelo.Oferta;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class OfertaTest {
    
    public OfertaTest() {
    }
    	
	Oferta oferta1 = new Oferta("1","Software Engineer","Un trabajo fuerte");

	@Test
	public void testVerDatos() {
		String datos = "Oferta de codigo " + oferta1.getCodigo() + ". Se busca " + oferta1.getCargo() + ". Descripcion: " + oferta1.getDescripcion();
		assertEquals(datos, "Oferta de codigo 1. Se busca Software Engineer. Descripcion: Un trabajo fuerte");
	}
	
	@Test
	public void testGetCodigo() {
		String codigo = oferta1.getCodigo();
		assertEquals(codigo, "1");
	}
    
        @Test
	public void testGetCargo() {
		String cargo = oferta1.getCargo();
		assertEquals(cargo, "Software Engineer");
	}
        
        @Test
	public void testGetDescripcion() {
		String descripcion = oferta1.getDescripcion();
		assertEquals(descripcion, "Un trabajo fuerte");
	}
    
}
