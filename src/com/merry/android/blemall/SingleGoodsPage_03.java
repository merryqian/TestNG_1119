package com.merry.android.blemall;

import java.util.List;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.merry.android.util.Log;

public class SingleGoodsPage_03 extends BaiLian {
	
     //从搜索结果中进入单品页
	@Test
	public void singleGoodsPage()
	{
		//ҵ��·����������ҳ->������Ʒ->��������е��������Ʒ����

		
		androidDriver.findElement(By.xpath("//*[@id='commodity_collection_content']/div[1]/div[1]")).click();
		implicitlyWait(androidDriver,10);
		WebElement goodsname=androidDriver.findElement(By.xpath("//*[@id='commoditypage1']/div[2]/div[1]/a"));
		Log.logInfo("商品名= "+goodsname.getText());
		WebElement price=androidDriver.findElement(By.xpath("//*[@id='commoditypage1']/div[2]/div[1]/a/p"));
		Log.logInfo("价格= "+price.getText());
//		List listgoods=androidDriver.findElements(By.xpath("//*[@id='commodity_collection_content']/div[@class='cxlist']"));
//		System.out.println(listgoods.size());
//		MobileElement shoppcart=androidDriver.findEl    ement(By.xpath("//*[@id='productshow_joinshoppingcart_0']"));
//		Log.logInfo(shoppcart.isEnabled());
//		androidDriver.findElement(By.xpath("//*[@id='commodity_collection_content']/div[1]/div[2]")).click();
//		implicitlyWait(androidDriver, 30);
		
	}

	

}
