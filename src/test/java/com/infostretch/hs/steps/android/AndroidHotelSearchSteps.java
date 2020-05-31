package com.infostretch.hs.steps.android;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.steps.common.HotelSearchSteps;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class AndroidHotelSearchSteps extends HotelSearchSteps {

	@QAFTestStep(description = "add {rooms} rooms for {adults} adults & {children} children each")
	public void addMembers(int rooms, int adults, int children) {

		CommonStep.click("hotel.search.layout.rooms");
		$("hotel.search.btn.addanother.room").waitForPresent();

		// Default Room
		int count = 1;
		addAdults(adults);
		addChild(children);
		
		while (count < rooms) {
			$("hotel.search.btn.addanother.room").click();

			count++;
			
			addAdults(adults);
			addChild(children);			
		}	

		// click done button
		CommonStep.click("hotel.search.btn.guests.done");
	}

	private void addAdults(int adults) {

		int currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
		if (currentAdults < adults) {
			// click on add adult button
			while (currentAdults < adults) {
				CommonStep.click("hotel.search.btn.adults.add");
				currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
			}
		} else if (currentAdults > adults) {
			// click on substract button
			while (currentAdults > adults) {
				CommonStep.click("hotel.search.btn.adults.substract");
				currentAdults = Integer.parseInt($("hotel.search.txt.adults.count").getText());
			}
		}
	}

	private void addChild(int children) {

		// click on add children button
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
	}
}
