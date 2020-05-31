package com.infostretch.hs.steps.ios;

import com.infostretch.hs.steps.common.SearchResultSteps;
import com.infostretch.hs.utils.MobileUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class IosSearchResultSteps extends SearchResultSteps {
	@QAFTestStep(description = "user filters by price by setting {price} value")
	public void applyMinimumPriceFilter(int price) {
		// TODO: Due to bug, we cannot change the slider to specific value
	}

	@QAFTestStep(description = "user scroll and select the {index} item from filter result page")
	public void userScrollAndSelectHotel(int index) {
		String loc = String.format(
				ConfigurationManager.getBundle().getString("search.select.hotel", "search.select.hotel"), index);
		CommonStep.waitForPresent(loc);
		MobileUtils.findElementByScrolling(loc, index + 3).click();
	}
	
	@QAFTestStep(description = "apply filter by user rating {rating}")
    public void applyUserRatingFilter(String rating) {
        String locator = String
            .format(ConfigurationManager.getBundle().getString("hotel.search.txt.filter.rating"),
                rating);
        MobileUtils.findElementByScrolling(locator, 5);
        try {
        	CommonStep.waitForPresent("hotel.search.txt.filter.rating1", 5);
        } catch (Exception e) {
			// TODO: handle exception
		}
        CommonStep.click(locator);
    }
}
