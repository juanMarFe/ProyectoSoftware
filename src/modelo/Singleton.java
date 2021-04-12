package modelo;

import java.util.ArrayList;

public class Singleton {
	static private Singleton singleton = null;
	private ArrayList<Empresa> empresas;
	private ArrayList<Trabajador> trabajadores;

	private Singleton() {
		this.empresas = new ArrayList<Empresa>();

	}

	public static Singleton crearInstaSingleton() {

		if (singleton == null) {
			singleton = new Singleton();
		}

		return singleton;
	}

	public boolean C_Oferta(String codigo, String cargo, String descripcion, Empresa empresa) {
		try {
			boolean f = false;
			int r = -1;
			for (int i = 0; i < empresas.size(); i++) {
				if (empresa.getNIT() == empresas.get(i).getNIT()) {
					f = true;
					r = i;
				}
			}
			if (f) {
				empresas.get(r).addAgrupacion(new Oferta(codigo, cargo, descripcion));
			}
			return f;

		} catch (Exception e) {
			return false;
		}
	}

	public String R_Oferta(String codigo) {
		String oferta = null;
		for (int i = 0; i < empresas.size(); i++) {
			for (int j = 0; j < empresas.get(i).getOfertas().size(); j++) {
				String[] y = empresas.get(i).getOfertas().get(j).verDatos().split(" ");
				if (empresas.get(i).getOfertas().get(j).verDatos() == y[3]) {
					oferta = empresas.get(i).getOfertas().get(j).verDatos();
				}
			}
		}
		return oferta;
	}

	public boolean U_Oferta() {

		return true;
	}

	public boolean D_Oferta(Empresa empresa, String oferta) {
		try {
			Empresa e = null;
			boolean b = false;
			for (int i = 0; i < empresas.size(); i++) {
				if (empresas.get(i).getNIT().equals(empresa.getNIT())) {
					e = empresas.get(i);
				}
			}
			if (e != null) {
				for (int i = 0; i < e.getOfertas().size(); i++) {
					if (e.getOfertas().get(i).verDatos().equals(oferta)) {
						e.getOfertas().remove(i);
						b = true;
					}
				}
			}
			return b;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean C_Empresa(Empresa empresa) {
		try {
			boolean b = true;
			for (int i = 0; i < empresas.size(); i++) {
				if (empresas.get(i).getNIT().equals(empresa.getNIT())) {
					b = false;
				}
			}
			if(b) {
				empresas.add(empresa);
			}
			return b;
		} catch (Exception e) {
			return false;
		}

	}

	public Empresa R_Empresa(String nit) {
		Empresa empresa=null;
		for (int i = 0; i < empresas.size(); i++) {
			if (empresas.get(i).getNIT().equals(nit)) {
				empresa=empresas.get(i);
			}
		}
		return empresa;
	}

	public boolean U_Empresa() {
		
		return true;
	}

	public boolean D_Empresa(String nit) {
		try {
			boolean b = false;
			int f=-1;
			for (int i = 0; i < empresas.size(); i++) {
				if (empresas.get(i).getNIT().equals(nit)) {
					b = true;
					f=i;
				}
			}
			if(b) {
				empresas.remove(f);
			}
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean C_Trabajador(Trabajador trabajador) {
		try {
			boolean b = true;
			for (int i = 0; i < trabajadores.size(); i++) {
				if (trabajadores.get(i).getLogin().equals(trabajador.getLogin())) {
					b = false;
				}
			}
			if(b) {
				trabajadores.add(trabajador);
			}
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	public Trabajador R_Trabajador(String usuario) {
		Trabajador trabajador=null;
		for (int i = 0; i < trabajadores.size(); i++) {
			if (trabajadores.get(i).getLogin().equals(usuario)) {
				trabajador=trabajadores.get(i);
			}
		}
		return trabajador;
	}

	public boolean U_Trabajador() {
		
		return true;
	}

	public boolean D_Trabajador(String usuario) {
		try {
			boolean b = false;
			int indice=-1;
			for (int i = 0; i < trabajadores.size(); i++) {
				if (trabajadores.get(i).getLogin().equals(usuario)) {
					b = true;
					indice=i;
				}
			}
			if(b) {
				trabajadores.remove(indice);
			}
			return b;
		} catch (Exception e) {
			return false;
		}
	}

}
