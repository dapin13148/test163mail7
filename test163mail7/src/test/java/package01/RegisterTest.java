package package01;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by lenovo on 2016/10/11.
 */
public class RegisterTest {
    WebDriver driver;
    
    @BeforeMethod
    public void openchrome(){

        System.setProperty("webdriver.chrome.driver","D:\\工具插件\\chromedriver.exe");
        driver=new ChromeDriver();
        try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.get("http://mail.163.com");
        String title=driver.getTitle();
        System.out.println(title);
//      Assert.assertEquals(title,"新浪首页");
    }
    
    @Test
    public void registerTest(){
    	driver.findElement(By.xpath("//*[@id='commonOperation']/a[2]")).click();
    	System.out.println("RegisterTest.registerTest()");
    	
    	String curHandle=driver.getWindowHandle();
    	Set<String> handlesSet=driver.getWindowHandles();
    	for(String handle:handlesSet){
    		if(!handle.equals(curHandle)){
    			driver.switchTo().window(handle);
    		}
    	}
    	
    	String time=String.valueOf(System.currentTimeMillis()/100);
    	driver.findElement(By.id("nameIpt")).sendKeys("user"+time);
    	driver.findElement(By.id("mainPwdIpt")).sendKeys("abc123789");
    	driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("abc123789");
    	
    	driver.findElement(By.id("vcodeIpt")).sendKeys("user"+time);
    	driver.findElement(By.id("mainMobileIpt")).sendKeys(time);
    	
    	driver.findElement(By.id("mainAcceptIpt")).click();
    	driver.findElement(By.id("mainRegA")).click();
    	
    	WebDriverWait wait=new WebDriverWait(driver,5);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("txt-err")));
    	String info=driver.findElement(By.className("txt-err")).getText();
    	System.out.println("++++++++++info:"+info);
    	Assert.assertEquals(info,"  验证码不正确，请重新填写");
    	



    }

   
/*    @AfterTest  //没有建testng.xml时方法执行后也执行了这个
    public void closedchrome()throws InterruptedException{
        Thread.sleep(5000);
        driver.quit();
    }*/
}
