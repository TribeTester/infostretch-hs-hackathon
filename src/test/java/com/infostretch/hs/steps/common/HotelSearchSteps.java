package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class HotelSearchSteps {

    @QAFTestStep(description = "select a location {location}")
    public void selectLocation(String location) {
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
