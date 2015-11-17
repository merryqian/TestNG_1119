package com.merry.android.blemall;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class FirstPageLoad_01 extends BaiLian {
    
	@Test
	public void firstPageLoad() {
        String timeString=ts.getTimeString();
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
}
