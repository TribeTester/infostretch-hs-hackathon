package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class HotelSearchSteps {

    @QAFTestStep(description = "select a location {location}")
    public void selectLocation(String location) {
    	CommonStep.click("hotel.search.link.city");
    	CommonStep.sendKeys(location, "hotel.search.input.city");
    	CommonStep.click("hotel.search.list.autosuggestion.results");
    }

    @QAFTestStep(description = "select a {checkInDate} and {checkOutDate} dates")
    public void selectDates(String checkInDate, String checkOutDate) {
    	
    }

    @QAFTestStep(description = "select rooms details with {adults} adults & {children} children")
    public void selectRoomsDetails(int adults, int children) {
    	CommonStep.click("hotel.search.layout.rooms");
    	if(adults == 1) {
    		CommonStep.click("hotel.search.btn.adults.substract");
    	}
    	while(adults > 2) {
    		CommonStep.click("hotel.search.btn.adults.add");
    		adults -= 1;
    	}
    	while(children > 0) {
    		CommonStep.click("hotel.search.btn.children.substract");
    	}
    	CommonStep.click("hotel.search.btn.guests.done");
    }

    @QAFTestStep(description = "select guests details with {adults} adults & {children} children")
    public void selectGuestDetails(int adults, int children) {
    }

    @QAFTestStep(description = "select travelling for {tripType}")
    public void setTripType(String tripType) {
    	CommonStep.click("hotel.search.checkbox.triptype");
    }

    @QAFTestStep(description = "search for the options")
    public void search() {
    	CommonStep.click("hotel.search.btn.submit");
    }
}
