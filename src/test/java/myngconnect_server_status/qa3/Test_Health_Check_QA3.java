package myngconnect_server_status.qa3;

import java.text.SimpleDateFormat;
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
import java.util.Date;

class global
{
	public static String content_repository_check = "false";
	public static String sri1_check = "false";
	public static String sri2_check = "false";
	public static String sri3_check = "false";
	public static String webassets_check = "false";
	public static String wowza_check = "false";
	public static String auth_api_check = "false";
	public static String database_check = "false";
	
	String resolution = SetupDriver.resolutionCategory;
}

@RunWith(Parameterized.class)
	public class Test_Health_Check_QA3 extends global{ 
	String s = "fasle";  
	private String testEnv;
	private RemoteWebDriver driver = null;
	public Test_Health_Check_QA3(String testEnv){
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
Health_check h = new Health_check();
CSVHandler login_details = new CSVHandler("src/test/resources/login_health_check_qa3.csv");
String baseurl0_name = login_details.getElementXpath("baseurl0_name");
h.health_login(driver);
WebElement ele = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div"));
//Make server CSV driven
Screenshot.takeElementScreenshot(driver, ele, ele.getLocation(), "base_health_"+baseurl0_name);
}

//Capture and Compare(Ignored) Screenshot
@Test
public void Test_Health_1_CompareScreenshot()throws Exception{
Health_check h = new Health_check();
h.health_login(driver);
CSVHandler login_details = new CSVHandler("src/test/resources/login_health_check_qa3.csv");
String baseurl0_name = login_details.getElementXpath("baseurl0_name");
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
System.out.println(sdf.format(date));
String time;
String text;
String str = sdf.format(date);
System.out.println("STR:"+str);
time = str.substring(0,2);
time +="-"+ str.substring(3,5);
System.out.println("TIME:"+time);
synchronized (driver) {driver.wait(15000);}
WebElement ele = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div"));
String result=null;
String delivery_mode=null;
CSVHandler general = null;
general = new CSVHandler("src/test/resources/login_health_check_qa3.csv");

//Make server CSV driven
driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
synchronized (driver){driver.wait(20000);}

//Check the delivery mode
String content_repository_delivery_mode = general.getElementXpath("content_repository_delivery_mode");
text=driver.findElement(By.xpath(content_repository_delivery_mode)).getText();
System.out.println(text);
if(text.contains("Apache"))
	delivery_mode="apache";
else delivery_mode="svn";

//Content Repository
if(delivery_mode.equals("apache"))
{
	String content_repository_apache = general.getElementXpath("content_repository_apache");
	text=driver.findElement(By.xpath(content_repository_apache)).getText();
}
else 
{
	String content_repository = general.getElementXpath("content_repository");
	text=driver.findElement(By.xpath(content_repository)).getText();
}
	if(text.contains("Success"))
	content_repository_check="true";

text=null;

//SRI1
if(delivery_mode.equals("apache"))
{
	String sri1_apache = general.getElementXpath("sri1_apache");
	text=driver.findElement(By.xpath(sri1_apache)).getText();
}
else 
{
	String sri1 = general.getElementXpath("sri1");
	text=driver.findElement(By.xpath(sri1)).getText();
}
if(text.contains("Success"))
	sri1_check="true";

text=null;

//SRI2
if(delivery_mode.equals("apache"))
{
	String sri2_apache = general.getElementXpath("sri2_apache");
	text=driver.findElement(By.xpath(sri2_apache)).getText();
}
else 
{
	String sri2 = general.getElementXpath("sri2");
	text=driver.findElement(By.xpath(sri2)).getText();
}
if(text.contains("Success"))
	sri2_check="true";

text=null;

//SRI3
if(delivery_mode.equals("apache"))
{
	String sri3_apache = general.getElementXpath("sri3_apache");
	text=driver.findElement(By.xpath(sri3_apache)).getText();
}
else 
{
	String sri3 = general.getElementXpath("sri3");
	text=driver.findElement(By.xpath(sri3)).getText();
}
if(text.contains("Success"))
	sri3_check="true";

text=null;

//Webassets
String webassets = general.getElementXpath("webassets");
text=driver.findElement(By.xpath(webassets)).getText();
if(text.contains("Success"))
	webassets_check="true";

text=null;

//Authorization API
if(delivery_mode.equals("apache"))
{
	String auth_api_apache = general.getElementXpath("auth_api_apache");
	text=driver.findElement(By.xpath(auth_api_apache)).getText();
}
else 
{
	String auth_api = general.getElementXpath("auth_api");
	text=driver.findElement(By.xpath(auth_api)).getText();
}
if(text.contains("Success"))
	auth_api_check="true";

text=null;

//Database
String database = general.getElementXpath("database");
text=driver.findElement(By.xpath(database)).getText();
if(text.contains("Success"))
	database_check="true";

text=null;

//Wowza
if(delivery_mode.equals("apache"))
{
	String wowza_apache = general.getElementXpath("wowza_apache");
	text=driver.findElement(By.xpath(wowza_apache)).getText();
}
else 
{
	String wowza = general.getElementXpath("wowza");
	text=driver.findElement(By.xpath(wowza)).getText();
}
if(text.contains("Success"))
	wowza_check="true";

Screenshot.takeElementScreenshot(driver, ele, ele.getLocation(), time + "_"+"actual_health_"+baseurl0_name);

/* Compare Screenshot
result = Screenshot.compareScreenshots("actual_health_"+baseurl0_name, "base_health_"+baseurl0_name);
if(result.equals("True"))
{
	global_check="true";
}
System.out.println(result);
TestAssertion.assertionEquals(driver, "true", global_check);
*/
}

@Test
public void TestHealth_2_ContentRepository()throws Exception{

	System.out.println("HERE");
TestAssertion.assertionEquals(driver, "true", content_repository_check);
}

@Test
public void TestHealth_3_SRI1()throws Exception{
TestAssertion.assertionEquals(driver, "true", sri1_check);
}

@Test
public void TestHealth_4_SRI2()throws Exception{
TestAssertion.assertionEquals(driver, "true", sri2_check);
}

@Test
public void TestHealth_5_SRI3()throws Exception{
TestAssertion.assertionEquals(driver, "true", sri3_check);
}

@Test
public void TestHealth_6_Webassets()throws Exception{
TestAssertion.assertionEquals(driver, "true", webassets_check);
}

@Test 
public void TestHealth_7_AuthorizationAPI()throws Exception{
TestAssertion.assertionEquals(driver, "true", auth_api_check);
}

@Test
public void TestHealth_8_Databse()throws Exception{
TestAssertion.assertionEquals(driver, "true", database_check);
}

@Test
public void TestHealth_09_Wowza()throws Exception{
TestAssertion.assertionEquals(driver, "false", wowza_check);
}
}