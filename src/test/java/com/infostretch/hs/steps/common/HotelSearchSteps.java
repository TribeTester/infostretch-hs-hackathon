package com.infostretch.hs.steps.common;

import static com.infostretch.hs.utils.LocatorUtils.getDynamicLocator;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.utils.Constants;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class HotelSearchSteps {

	@QAFTestStep(description = "select a location {location}")
	public void selectLocation(String location) {
		// click on current selected city
		click("hotel.search.link.city");

		// search for city
		sendKeys(location, "hotel.search.input.city");

		// wait for suggestions to appear and click on first suggestion
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

		// before search get all property and save for further verification

		// save city value
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CITY,
				$("hotel.search.txt.selected.city").getText());

		// save checkin date
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKIN_DATE,
				$("hotel.search.txt.selected.checkInDate").getText());

		// save checkin month
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKIN_MONTH_YEAR,
				$("hotel.search.txt.selected.checkInMonthYear").getText());

		// save checkin week
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKIN_WEEK,
				$("hotel.search.txt.selected.checkInWeek").getText());

		// save checkout date
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKOUT_DATE,
				$("hotel.search.txt.selected.checkOutDate").getText());

		// save checkout month
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKOUT_MONTH_YEAR,
				$("hotel.search.txt.selected.checkOutMonthYear").getText());

		// save checkout week
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_CHECKOUT_WEEK,
				$("hotel.search.txt.selected.checkOutWeek").getText());

		// save guest count
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_GUESTS_COUNT,
				$("hotel.search.txt.selected.guestCount").getText());

		// save room count
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_ROOMS_COUNT,
				$("hotel.search.txt.selected.roomCount").getText());

		// click on hotel search button
		CommonStep.click("hotel.search.btn.submit");
	}
}
