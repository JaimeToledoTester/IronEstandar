package Test;

import org.testng.annotations.BeforeMethod;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Adicional.LeerJson;
import Adicional.Mail2;
import POM.Base;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;

public class Global {
	Base b=Base.getInstance();
	LeerJson js = LeerJson.getInstance();
	Mail2 m= new Mail2();
	
  @BeforeMethod
  public void beforeMethod() throws FileNotFoundException, IOException, ParseException {
	  try {
	  js.leerjson();
	  }catch(Exception e) {System.out.println("Error en Global,leerjson: "+e);}

  }

  @Test
  public void testeo() {
	  try {
	  assert(true);
	  }catch(Exception e) {System.out.println("Error en Global, Mtesteo: "+e);}
  }


  @AfterSuite
  public void afterSuite() {
	  try {
//    b.driver.quit();
	  m.EnviarEmail();
	  }catch(Exception e) {System.out.println("Error en Global, enviarmail: "+e);}

	  }

}
