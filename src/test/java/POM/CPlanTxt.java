package POM;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class CPlanTxt {

	Base b=Base.getInstance();
	CLogin clogin=CLogin.getInstance();	
	public ExtentReports extent;
	ExtentTest test;
	By User = By.id("User");
	By Pass = By.id("Password");
	By Loggin = By.xpath("//input[@value='Iniciar Sesi�n']");
	By Modulo = By.className("zmdi-calendar");
	By PorTxt = By.xpath("//a[@id=1 and (contains(text(),'Carga de Citas') or contains(text(),'plan'))]");
	By SelecTxt = By.id("uploadFileName");
	By CargaTxt = By.id("file_upload_button");
	String ruta, usuario, contrase�a, url, empresa;

	public void Login() {
		try {
			clogin.login(usuario,contrase�a,url,empresa);
			selmod();
		} catch (Exception e) {
			System.out.println("Error en Planeaci�n MLogin: " + e);
		}
	}

	public void selmod() {
		try {
			test=b.createtest("CP:Cargar Planeacion de :",empresa);
			b.wait(Modulo);
			b.clic(Modulo);
			cargatxt();
		} catch (Exception e) {
			System.out.println("Error Planeaci�n Mselmod: " + e);
		}
	}

	public void cargatxt() {
		try {
			ExtentTest seltxt = test.createNode("Seleccion de archivo txt para planeacion");
			if (ruta.isEmpty()) {
				seltxt.fail("No se cargo un archivo txt.");
			} else {
				b.wait(PorTxt);
				b.clic(PorTxt);
				b.isEnabled(SelecTxt);
				b.write(ruta, SelecTxt);
				b.clic(CargaTxt);
				seltxt.pass("Se cargo el archivo txt: " + ruta);		
			}
			asser();
		} catch (Exception e) {
			System.out.println("Error Planeaci�n Mcargartxt: " + e);
		}
	}

	public void asser() {
		assertTrue(!ruta.isEmpty());
	}

	public void CargarTxt(String usuario, String contrase�a, String url, String ruta, String empresa) {
		try {
			this.ruta = ruta;
			this.usuario = usuario;
			this.contrase�a = contrase�a;
			this.empresa = empresa;
			this.url = url;
			Login();
		} catch (Exception e) {
			System.out.println("Error en clase CPlaneaci�n: " + e);
		}
	}
	
	private static CPlanTxt plantxt;

	private CPlanTxt() { 
		this.extent=b.createreport();//crea reporte  
	}

	public static CPlanTxt getInstance() {
       if (plantxt == null) {
    	   plantxt=new CPlanTxt();
		}
		return plantxt;
	}

}
