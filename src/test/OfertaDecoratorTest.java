package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Agrupacion;
import modelo.Oferta;
import modelo.SueldoMensual;

public class OfertaDecoratorTest {
	
	float sueldo = 500000;
	
	Agrupacion oferta1 = new Oferta("1","Software Engineer","Un trabajo fuerte");
	Agrupacion oferta2 = new SueldoMensual(new Oferta("1","Software Engineer","Un trabajo fuerte"),sueldo);

	@Test
	public void testOferta() {
		String datosOferta = oferta1.verDatos();
		assertEquals(datosOferta, " Oferta de codigo 1 Se busca Software Engineer. Descripcion: Un trabajo fuerte.");
	}
	
	@Test
	public void testDecoration() {
		String datosOferta = oferta2.verDatos();
		assertEquals(datosOferta, " Oferta de codigo 1 Se busca Software Engineer. Descripcion: Un trabajo fuerte. Sueldo mensual: 500000.0");
	}

}
