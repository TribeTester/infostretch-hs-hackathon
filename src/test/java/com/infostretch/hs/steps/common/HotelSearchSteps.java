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

    @QAFTestStep(description = "add room details with {adults} adults & {children} children")
    public void addRoomsDetails(int adults, int children) {
        CommonStep.click("hotel.search.layout.rooms");
        addMembers(adults, children);
    }

    @QAFTestStep(description = "add another room details with {adults} adults & {children} children")
    public void addAnotherRoomDetails(int adults, int children) {
        CommonStep.click("hotel.search.layout.rooms");
        CommonStep.click("hotel.search.btn.addanother.room");
        addMembers(adults, children);
    }

    @QAFTestStep(description = "add members {adults} adults & {children} children")
    private static void addMembers(int adults, int children) {
        $("hotel.search.txt.adults.count").waitForPresent();
        int currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
        if (currentAdults < adults) {
            //click on add adult button
            while (currentAdults < adults) {
                CommonStep.click("hotel.search.btn.adults.add");
                currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
            }
        } else if (currentAdults > adults) {
            //click on substract button
            while (currentAdults > adults) {
                CommonStep.click("hotel.search.btn.adults.substract");
                currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
            }
        }

        //click on add children button
        int currentChildren = Integer.parseInt($("hotel.search.txt.children.count").getText());
        if (currentChildren < children) {
            while (currentChildren < children) {
                CommonStep.click("hotel.search.btn.children.add");
                currentChildren = Integer.parseInt($("hotel.search.txt.children.count").getText());
            }
        } else if (currentChildren > children) {
            while (currentChildren < children) {
                CommonStep.click("hotel.search.btn.children.substract");
                currentChildren = Integer.parseInt($("hotel.search.txt.children.count").getText());
            }
        }

        //click done button
        CommonStep.click("hotel.search.btn.guests.done");
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
