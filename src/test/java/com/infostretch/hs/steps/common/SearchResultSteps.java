package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class SearchResultSteps {


    @QAFTestStep(description = "user should see list of available options")
    public void verifySearchResultOptions() {
    }

    @QAFTestStep(description = "user on search result page and filter by price by setting {minimum} value")
    public void applyMinimumPriceFilter(String price) {
    }

    @QAFTestStep(description = "apply filter by user rating {rating} & above")
    public void applyRatingFilter(String rating) {
    }

    @QAFTestStep(description = "verify filters applied and user should see filter result page")
    public void verifyFiltersApplied() {
    }

    @QAFTestStep(description = "user scroll and select the {index} item from filter result page")
    public void userScrollAndSelectHotel(String index) {
    }


}
