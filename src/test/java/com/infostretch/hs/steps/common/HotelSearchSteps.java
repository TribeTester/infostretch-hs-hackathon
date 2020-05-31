package com.infostretch.hs.steps.common;

import static com.infostretch.hs.utils.LocatorUtils.getDynamicLocator;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class HotelSearchSteps {
	
	
    @QAFTestStep(description = "select a location {location}")
    public void selectLocation(String location) {
        //click on current selected city
        click("hotel.search.link.city");

        //search for city
        sendKeys(location, "hotel.search.input.city");

        //wait for suggestions to appear and click on first suggestion
        getDynamicLocator("hotel.search.list.autosuggestion.results", location).click();

    }

    @QAFTestStep(description = "select a {checkInDate} and {checkOutDate} dates")
    public void selectDates(String checkInDate, String checkOutDate) {
    }

    @QAFTestStep(description = "select travelling for {tripType}")
    public void setTripType(String tripType) {
    	getDynamicLocator("hotel.search.checkbox.triptype", tripType).click();
    }

    @QAFTestStep(description = "search for the options")
    public void search() {
    	CommonStep.click("hotel.search.btn.submit");
    }
}
