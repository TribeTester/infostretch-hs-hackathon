package com.infostretch.hs.steps.ios;

import static com.infostretch.hs.utils.LocatorUtils.$;
import static com.infostretch.hs.utils.LocatorUtils.getDynamicLocator;
import static com.infostretch.hs.utils.MobileUtils.getDriver;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

import java.util.List;

import com.infostretch.hs.steps.common.HotelSearchSteps;
import com.infostretch.hs.utils.LocatorUtils;
import com.infostretch.hs.utils.MobileUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class IosHotelSearchSteps extends HotelSearchSteps {
	
	@QAFTestStep(description = "select a location {location}")
    public void selectLocation(String location) {
        //click on current selected city
        click("hotel.search.link.city");

        //search for city
        sendKeys(location, "hotel.search.input.city");

        //wait for suggestions to appear and click on first suggestion
        getDynamicLocator("hotel.search.list.autosuggestion.results", location).waitForPresent();
        getDynamicLocator("hotel.search.list.autosuggestion.results", location).click();
    }

	@QAFTestStep(description = "select a {checkInDate} and {checkOutDate} dates")
	public void selectDates(String checkInDate, String checkOutDate) {
		CommonStep.click("hotel.search.layout.checkin");
		String checkInLocator = String.format(
				ConfigurationManager.getBundle().getString("selectdate.date.label", "selectdate.date.label"),
				checkInDate);
		MobileUtils.findElementByScrolling(checkInLocator, 5);
		CommonStep.click(checkInLocator);

		String checkOutLocator = String.format(
				ConfigurationManager.getBundle().getString("selectdate.date.label", "selectdate.date.label"),
				checkOutDate);
		MobileUtils.findElementByScrolling(checkOutLocator, 5);
		CommonStep.click(checkOutLocator);

		CommonStep.click("done.button");
	}

	@QAFTestStep(description = "add {rooms} rooms for {adults} adults & {children} children each")
	public void addMembers(int rooms, int adults, int children) {
		CommonStep.click("hotel.search.layout.rooms");
		CommonStep.waitForPresent("hotel.search.btn.addanother.room");

		List<QAFWebElement> removeButtons = getDriver().findElements("details.removeroom.button");
		for (int i = 0; i < removeButtons.size() - 1; i++) {
			removeButtons.get(i).click();
		}

		// Default Room
		int count = 1;
		addAdults(adults, count);
		addChild(children, count);

		while (count < rooms) {
			CommonStep.click("hotel.search.btn.addanother.room");

			count++;
			String roomLocator = String.format(
					ConfigurationManager.getBundle().getString("room.details.cell", "room.details.cell"), count);
			MobileUtils.findElementByScrolling(roomLocator, 2);

			addAdults(adults, count);
			addChild(children, count);
		}

		// click done button
		CommonStep.click("hotel.search.btn.guests.done");
	}

	private void addAdults(int adults, int room) {
		QAFExtendedWebElement roomSection = (QAFExtendedWebElement) LocatorUtils.getDynamicLocator("room.details.cell",
				room);

		int currentAdults = Integer
				.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.adults.count").getText());

		if (currentAdults < adults) {
			// click on add adult button
			while (currentAdults < adults) {
				new QAFExtendedWebElement(roomSection, "hotel.search.btn.adults.add").click();
				currentAdults = Integer
						.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.adults.count").getText());
			}
		} else if (currentAdults > adults) {
			// click on substract button
			while (currentAdults > adults) {

				new QAFExtendedWebElement(roomSection, "hotel.search.btn.adults.substract").click();
				currentAdults = Integer
						.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.adults.count").getText());
			}
		}
	}

	private void addChild(int children, int room) {

		QAFExtendedWebElement roomSection = (QAFExtendedWebElement) LocatorUtils.getDynamicLocator("room.details.cell",
				room);

		int currentAdults = Integer
				.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.children.count").getText());

		if (currentAdults < children) {
			// click on add adult button
			while (currentAdults < children) {
				new QAFExtendedWebElement(roomSection, "hotel.search.btn.children.add").click();
				currentAdults = Integer
						.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.children.count").getText());
			}
		} else if (currentAdults > children) {
			// click on substract button
			while (currentAdults > children) {

				new QAFExtendedWebElement(roomSection, "hotel.search.btn.children.substract").click();
				currentAdults = Integer
						.parseInt(new QAFExtendedWebElement(roomSection, "hotel.search.txt.children.count").getText());
			}
		}
	}

	@QAFTestStep(description = "select travelling for {tripType}")
	public void setTripType(String tripType) {
		$("search.trip.type", tripType).click();
	}

	@QAFTestStep(description = "search for the options")
	public void search() {
		CommonStep.click("search.button");
	}
}
