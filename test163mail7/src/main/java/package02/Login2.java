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
		System.setProperty("webdriver.chrome.driver","D:\\���߲��\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://mail.163.com/");
	}
	
	
	public static void login(WebDriver driver,String username,String password){
		//����л��������¼
		driver.findElement(By.id("switchAccountLogin")).click();
		//���Ż��ǿո�ԭ����null����ʧ��driver.findElement(By.className("u-login-entry u-163-login-entry")).click();
		//Ϊʲô����driver.findElement(By.linkText("�����¼")).click();
		//Ϊʲô���У�ע���ڲ������ţ�xpath���µĵڼ���driver.findElement(By.xpath(".//*[@id='commonOperation']/a[1]")).click();
		//ȥ��ǰ����Ҳ����driver.findElement(By.xpath("//*[@id='commonOperation']/a[1]")).click();
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("�˳�")));
		
		String text=driver.findElement(By.linkText("�˳�")).getText();
		Assert.assertEquals(text, "�˳�");
		
		driver.findElement(By.linkText("�˳�")).click();  //û�д���㷽�����������ֱ������������
		
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
		System.out.println("text�����:"+text);
		Boolean xx=text.equals("���Ƚ�����֤");
		System.out.println("xx:"+xx);
		Boolean yy=text.equals("�ʺŻ��������");
		System.out.println("yy:"+yy);
		
		Boolean result=(text.equals("���Ƚ�����֤") | text.equals("�ʺŻ��������"));
		System.out.println("result�����:"+result);
		//Boolean x="ab"=="ab";
		//Assert.assertEquals(text, "�ʺŻ��������");
		//Assert.assertEquals(text, "���Ƚ�����֤");
		//Assert.assertEquals(text,"�ʺŻ��������", "���Ƚ�����֤");���������
		//Assert.assertEquals("text","�ʺŻ��������", "��������ʾ��Ϣ�ֻ��ˡ�������������������������");
		Assert.assertTrue(result);
	}
	
	
	//@AfterMethod
	private void CloseChrome() {
		//Ĭ�ϴ򿪵�data:,û�йرգ����������Ҳû�йرգ�ֻ�ر��˸�ѡ�������������
		//driver.close();
		driver.quit();
	}
}
