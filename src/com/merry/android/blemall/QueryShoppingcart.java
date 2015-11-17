package com.merry.android.blemall;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class QueryShoppingcart extends BaiLian{
	MobileElement num;
	@Test
	public void queryShoppingcart()
	{
		Log.logInfo("当前webview=" + androidDriver.getContext());
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		implicitlyWait(androidDriver, 30);
		androidDriver.findElement(By.xpath("//*[@id='nav']/ul/li[4]/a/span")).click();
		Log.logInfo("点击首页的\"购物车\"");
		implicitlyWait(androidDriver, 30);
		if(androidDriver.findElement(By.xpath("//*[@id='popup-content']")).isDisplayed())
		{
			Log.logError("加载失败");
			
		}
		if(androidDriver.findElement(By.xpath("//*[@id='shoppingcart-login']/a")).isDisplayed())
		{
			androidDriver.findElement(By.xpath("//*[@id='shoppingcart-login']/a")).click();
			MobileElement username=androidDriver.findElement(By.xpath("//*[@id='usernameField']"));
			username.clear();
			username.sendKeys("xcmt");
			MobileElement password=androidDriver.findElement(By.xpath("//*[@id='passwordField']"));
			password.sendKeys("qweqwe123");
			androidDriver.findElement(By.xpath("//*[@id='loginbtn']")).click();
		}
		else{
		num=androidDriver.findElement(By.xpath("//*[@id='shoppingcart_gopayment']"));
		Log.logInfo(num.getText());
		Log.logInfo("当前购物车里的商品数量= "+getNumber(num.getText()));        
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"购物车","查询购物车");
		}
		//return getNumber(num.getText());
		
	}
	public int getNumber(String text)
	{
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(text);   
		String s=m.replaceAll("").trim();
		int i=Integer.parseInt(s);
		return i;
	}

}
