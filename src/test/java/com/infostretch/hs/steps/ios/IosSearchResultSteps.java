package com.infostretch.hs.steps.ios;

import com.infostretch.hs.steps.common.SearchResultSteps;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class IosSearchResultSteps extends SearchResultSteps {
	 @QAFTestStep(description = "user filters by price by setting {price} value")
	    public void applyMinimumPriceFilter(int price) {
	        // TODO: Due to bug, we cannot change the slider to specific value
	    }
}
