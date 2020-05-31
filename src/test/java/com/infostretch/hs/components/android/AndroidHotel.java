package com.infostretch.hs.components.android;

import com.infostretch.hs.components.IHotel;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class AndroidHotel extends QAFWebComponent implements IHotel{
	
	@FindBy(locator = "hotel.item.name")
	private QAFWebElement name;
	
	@FindBy(locator = "hotel.item.location ")
	private QAFWebElement location;
	
	@FindBy(locator = "hotel.item.propertytype")
	private QAFWebElement propertyType;
	
	@FindBy(locator = "hotel.item.discountedprice")
	private QAFWebElement discountedPrice;
	
	@FindBy(locator = "hotel.item.actualprice")
	private QAFWebElement actualPrice;
	
	@FindBy(locator = "hotel.item.discount")
	private QAFWebElement discount;
		
	@FindBy(locator = "hotel.item.rating")	
	private QAFWebElement rating;
	
	public AndroidHotel() {
		super("");
	}
	
	@Override
	public void selectHotel(int index) {
		
	}
	
}
