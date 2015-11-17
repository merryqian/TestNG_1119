package com.merry.android.blemall;

import java.util.List;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class SearchGoods_05 extends BaiLian{
	@Test
	public void searchGoods()
	{
		Log.logInfo("获取当前webview=" + androidDriver.getContext());
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		implicitlyWait(androidDriver, 30);
		MobileElement search=androidDriver.findElement(By.xpath("//*[@id='home']/div/div[1]/header/div/div[3]/a"));
		search.click();
		Log.logInfo("点击首页的搜索框");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		implicitlyWait(androidDriver, 30);
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"搜索","进入首页页面");
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		MobileElement inputSearch=androidDriver.findElement(By.xpath("//*[@id='inputSearch']"));
		inputSearch.sendKeys("手机");
		implicitlyWait(androidDriver, 30);
		androidDriver.findElement(By.xpath("//*[@id='submitsearch']")).click();
		Log.logInfo("点击\"搜索\"");
		List list=androidDriver.findElementsByPartialLinkText("手机");
		System.out.println(list.size());
		implicitlyWait(androidDriver, 30);
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"搜索","查询结果");
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		
	}

}
