package com.merry.android.blemall;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class LoginBaiLian_02 extends BaiLian {
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
}
