package POM;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CPlaneacionTxt extends Base {

	ExtentReports extent;
    ExtentTest test;
    By User=By.id("User");
    By Pass=By.id("Password");
    By Loggin=By.xpath("//input[@value='Iniciar Sesión']");
    By Modulo= By.className("zmdi-calendar");
    By PorTxt= By.xpath("//a[@id=1 and (contains(text(),'Carga de Citas') or contains(text(),'plan'))]");
    By SelecTxt= By.id("uploadFileName");
    By CargaTxt=By.id("file_upload_button");
    String usuario,contraseña,url,ruta;
    WebDriver driver;
    
	public CPlaneacionTxt(WebDriver driver) {		
		 super(driver);
		 this.driver=driver;
	}
	
	public void CargarTxt(ExtentReports extent,ExtentTest test,String usuario,String contraseña,String url,String ruta) {
		this.usuario=usuario;
		this.contraseña=contraseña;
		this.url=url;
		this.extent=extent;
		this.test=test;
		this.ruta=ruta;
		try {
		go(url);
		waitimplic();
		wait(User);
		write(usuario,User); 
		write(contraseña,Pass);
		clic(Loggin);
//		waitimplic();
//		Thread.sleep(1000);
		wait(Modulo);
		clic(Modulo);
//		Thread.sleep(1000);
		wait(PorTxt);
		clic(PorTxt);
		isEnabled(SelecTxt);
		write(ruta,SelecTxt);
		clic(CargaTxt);
		assertTrue(!ruta.isEmpty());
		crearreporte();
		}catch(Exception e) {System.out.println("Error en clase CPlaneación: "+e);}
	}
	
	
	public void crearreporte() {
		try {   
		    test.log(Status.INFO, "Resultado de Prueba: ");// información
		  	ExtentTest curl = test.createNode("Url de Prueba"); // son escenarios o pasos
		  	if (url.isEmpty()) {
		  		Assert.fail("No se encontro una URL de Pruebas");
		  	} else {
		  		curl.pass("Url de Ambiente de Pruebas: " + url);
		  	}
		  	ExtentTest cusr = test.createNode("Registro de Usuario"); // son escenarios o pasos
			if (usuario.isEmpty()) {
				Assert.fail("El Campo usuario esta vacio.");
			} else { 
				cusr.pass("Se registro el usuario: " + usuario);
			} 
			ExtentTest cpass = test.createNode("Registro de Contrasena");
			if (contraseña.isEmpty()) {
				Assert.fail("El Campo Contrasena esta vacio.");
			} else {
				cpass.pass("Se registro contrasena: " + contraseña);
			}
			ExtentTest seltxt = test.createNode("Seleccion de archivo txt para planeacion");
			if (!ruta.isEmpty()) {
				seltxt.pass("Se cargo el archivo txt: " + ruta);
			} else {
				seltxt.fail("No se cargo un archivo txt.");
			}		   
		    }catch (Exception e) {System.out.println("Error en: createreporPlantxt Error:\n"+e);}
	}

}
