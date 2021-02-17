package POM;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Adicional.CapturarPantalla;
import Adicional.Parametros;

public class Base {

	Parametros param = Parametros.getInstance();
	CapturarPantalla c = new CapturarPantalla();// objeto para otra clase
	By logout =By.xpath("//*[contains(@href,'LogOut')]");
	private WebDriver driver;
	
	public Base(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebDriver chrome() {
		try {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/Driver/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		}catch(Exception e) {System.out.println("500 IN CHROME"+e);}
		return driver;
	}
	public WebDriver IExplorer() {
		try {
		System.setProperty("webdriver.ie.driver","./src/test/resources/Driver/IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();
		}catch(Exception e) {System.out.println("500 IN IE"+e);}
		return driver;
	}
	public WebDriver Mozilla() {
		try {
		System.setProperty("webdriver.gecko.driver","./src/test/resources/Driver/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		}catch(Exception e) {System.out.println("500 IN MOZ"+e);}
		return driver;
	}
	public WebElement findElement(By locator){
		return driver.findElement(locator);		
    }
	public Boolean isPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}
	
	public void write(String text,By locator) {
		driver.findElement(locator).sendKeys(text);
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void clic(By locator) {
		driver.findElement(locator).click();
	}
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}
	public void submit(By locator) {
		driver.findElement(locator).submit();
	}
	public void wait(By locator) {
	try {
		WebDriverWait load=new WebDriverWait(driver,10);
		load.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {System.out.println("No se encontro el elemento en el tiempo de espera");}
	}
	public Boolean isDisplayed(By locator) {
		try {
		return driver.findElement(locator).isDisplayed();
		}catch(org.openqa.selenium.NoSuchElementException e) {return false;}
	}
	public Boolean isEnabled(By locator) {
		try {
		return driver.findElement(locator).isEnabled();
		}catch(org.openqa.selenium.NoSuchElementException e) {return false;}
	}
	public void go(String url) {
		try {
		driver.get(url);
		}catch(Exception e) {System.out.println("Error en Base Mgo");}
	}
	public void waitimplic() {
    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	
	MediaEntityModelProvider evidencia=null;
	public void resultest(ITestResult result,ExtentTest test,WebDriver driver,String modulo) {//metodo para mostrar el resultado
		try {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel("El Resultado del Test:  " + result.getName() + " no fue exitoso!", ExtentColor.RED));
			evidencia=c.tomarcaptura(driver,modulo);
			test.fail("Evidencia: ", evidencia); 
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS,MarkupHelper.createLabel("Caso de prueba " + result.getName() + " Exitoso", ExtentColor.GREEN));
			evidencia=c.tomarcaptura(driver,modulo);
			test.pass("Evidencia: ", evidencia);
			wait(logout);
			clic(logout);
		}else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
		    MarkupHelper.createLabel("Caso de prueba " + result.getName() + "fue saltado", ExtentColor.YELLOW));
			test.pass("Evidencia: ", evidencia);
		}
		test.log(Status.INFO, "A continucion se muestran los pasos para la prueba: ");	
		}catch(Exception e) {System.out.println("Error en Base, Metodo resultest: "+e);}
	}
	
	
}
