package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Oferta;

public class OfertaTest {

	Oferta oferta = new Oferta("1","Software Engineer", "Un trabajo fuerte");
	
	@Test
	public void testVerDatos() {
		String datos = "Oferta de codigo " + oferta.getCodigo() + ". Se busca " + oferta.getCargo() + ". Descripcion: " + oferta.getDescripcion();
		assertEquals(datos, "Oferta de codigo 1. Se busca Software Engineer. Descripcion: Un trabajo fuerte");
	}
	
	@Test
	public void getCodigo() {
		String codigo = oferta.getCodigo();
		assertEquals(codigo, "1");
	}
	
	@Test
	public void getCargo() {
		String cargo = oferta.getCargo();
		assertEquals(cargo, "Software Engineer");
	}
	
	@Test
	public void getDescripcion() {
		String descripcion = oferta.getDescripcion();
		assertEquals(descripcion, "Un trabajo fuerte");
	}

}
