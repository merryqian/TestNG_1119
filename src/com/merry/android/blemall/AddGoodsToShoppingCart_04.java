package com.merry.android.blemall;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class AddGoodsToShoppingCart_04 extends BaiLian {
	@Test
	public void addGoodsToShoppingCart()
	{
		Log.logInfo("当前模式=" + androidDriver.getContext());
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		QueryShoppingcart qs=new QueryShoppingcart();
		androidDriver.findElement(By.xpath("//*[@id='nav']/ul/li[1]/a/span")).click();
		SingleGoodsPage_03 sp=new SingleGoodsPage_03();
		sp.singleGoodsPage();
		Log.logInfo("当前模式=" + androidDriver.getContext());
		androidDriver.context("WEBVIEW_cn.com.bailian.bailianmobile");
		Log.logInfo("切换webview= "+ androidDriver.getContext());
		androidDriver.findElement(By.xpath("//*[@id='joinshoppinglot']")).click();
		qs.queryShoppingcart();
		
	}

}
