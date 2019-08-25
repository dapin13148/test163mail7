package package02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import log4j.LoggerControler;
import log4j.LoggerTest;
import package01.Login2;

public class TestSendMail3 {
	WebDriver driver=null;
	final static LoggerControler log= LoggerControler.getLogger(LoggerTest.class);
	@BeforeClass
	public void openChrome(){
		System.setProperty("webdriver.chrome.driver", "D:\\工具插件\\chromedriver.exe");
		driver=new ChromeDriver();
   
		driver.get("https://mail.163.com");
		log.info("自动测试网页打开了");
	}
	

	@Test
	public void main(){
		Login2 login2=new Login2();
		login2.login(driver,"dapin13148","abc123789");
		
		//点击发信按钮，注意不是这两个字所在的span，且先等它加载完成
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#dvNavTop>ul>li+li")));
		//虽然这两个选择器都表示兄弟选择器，但是+选择器则表示某元素后相邻的兄弟元素，也就是紧挨着的，是单个的。
		//而~选择器则表示某元素后所有同级的指定元素，强调所有的。
		driver.findElement(By.cssSelector("#dvNavTop>ul>li+li")).click();	
		//成功：driver.findElement(By.cssSelector("#dvNavTop>ul>li:nth-child(2)")).click();
		//成功：driver.findElement(By.cssSelector(".qd0+ul>li+li")).click();
		//成功：driver.findElement(By.cssSelector(".qd0+ul>li:nth-child(2)")).click();

		//收件人，难点。。。。。。。输出一个还是多个有区别，注意小的都有两个，还有一个大框，是小的中input那个
		driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("dapin13148@qq.com");
		//成功：driver.findElement(By.xpath("//*[@aria-label='收件人地址输入框，请输入邮件地址，多人时地址请以分号隔开']")).sendKeys("dapin13148@qq.com");

		//标题，明显有个class，但是重复不能用
		driver.findElement(By.cssSelector("[aria-label='邮件主题输入框，请输入邮件主题']>input")).sendKeys("标题哦");;
		
		//附加，难点。。。。。。。。。按钮附加有几个div，不容易找到，但肯定代码也是挨着的，input可能性大。这里移动和点击不容易找到
		driver.findElement(By.className("O0")).sendKeys("D:\\学习总结\\我的.txt");
		
		//控制权给正文iframe
		WebElement frame=driver.findElement(By.cssSelector(".APP-editor-iframe"));	
		driver.switchTo().frame(frame);
		//右键点击“检查”，或者输入内容点击右键“检查”算是一个方法。
		//iframe内复制到xpath，在iframe看是否重复就行，粘贴搜索是在这个网页搜索全部，没关系，可用
		//其实firepath可用做的，chromeF12都可以，特别是ifarme，在ctrl+f输入框上面有显示路径
		//driver.findElement(By.xpath("/html/body")).sendKeys("内容哦！");
		//失败：这个重复了driver.findElement(By.cssSelector("[title='一次可发送2000张照片，600首MP3，一部高清电影']")).sendKeys("标题哦");


		driver.switchTo().defaultContent();
		//发送，不是a标签，是span标签，不用linkText。css的文本是怎样的呢？
		driver.findElement(By.xpath("//*[text()='发送']")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='发送成功']")));

		//a文本，h文本
		Boolean info=driver.findElement(By.xpath(".//*[text()='发送成功']")).isDisplayed();
		Assert.assertTrue(info);
		log.warning("发送邮件成功。");
	}
}
