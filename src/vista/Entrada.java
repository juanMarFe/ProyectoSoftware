package vista;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import modelo.Agrupacion;
import modelo.Empresa;
import modelo.FolderProxy;
import modelo.Oferta;
import modelo.PsicologoAdapter;
import modelo.Singleton;
import modelo.SueldoMensual;
import modelo.Trabajador;
import modelo.Usuario;
import modelo.UsuarioFactory;

public class Entrada {

	public static void main(String[] args) {
//		Singleton s= Singleton.crearInstaSingleton();
//		int key1=s.C_Empresa(new Empresa("1", "1", "1", "1", "1"));
//		int key2=s.C_Empresa(new Empresa("2", "2", "2", "2", "2"));
//		int key3=s.C_Empresa(new Empresa("4", "4", "4", "4", "4"));
//		int key4=s.C_Empresa(new Empresa("5", "5", "5", "5", "5"));
//		
//		System.out.println(s.R_Empresa(key1));
		/*
		 * //TESTING ADAPTER Usuario empresa = new Empresa("apple", "1234", "1",
		 * "Apple","Cra 100"); System.out.println(empresa.checkLogin("apple", "123"));
		 * System.out.println(empresa.checkLogin("apple", "1234"));
		 * 
		 * 
		 * //TESTING COMPOSITE
		 * 
		 * Agrupacion oferta1 = new Oferta("1A", "Software Engineer","Sin sueldo");
		 * Agrupacion oferta2 = new Oferta("2B", "Business Analyst","Sin sueldo");
		 * Agrupacion oferta3 = new Oferta("3C", "SCRUM Master","Sin sueldo");
		 * 
		 * Empresa empresa1= new Empresa("emp1", "1234","22.33.22","Empresa 1","Cra 1");
		 * Empresa empresa2= new Empresa("emp2",
		 * "1234","22.33.44","Empresa 2","Calle 2"); Empresa empresa3= new
		 * Empresa("emp3", "1234","11.33.55","Empresa 3","Cra 15");
		 * 
		 * empresa1.addAgrupacion(oferta1); empresa1.addAgrupacion(oferta2);
		 * 
		 * empresa3.addAgrupacion(oferta3); empresa3.addAgrupacion(oferta2);
		 * 
		 * empresa2.addAgrupacion(empresa1); empresa2.addAgrupacion(empresa3);
		 * 
		 * 
		 * empresa1.verDatos(); System.out.println();
		 * 
		 * empresa2.verDatos(); System.out.println();
		 * 
		 * empresa3.verDatos(); System.out.println();
		 * 
		 * 
		 * //TESTING DECORATOR
		 * 
		 * String codigo = "1"; String cargo = "SE"; String descripcion= "Retirate";
		 * String sueldo = "500000";
		 * 
		 * Agrupacion oferta4 = new Oferta(codigo,cargo,descripcion);
		 * System.out.println(oferta4.verDatos());
		 * 
		 * Agrupacion oferta5 = new SueldoMensual(new
		 * Oferta(codigo,cargo,descripcion),sueldo);
		 * System.out.println(oferta5.verDatos());
		 * 
		 * 
		 * //TESTING PROXY
		 * 
		 * Usuario rightTrabajador = new Trabajador("arpit", "arpit"); Usuario
		 * wrongTrabajador = new Trabajador("Gabriel", "123"); Empresa rightEmpresa= new
		 * Empresa("emp1", "1234","22.33.22","Empresa 1","Cra 1"); Empresa wrongEmpresa=
		 * new Empresa("arpit", "arpit","22.33.22","Empresa 1","Cra 1");
		 * 
		 * FolderProxy folderProxyTrabajadorRight= new FolderProxy(rightTrabajador);
		 * System.out.println("Username and password are correct:");
		 * folderProxyTrabajadorRight.performOperation(); System.out.println(
		 * "********************************************************************");
		 * 
		 * FolderProxy folderProxyTrabajadorWrong= new FolderProxy(wrongTrabajador);
		 * System.out.println("Username and password are incorrect");
		 * folderProxyTrabajadorWrong.performOperation(); System.out.println(
		 * "********************************************************************");
		 * 
		 * FolderProxy folderProxyEmpresaRight= new FolderProxy(rightEmpresa);
		 * System.out.println("Username and password are correct:");
		 * folderProxyEmpresaRight.performOperation(); System.out.println(
		 * "********************************************************************");
		 * 
		 * FolderProxy folderProxyEmpresaWrong= new FolderProxy(wrongEmpresa);
		 * System.out.println("Username and password are incorrect");
		 * folderProxyEmpresaWrong.performOperation(); System.out.println(
		 * "********************************************************************");
		 * 
		 * 
		 * //TESTING FLYWEIGHT
		 * 
		 * UsuarioFactory factory = new UsuarioFactory(); factory.saveUsuario(0, new
		 * Empresa("emp1", "1234","22.33.22","Empresa 1","Cra 1"));
		 * factory.saveUsuario(1, new Trabajador("Gabriel", "123"));
		 * 
		 * Usuario a = factory.getUsuario(0); Usuario b = factory.getUsuario(1);
		 * 
		 * System.out.println("El usuario es una "+a.getTipoUsuario()+" de nombre "+a.
		 * getLogin() );
		 * System.out.println("El usuario es una "+b.getTipoUsuario()+" de nombre "+b.
		 * getLogin() );
		 * 
		 */
//		Singleton s = Singleton.crearInstaSingleton();
//		s.C_Empresa(new Empresa("Gabo", "1", "1234", "1", "1"));
//		
//		FolderProxy fp = new FolderProxy("Gabo", "1");
//		System.out.println(fp.performOperation());
		
//		s.C_AgrupacionOferta(new Oferta("1", "panadero", "hola"), "1234");
//		s.C_AgrupacionOferta(new Oferta("2", "constructor", "hola"), "1234");
//		System.out.println(s.R_TodasLasOfertas("1234"));
//		System.out.println(s.R_UnicaOferta("2", "1234"));
//		s.D_Oferta("1", "1234");
		
		
		Singleton s = Singleton.crearInstaSingleton();
		s.C_Trabajador(new Trabajador("1", "1", "1", "1"));
		FolderProxy fp= new FolderProxy("Admin","Admin123");
		System.out.println(s.C_Empresa(new Empresa("Santi", "1", "446", "1", "1"),fp.performOperation()));
		System.out.println(s.C_Empresa(new Empresa("Santi1", "123", "2312414", "sdfasdf", "sdfaaf"),fp.performOperation()));
		//System.out.println(s.U_Trabajador(null, new Trabajador("2", "1", "1", "1"), null));
		System.out.println(s.U_Empresa("Santi", new Empresa("Santi2","123","2341252","sdafsdf","sdafsdf"), fp.performOperation()));
		System.out.println(s.R_Empresa("San"));

		
		
//		
//		FolderProxy fp = new FolderProxy(new Empresa("1", "1", "1234", "1", "1"));
//		System.out.println(s.R_Empresa(fp.performOperation()).getLogin());
////		s.D_Empresa("1234");
////		System.out.println(s.R_Empresa(fp.performOperation()));
//		s.U_Empresa("1234", new Empresa("Santi", "1", "446", "1", "1"));
//		System.out.println(s.R_Empresa(fp.performOperation()));
//		System.out.println(s.R_Empresa("446").getLogin());
//		System.out.println(s.R_Empresa("446").getId());
		
		
		
	}
}
