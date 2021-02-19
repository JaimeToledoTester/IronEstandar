package POM;


import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class CLogin {
	Base b=Base.getInstance();	
    By User=By.id("User");
    By Pass=By.id("Password");
    By Loggin=By.xpath("//input[@value='Iniciar Sesión']");
    By asser=By.xpath("//*[contains(@href,'LogOut')]"); 
    String usuario,contraseña,url;
    public ExtentReports extent;
    ExtentTest test;
    public WebDriver driver;
     

   public void Url() {
	   ExtentTest curl = test.createNode("Url de Prueba"); // son escenarios o pasos
	  	if (url.isEmpty()) {
	  		Assert.fail("No se encontro una URL de Pruebas");
	  	} else {
	 	    b.go(url); 
	 	    User();
	  		curl.pass("Url de Ambiente de Pruebas: " + url);
	  	}   
   }
   public void User() {
	   ExtentTest cusr = test.createNode("Registro de Usuario"); // son escenarios o pasos
		if (usuario.isEmpty()) {
			Assert.fail("El Campo usuario esta vacio.");
		} else { 
			cusr.pass("Se registro el usuario: " + usuario);
			b.wait(User);
		    b.write(usuario,User); 	
		    Password();
		} 
 
   }
    
   public void Password() {
	   ExtentTest cpass = test.createNode("Registro de Contrasena");
		if (contraseña.isEmpty()) {
			Assert.fail("El Campo Contrasena esta vacio.");
		} else {
			cpass.pass("Se registro contrasena: " + contraseña);
			b.wait(Pass);
			b.write(contraseña,Pass);
		}
		asser();
   }
   
   public void asser() {
	    b.submit(Loggin);
	    b.wait(asser);
	    assertTrue(b.isDisplayed(asser));    
  }
   
   public void login(String usuario,String contraseña,String url,String empresa)   {
   try {
   this.usuario=usuario;
   this.contraseña=contraseña;
   this.url=url;
   this.test=b.createtest("CP: Validar Login de ",empresa);
   Url();
   }catch(Exception e) {System.out.println("Error en CLogin, Metodo Login"+e);}
   }
   
   private static CLogin clog;

	private CLogin() {

	}

	public static CLogin getInstance() {
       if (clog == null) {
			clog=new CLogin();
		}
		return clog;
	}
}
