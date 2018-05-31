package com.testNG.retry.Automation.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppTest {
	public  static final String SELENIUMCN_LINK = "div#content_left div.result:nth-child(1) h3 a";
	public  static final String SELENIUMCN_LINKs = "div#content_left div.result h3 a";
	WebDriver driver = new ChromeDriver();	
	
	@BeforeMethod
	public void BeforeMethod() {
        //Open baidu homePage
		String URL = "https://www.baidu.com";	
		driver.get(URL);
	}
	
	@Test(groups = "SMOKE")
	public void testCase1() throws Exception {
		//输入selenium中文论坛
		WebElement query = driver.findElement(By.name("wd"));
		query.sendKeys("selenium中文论坛");
		WebElement btn = driver.findElement(By.id("su"));
//		WebElement btn = driver.findElement(By.id("找不到我"));
		btn.click();//点击搜索
		Thread.sleep(5000);		
		WebElement link = driver.findElement(By.cssSelector(SELENIUMCN_LINK));
		link.click();
		Thread.sleep(5000);		
		driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);		
		String expectedTitle ="Selenium - 开源中国社区";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,expectedTitle);//断言
    }
	
	@Test(groups = "SMOKE")
	public void testCase2() throws Exception {
		WebElement query = driver.findElement(By.name("wd"));
		query.sendKeys("TestNg实现case失败重新运行");
		WebElement btn = driver.findElement(By.id("su"));
		btn.click();
		Thread.sleep(5000);		
		List<WebElement> linkLi = driver.findElements(By.cssSelector(SELENIUMCN_LINKs));
		for(int i = 0;i<linkLi.size();i++) {
			String title = linkLi.get(i).getText();
			String link = linkLi.get(i).getAttribute("href");
			System.out.println("The TITLE is:["+title+"]\n LINK is:["+link+"]");
		}
		driver.close();
    }
}
