package com.infostretch.hs.steps.common;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.utils.MobileUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class SearchResultSteps {

	@QAFTestStep(description = "user should see list of available options")
	public void verifySearchResultOptions() {
		CommonStep.assertPresent("hotel.search.icon.price.filter");
		CommonStep.assertPresent("hotel.search.icon.popular.filter");
		CommonStep.assertPresent("hotel.search.icon.location.filter");
	}

	@QAFTestStep(description = "user filters by price by setting {price} value")
	public void applyMinimumPriceFilter(int price) {
		QAFWebElement priceBar = MobileUtils.findElementByScrolling("hotel.search.txt.filter.pricebar", 2);
		int min = getMinPriceFilter();
		if (price > min) {
			Point location = priceBar.getLocation();
			Dimension size = priceBar.getSize();
			int startX = location.getX();
			int y = location.getY() + (size.getHeight() / 2);
			int endX = location.getX() + (int) ((size.getWidth()) * ((float) price / (float) getMaxPriceFilter()));
			MobileUtils.dragAndDrop(startX, y, endX, y, Duration.ofSeconds(1));
		}
	}

	private int getMinPriceFilter() {
		return Integer.parseInt($("hotel.search.txt.filter.pricerange").getText().split("-")[0].replaceAll("\\D", ""));
	}

	private int getMaxPriceFilter() {
		return Integer.parseInt($("hotel.search.txt.filter.pricerange").getText().split("-")[1].replaceAll("\\D", ""));
	}

	@QAFTestStep(description = "apply filter by user rating {rating}")
	public void applyUserRatingFilter(String rating) {
		String locator = String.format(ConfigurationManager.getBundle().getString("hotel.search.txt.filter.rating"),
				rating);
		MobileUtils.findElementByScrolling(locator, 5).click();
	}

	@QAFTestStep(description = "user applys filter")
	public void applyFilter() {
		CommonStep.click("hotel.search.btn.filter.apply");
	}

	public void applyRatingFilter(String rating) {
		CommonStep.click("hotel.search.icon.rating.filter");
		switch (rating) {
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
	public void userScrollAndSelectHotel(int index) {
		CommonStep.waitForPresent("hotel.list");
		Set<String> hotelNames = new HashSet<String>();
		while (hotelNames.size() <= index) {
			String hotelName = CommonStep.getText("hotel.item.name");
			hotelNames.add(hotelName);
			if (hotelNames.size() == index - 1) {
				CommonStep.click("hotel.item.name");
				break;
			}
			MobileUtils.swipeVertically();
		}
	}
}
