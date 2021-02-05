package Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import Adicional.LeerJson;
import Adicional.Mail2;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Global {
	
	LeerJson js = LeerJson.getInstance();
	Mail2 m= new Mail2();
	
  @BeforeMethod
  public void beforeMethod() throws FileNotFoundException, IOException, ParseException {
	  js.leerjson();
  }

  @AfterMethod
  public void afterMethod() {
	  
  }

  @BeforeClass
  public void beforeClass() {
	 
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @Test
  public void testeo() {
	  assert(true);
  }
  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
	  m.EnviarEmail();
  }

}
