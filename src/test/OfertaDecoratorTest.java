/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Agrupacion;
import modelo.Oferta;
import modelo.SueldoMensual;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author JOSE NIEVES
 */
public class OfertaDecoratorTest {
    
    public OfertaDecoratorTest() {
    }
    
        float sueldo = 500000;
	Agrupacion oferta1 = new Oferta("1","Software Engineer","Un trabajo fuerte");
	Agrupacion oferta2 = new SueldoMensual(new Oferta("1","Software Engineer","Un trabajo fuerte"), sueldo);

	@Test
	public void testOferta() {
		String datosOferta = oferta1.verDatos();
                System.out.println(datosOferta);
		assertEquals(datosOferta, "Oferta de codigo 1 Se busca Software Engineer. Descripcion: Un trabajo fuerte.");
	}
	
	@Test
	public void testDecoration() {
		String datosOferta = oferta2.verDatos();
                System.out.println(datosOferta);
		assertEquals(datosOferta, "Oferta de codigo 1 Se busca Software Engineer. Descripcion: Un trabajo fuerte. Sueldo mensual: 500000.0");
	}
}
