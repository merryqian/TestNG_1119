package com.merry.android.blemall;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class CreateOrder extends BaiLian {
	@Test
	public void createOrder()
	{
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		QueryShoppingcart qs=new QueryShoppingcart();
		qs.queryShoppingcart();
		androidDriver.findElement(By.xpath("//*[@id='submitorderbtn']"));
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"下单","订单确认页");
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		WebElement success=androidDriver.findElement(By.xpath("//*[@id='payorder']/div[1]/div/div[1]/div/h2"));
		Log.logInfo(success.getText());
		androidDriver.context("NATIVE_APP");
		snapshot(androidDriver,"下单","下单成功页");
		Assert.assertEquals("提交订单成功", success.getText(),"下单成功");
	}

}
