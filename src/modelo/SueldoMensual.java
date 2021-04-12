package modelo;

public class SueldoMensual extends OfertaDecorator{
	private String sueldo;

	public SueldoMensual(Agrupacion specialOferta, String sueldo) {
		super(specialOferta);
		this.sueldo =sueldo;	
	}

	public String verDatos() {
		return specialOferta.verDatos() + addSueldo();
	}
	
	private String addSueldo() {
		return ", sueldo mensual: "+this.sueldo;
	}
	
}
