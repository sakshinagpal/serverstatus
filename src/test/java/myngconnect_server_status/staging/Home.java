package myngconnect_server_status.staging;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.compro.automation.utils.CSVHandler;

public class Home {
	CSVHandler login_details = null;
    public void login(RemoteWebDriver driver) throws Exception {
        String baseurl = "http://s-www.myngconnect.com";
        synchronized (driver){driver.wait(5000);}
        driver.get(baseurl + "/login/teacher/login.spr");
	    driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("testteacher2@wcg.com");
		driver.findElement(By.id("passwordField")).clear();
	    driver.findElement(By.id("passwordField")).sendKeys("password");
	    driver.findElement(By.id("imgLogin")).click();
 }
}
