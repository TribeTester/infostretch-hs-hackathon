package com.infostretch.hs.steps.ios;

import com.infostretch.hs.steps.common.HomePageSteps;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

public class IosHomePageSteps extends HomePageSteps {

	@QAFTestStep(description = "user navigates to Sort and Filter page")
	public void navigateToSortFilter(String menu) {
		dismissOverlay();
		CommonStep.click("home.sortfilter.menu");
	}
	
	private void dismissOverlay() {
		if ($("cancellation.popup").isPresent()) {
			CommonStep.click("cancellation.popup");
		}
		
		if ($("coachmark.arrow.popup").isPresent()) {
			CommonStep.click("coachmark.arrow.popup");
		}
	}
}
