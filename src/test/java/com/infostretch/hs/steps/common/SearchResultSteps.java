package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class SearchResultSteps {


    @QAFTestStep(description = "user should see list of available options")
    public void verifySearchResultOptions() {
    }

    @QAFTestStep(description = "user filters by price by setting {minimum} value")
    public void applyMinimumPriceFilter(String price) {
    }

    @QAFTestStep(description = "apply filter by user rating {rating} & above")
    public void applyUserRatingFilter(String rating) {
    }

    @QAFTestStep(description = "use applys filter")
    public void applyFilter() {
    }

    @QAFTestStep(description = "user scroll and select the {index} item from filter result page")
    public void userScrollAndSelectHotel(String index) {
    }


}
