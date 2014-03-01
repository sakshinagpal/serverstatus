package myngconnect_server_status.staging;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.compro.automation.core.*;

@RunWith(Parameterized.class)
public class Test_Existing_Credentials_Check_STG{ 
	 
	private String testEnv;
	private RemoteWebDriver driver = null;
	
	public Test_Existing_Credentials_Check_STG(String testEnv){
		this.testEnv = testEnv;
	  }
		 	
	  @Parameters
	   public static Collection<Object[]> data() throws Exception {
		   return (new TestEnvironement()).getEnvironment();
   }
	
	@Before
	public void setUp() {
		driver = TestRun.init(testEnv);
		System.out.println("check editing");
	}
		
	@After
	public void tearDown() throws Exception {
		TestRun.stop(driver);
	}
//----------------------------------------------------------------------------------------------------
@Test
public void TestExistingCredentials()throws Exception{
System.out.println("In @Test");
String flag="false";
Home h = new Home();
h.login(driver);
//synchronized (driver){driver.wait(10000);}
String url = driver.getCurrentUrl();
System.out.println(url);
if(url.contains("ecosystem") || url.contains("Ecosystem"))
	flag="true";
System.out.println("flag : "+flag);
TestAssertion.assertionEquals(driver, "true", flag);
System.out.println("End of @test");
}
}