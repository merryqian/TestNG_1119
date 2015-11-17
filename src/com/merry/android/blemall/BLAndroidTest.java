package com.merry.android.blemall;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merry.android.util.Log;
import com.merry.android.util.ParseXml;
import com.merry.android.util.TimeString;

public class BLAndroidTest {
	AndroidDriver<MobileElement> androidDriver;
	ParseXml xml = new ParseXml();
	TimeString ts=new TimeString();
//	static{
//		try {
//			setUp();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@BeforeTest
	 public  void setUp() throws Exception {
	 // set up appium
	
//	 File classpathRoot = new File(System.getProperty("user.dir"));
//	 File appDir = new File(classpathRoot, "app/");
//	 File app = new File(appDir,"BaiLianMobileApp.apk");
	 DesiredCapabilities capabilities = new DesiredCapabilities();
	 capabilities.setCapability("device","Android");
	 capabilities.setCapability("platformName", "Android");
	 capabilities.setCapability("deviceName","4f7bb04c");
	 capabilities.setCapability(CapabilityType.VERSION, "4.4");
	 capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
	 //capabilities.setCapability("app", app.getAbsolutePath());
	 capabilities.setCapability("appPackage", "cn.com.bailian.bailianmobile");
	 capabilities.setCapability("appActivity", ".BaiLianMobileApp");
	 capabilities.setCapability("unicodeKeyboard", "True");
	 capabilities.setCapability("resetKeyboard", "True");
	 androidDriver= new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	 implicitlyWait(androidDriver,30);
	 }

	 @AfterTest
	public void tearDown() throws Exception {
		 androidDriver.quit();
		System.out.println("the end");
	}

	/**
	 * This Method create for take screenshot
	 * 
	 * @author Merry
	 * @param drivername
	 * @param filename
	 */
	public  void snapshot(TakesScreenshot drivername, String foldername,String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
        String timeString=ts.getTimeString();
		String currentPath = System.getProperty("user.dir");
		// get current workfolder
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("save snapshot path is:" + currentPath + "/"
					+ filename + ".jpg");
			FileUtils.copyFile(scrFile, new File(currentPath +"\\"+ "screenshot"+"\\"+timeString+"\\" +foldername+ "\\"+filename
					+ ".jpg"));
		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished, it's in " + currentPath
					+ " folder");
		}
	}

	public void implicitlyWait(AppiumDriver<MobileElement> driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	//首页加载
	@Test
	public void firstPageLoad() {
		implicitlyWait(androidDriver, 30);
		Log.logInfo("等待30秒");
	   // androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		try {
			MobileElement el = androidDriver.findElementByAccessibilityId("领券中心 Link");
			// if(el.isDisplayed())
			Assert.assertTrue(el.isDisplayed());
			Log.logInfo("首页加载成功" + el.getText());
		} catch (Exception e) { 
			e.printStackTrace();
			Log.logInfo("首页加载失败");
		}
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver, "首页", "firstpage");

	}
	//登录
	@Test
	public void loginBaiLian() throws InterruptedException {
		implicitlyWait(androidDriver, 30);
		Log.logInfo("等待30秒");
		Log.logInfo("当前webview=" + androidDriver.getContext());
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换后的webview "+ androidDriver.getContext());
		implicitlyWait(androidDriver, 5);
		Log.logInfo("等待5秒");
		androidDriver.findElement(By.xpath("//div[@id='nav']/ul/li[5]/a/span")).click();
		Log.logInfo("点击\"我\"");
		androidDriver.findElement(By.xpath("//div[@id='userinfoPage']/div/div[2]/div/div/div/a[2]")).click();
		Log.logInfo("点击\"登录\"");
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"登录","登录1");
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		androidDriver.findElement(By.xpath("//*[@id='usernameField']")).clear();
		Log.logInfo("清空用户名字段");
		WebElement username=androidDriver.findElement(By.xpath("//*[@id='usernameField']"));
		username.sendKeys("18516042869");
		Log.logInfo("输入的用户名： = "+username.getAttribute("value"));
		MobileElement password=androidDriver.findElement(By.xpath("//*[@id='passwordField']"));
		password.sendKeys("donkey1987");
		Log.logInfo("输入的密码= "+password.getAttribute("value"));
		androidDriver.findElement(By.xpath("//*[@id='loginbtn']")).click();
		Log.logInfo("点击\"登录\"按钮");
        MobileElement userLogin=androidDriver.findElement(By.xpath("//*[@id='userinfoPage']/div/div[2]/div/div/div[3]/p"));
        try{
        	if(userLogin.isDisplayed())
        	Log.logInfo("登录成功= "+userLogin.getText());
        }catch(Exception e)
        {
        	e.printStackTrace();
        	Log.logError("登录失败");
        	
        }
        androidDriver.context("NATIVE_APP");
        snapshot(androidDriver,"登录","登录2");
	}
	//搜索功能
		@Test
		public void searchGoods()
		{
			Log.logInfo("获取当前webview=" + androidDriver.getContext());
			androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
			Log.logInfo("切换webview= "+ androidDriver.getContext());
			androidDriver.findElement(By.xpath("//*[@id='nav']/ul/li[1]/a/span")).click();
			implicitlyWait(androidDriver, 30);
			MobileElement search=androidDriver.findElement(By.xpath("//*[@id='home']/div/div[1]/header/div/div[3]/a"));
			search.click();
			Log.logInfo("点击首页的搜索框");
			Log.logInfo("切换webview= "+ androidDriver.getContext());
			implicitlyWait(androidDriver, 30);
			MobileElement inputSearch=androidDriver.findElement(By.xpath("//*[@id='inputSearch']"));
			androidDriver.context("NATIVE_APP");
			snapshot(androidDriver,"搜索","1");
			androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
			inputSearch.sendKeys("手机");
			implicitlyWait(androidDriver, 30);
			androidDriver.findElement(By.xpath("//*[@id='submitsearch']")).click();
			Log.logInfo("点击\"搜索\"");
			List list=androidDriver.findElementsByPartialLinkText("手机");
			System.out.println(list.size());
			implicitlyWait(androidDriver, 30);
			androidDriver.context("NATIVE_APP");
			snapshot(androidDriver,"搜索","2");
			androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
			
		}
		
		 
	
	

}
