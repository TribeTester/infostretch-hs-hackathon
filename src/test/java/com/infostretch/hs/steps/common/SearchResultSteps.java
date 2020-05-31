package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

import static com.infostretch.hs.utils.LocatorUtils.getDynamicLocator;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.components.IHotel;
import com.infostretch.hs.utils.LocatorUtils;
import com.infostretch.hs.utils.MobileUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class SearchResultSteps {

    @QAFTestStep(description = "user should see list of available options")
    public void verifySearchResultOptions() {
    	CommonStep.assertPresent("hotel.search.icon.price.filter");
    	CommonStep.assertPresent("hotel.search.icon.popular.filter");
    	CommonStep.assertPresent("hotel.search.icon.location.filter");
    }

    
    @QAFTestStep(description = "user on search result page and filter by price by setting {minimum} value")
    public void applyMinimumPriceFilter(String price) {
    	CommonStep.click("hotel.search.icon.price.filter");
    	CommonStep.assertPresent("hotel.search.icon.price.filter");
    	int startX = $("hotel.search.txt.filter.pricebar").getLocation().getX();
    	int yAxis = $("hotel.search.txt.filter.pricebar").getLocation().getY();
    	int end = $("hotel.search.txt.filter.pricebar").getSize().getWidth();
    	MobileUtils.dragAndDrop(startX, yAxis, 105, yAxis, null);
    	CommonStep.click("hotel.search.btn.filter.apply");
    }

    @QAFTestStep(description = "apply filter by user rating {rating} & above")
    public void applyRatingFilter(String rating) {
    	CommonStep.click("hotel.search.icon.rating.filter");
    	switch(rating){
    	case "3 Star":
    		CommonStep.click("hotel.search.txt.filter.rating.threestar");
    		break;
    	case "4 Star":
    		CommonStep.click("hotel.search.txt.filter.rating.fourstar");
    		break;
    	case "5 Star":
    		CommonStep.click("hotel.search.txt.filter.rating.fivestar");
    		break;
    	case "Unrated":
    		CommonStep.click("hotel.search.txt.filter.rating.unrated");
    		break;
    	}
    	CommonStep.click("hotel.search.btn.filter.apply");
    }
    	
    
    @QAFTestStep(description = "verify filters applied and user should see filter result page")
    public void verifyFiltersApplied() {
    	CommonStep.assertPresent("hotel.search.txt.filter.identity");
    }
    
    @QAFTestStep(description = "user scroll and select the {index} item from filter result page")
    public void userScrollAndSelectHotel(String index) {
    	CommonStep.waitForPresent("hotel.list");
    	Set<String> hotelNames = new HashSet<String>();
    	while(hotelNames.size() <= Integer.parseInt(index)) {
    		String hotelName = CommonStep.getText("hotel.item.name");
    		hotelNames.add(hotelName);
    		if(hotelNames.size() == Integer.parseInt(index)-1) {
    			CommonStep.click("hotel.item.name");
    			break;
    		}
    		MobileUtils.swipeVertically();
    	}
    }


}
