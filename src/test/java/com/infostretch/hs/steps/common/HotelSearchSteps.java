package com.infostretch.hs.steps.common;

import static com.infostretch.hs.utils.LocatorUtils.getDynamicLocator;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

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

    @QAFTestStep(description = "select rooms details with {adults} adults & {children} children")
    public void selectRoomsDetails(int adults, int children) {
    }

    @QAFTestStep(description = "select guests details with {adults} adults & {children} children")
    public void selectGuestDetails(int adults, int children) {
    }

    @QAFTestStep(description = "select travelling for {tripType}")
    public void setTripType(String tripType) {
    }

    @QAFTestStep(description = "search for the options")
    public void search() {
    }
}
