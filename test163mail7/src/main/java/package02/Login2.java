package package02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import package02.Login2;

public class Login2 {
	WebDriver driver;
	
	//@BeforeMethod
	public void openchrome(){
		System.setProperty("webdriver.chrome.driver","D:\\工具插件\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://mail.163.com/");
	}
	
	
	public static void login(WebDriver driver,String username,String password){
		//点击切换到密码登录
		driver.findElement(By.id("switchAccountLogin")).click();
		//减号还是空格原因导致null所以失败driver.findElement(By.className("u-login-entry u-163-login-entry")).click();
		//为什么不行driver.findElement(By.linkText("密码登录")).click();
		//为什么不行，注意内部单引号，xpath其下的第几个driver.findElement(By.xpath(".//*[@id='commonOperation']/a[1]")).click();
		//去掉前面点号也不行driver.findElement(By.xpath("//*[@id='commonOperation']/a[1]")).click();
		//driver.findElement(By.xpath(".//*[@id='switchAccountLogin']")).click();
		//driver.findElement(By.cssSelector("#switchAccountLogin")).click();
		
		driver.switchTo().frame(0);	
		driver.findElement(By.cssSelector("#account-box>div>input")).sendKeys(username);
		driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("#dologin")).click();
	}
	
	@Test
	public void loginSuccess(){
/*		driver.findElement(By.id("switchAccountLogin")).click();
		driver.switchTo().frame(0);	
		driver.findElement(By.cssSelector("#account-box>div>input")).sendKeys("dapin13148");
		driver.findElement(By.cssSelector("[name='password']")).sendKeys("abc123789");
		driver.findElement(By.cssSelector("#dologin")).click();*/
		Login2.login(driver, "dapin13148", "abc123789");
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("退出")));
		
		String text=driver.findElement(By.linkText("退出")).getText();
		Assert.assertEquals(text, "退出");
		
		driver.findElement(By.linkText("退出")).click();  //没有从外层方法传入参数，直接在这里加入的
		
	}
	

	@Test(dependsOnMethods={"loginSuccess"})
	public void loginFalse(){
/*		driver.findElement(By.id("switchAccountLogin")).click();
		driver.switchTo().frame(0);	
		driver.findElement(By.cssSelector("#account-box>div>input")).sendKeys("dapin13148");
		driver.findElement(By.cssSelector("[name='password']")).sendKeys("111abc123789");
		driver.findElement(By.cssSelector("#dologin")).click();*/
		Login2.login(driver, "dapin13148", "111abc123789");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ferrorhead")));
		
		String text=driver.findElement(By.className("ferrorhead")).getText();
		System.out.println("text结果是:"+text);
		Boolean xx=text.equals("请先进行验证");
		System.out.println("xx:"+xx);
		Boolean yy=text.equals("帐号或密码错误");
		System.out.println("yy:"+yy);
		
		Boolean result=(text.equals("请先进行验证") | text.equals("帐号或密码错误"));
		System.out.println("result结果是:"+result);
		//Boolean x="ab"=="ab";
		//Assert.assertEquals(text, "帐号或密码错误");
		//Assert.assertEquals(text, "请先进行验证");
		//Assert.assertEquals(text,"帐号或密码错误", "请先进行验证");这个理解错误
		//Assert.assertEquals("text","帐号或密码错误", "哈哈，提示信息又换了。。。。。。。。。。。。。");
		Assert.assertTrue(result);
	}
	
	
	//@AfterMethod
	private void CloseChrome() {
		//默认打开的data:,没有关闭，所以浏览器也没有关闭，只关闭了该选项卡。？？？？？
		//driver.close();
		driver.quit();
	}
}
