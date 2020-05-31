package com.infostretch.hs.steps.ios;

import java.util.Map;

import com.infostretch.hs.steps.common.BookingSteps;
import com.infostretch.hs.utils.TestDataUtils;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class IosBookingSteps extends BookingSteps {

	@QAFTestStep(description = "user fills traveller information")
	public void fillTravellerInformation() {
		Map<String, Object> testdata = TestDataUtils.getCurrentTestcaseTestData();

		// fill primary guest first name
		CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.firstName")), "guest.input.first.name");

		// fill primary guest last name
		CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.lastName")), "guest.input.last.name");

		// fill primary guest mobile number
		CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.mobile")), "guest.input.mobile.no");

	}
}
