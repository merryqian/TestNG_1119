package com.merry.android.blemall;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merry.android.util.Log;
import com.merry.android.util.ParseXml;
import com.merry.android.util.TimeString;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaiLian {
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
	@BeforeMethod
	 public  void setUp() throws Exception {
	 // set up appium
	
//	 File classpathRoot = new File(System.getProperty("user.dir"));
//	 File appDir = new File(classpathRoot, "app/");
//	 File app = new File(appDir,"BaiLianMobileApp.apk");
	 DesiredCapabilities capabilities = new DesiredCapabilities();
	 capabilities.setCapability("device","Android");
	 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
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

	 @AfterMethod
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

	// 点击某元素
	public void tab(WebElement element) {
		try {
			TouchAction ta = new TouchAction(androidDriver);
			ta.tap(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 点击某坐标
	public void tab(int x, int y) {
		try {
			TouchAction ta = new TouchAction(androidDriver);
			ta.tap(x, y).moveTo(x, y);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 判断元素是否存在
	public boolean isElementExist(By by) {
		try {
			androidDriver.findElement(by);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 在指定时间内元素是否存在，如存在则立即返回结果，如不存在则超时后返回结果
	public boolean isElementExist(By by, int timeout) {
		try {
			new WebDriverWait(androidDriver, timeout).until(ExpectedConditions
					.presenceOfElementLocated(by));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void swipeOnElement(WebElement element,String direction,int duration)
	{
	 //x,y分别为元素的起始坐标点
	 int x=element.getLocation().getX();
	 int y=element.getLocation().getY();
	 int elementWidth=element.getSize().getWidth();
	 int elementHeight=element.getSize().getHeight();
	 switch(direction)
	 {
	   case "Up":
	   int startX=x+elementWidth/2;
	   int startY=y+elementHeight*4/5;
	   int endY=y+elementHeight*1/5;
	   androidDriver.swipe(startX,startY,startX,endY,duration);
	   break;
	   case "Down":
	   startX=x+elementWidth/2;
	   startY=y+elementHeight*1/5;
	   endY=y+elementHeight*4/5;
	   androidDriver.swipe(startX,startY,startX,endY,duration);
	   break;
	   case "Left":
	   startY=x+elementHeight/2;
	   startX=elementWidth*4/5;
	   int endX=y+elementWidth*1/5;
	   androidDriver.swipe(startX,startY,endX,startY,duration);
	   break;
	   case "Right":
	   startY=x+elementHeight/2;
	   startX=y+elementWidth*1/5;
	   endX=y+elementWidth*4/5;
	   androidDriver.swipe(startX,startY,endX,startY,duration);
	   break;
	   default:
	   break;
	  }
	 }
	//@Test
	public void firstPage() throws InterruptedException
	{
		androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		swipeUtilElementAppear("体验百联",1000);
		androidDriver.findElementByAccessibilityId("体验百联").click();
		System.out.println("hello merry");
		
	}
	// 向左滑动
		public void swipeToLeft(int duration) throws InterruptedException {
			int startX = this.appScreen()[0] * 4 / 5;
			int endX = this.appScreen()[0] * 1 / 5;
			int y = this.appScreen()[1] * 1 / 2;
			try {
				androidDriver.swipe(startX, y, endX, y, duration);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private int[] appScreen() throws InterruptedException {
			// TODO Auto-generated method stub
			int width = androidDriver.manage().window().getSize().width;
			int height = androidDriver.manage().window().getSize().height;
			int[] screen = new int[2];
			screen[0] = width;
			screen[1] = height;
			return screen;
		}
	public void swipeUtilElementAppear( String accessID,int duration) throws InterruptedException {
		boolean flag = true;
		while (flag) {
			try {
				androidDriver.findElementByAccessibilityId("accessID");
				flag = false;
			} catch (Exception e) {
				this.swipeToLeft(duration);
			}
		}
	}
}
