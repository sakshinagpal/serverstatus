package myngconnect_server_status.staging;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.compro.automation.core.*;
import com.compro.automation.utils.CSVHandler;
import com.compro.automation.utils.Screenshot;

class global
{
	public static String global_check = "false";
	public static String svn="false";
	String resolution = SetupDriver.resolutionCategory;
}

@RunWith(Parameterized.class)
	public class Test_Health_Check_STG extends global{ 
	String s = "fasle";  
	private String testEnv;
	private RemoteWebDriver driver = null;
	public Test_Health_Check_STG(String testEnv){
		this.testEnv = testEnv;
	  }
		 	
	  @Parameters
	   public static Collection<Object[]> data() throws Exception {
		   return (new TestEnvironement()).getEnvironment();
   }
	
	@Before
	public void setUp() {
		driver = TestRun.init(testEnv);
	}
		
	@After
	public void tearDown() throws Exception {
		TestRun.stop(driver);
	}

//Take Base Screenshot
@Ignore 
public void Test_Health_0_BaseScreenShot()throws Exception{
System.out.println("In @Test");
Health_check h = new Health_check();
CSVHandler login_details = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String baseurl0_name = login_details.getElementXpath("baseurl0_name");
h.health_login(driver);
WebElement ele = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div"));
//Make server CSV driven
Screenshot.takeElementScreenshot(driver, ele, ele.getLocation(), "base_health_"+baseurl0_name);
System.out.println("End of @test");
}

//Compare Base Screenshot
@Test
public void Test_Health_1_CompareScreenshot()throws Exception{
System.out.println("Screenshot Compare");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler login_details = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String baseurl0_name = login_details.getElementXpath("baseurl0_name");
WebElement ele = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div"));
String result=null;
//Make server CSV driven
//driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
//synchronized (driver){driver.wait(10000);}
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String wowza = general.getElementXpath("wowza");
driver.findElement(By.xpath(wowza)).getText();
Screenshot.takeElementScreenshot(driver, ele, ele.getLocation(), "actual_health_"+baseurl0_name);
result = Screenshot.compareScreenshots("actual_health_"+baseurl0_name, "base_health_"+baseurl0_name);
if(result.equals("True"))
{
	global_check="true";
}
System.out.println(result);
TestAssertion.assertionEquals(driver, "true", global_check);
}

@Test
public void TestHealth_2_ContentRepository()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("Check Content Repository");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String content_repository = general.getElementXpath("content_repository");
text=driver.findElement(By.xpath(content_repository)).getText();
System.out.println("Check Content Repository : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test
public void TestHealth_3_SRI1()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("SRI 1");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String sri1 = general.getElementXpath("sri1");
text=driver.findElement(By.xpath(sri1)).getText();
System.out.println("SRI 1 : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test 
public void TestHealth_4_SRI2()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("SRI 2");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String sri2 = general.getElementXpath("sri2");
text=driver.findElement(By.xpath(sri2)).getText();
System.out.println("SRI 2 : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test
public void TestHealth_5_SRI3()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("SRI 3");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String sri3 = general.getElementXpath("sri3");
text=driver.findElement(By.xpath(sri3)).getText();
System.out.println("SRI 3 : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test
public void TestHealth_7_Wowza()throws Exception{
System.out.println(global_check);
String server;
String text;
String check = "false";
CSVHandler general = null;
if(!global_check.equals("true"))
{	
System.out.println("Wowza");
Health_check h = new Health_check();
h.health_login(driver);
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
server = general.getElementXpath("baseurl0_name");
//synchronized (driver){driver.wait(10000);}
String wowza = general.getElementXpath("wowza");
text=driver.findElement(By.xpath(wowza)).getText();
System.out.println("Wowza : "+text);
if((!server.equals("staging")))
	{
	if(text.contains("Failed"))
		check="true";
	TestAssertion.assertionEquals(driver, "true", check);
	}
else if((server.equals("staging")))
	{	
	if(text.contains("Success"))
		check="true";
	TestAssertion.assertionEquals(driver, "true", check);
	}
}
}

@Test 
public void TestHealth_8_AuthorizationAPI()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("Authorization API");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String auth_api = general.getElementXpath("auth_api");
text=driver.findElement(By.xpath(auth_api)).getText();
System.out.println("Authorization API : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test 
public void TestHealth_9_Databse()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("Database");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String database = general.getElementXpath("database");
text=driver.findElement(By.xpath(database)).getText();
System.out.println("Database : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}

@Test 
public void TestHealth_10_Webassets()throws Exception{
System.out.println(global_check);
if(!global_check.equals("true"))
{	
System.out.println("Webassets");
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_staging.csv");
String text;
String check = "false";
//synchronized (driver){driver.wait(10000);}
String webassets = general.getElementXpath("webassets");
text=driver.findElement(By.xpath(webassets)).getText();
System.out.println("Webassets : "+text);
if(text.contains("Success"))
	check="true";
TestAssertion.assertionEquals(driver, "true", check);
}
}
}