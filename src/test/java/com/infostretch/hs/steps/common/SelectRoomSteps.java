package com.infostretch.hs.steps.common;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.utils.Constants;
import com.infostretch.hs.utils.MobileUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class SelectRoomSteps {

	@QAFTestStep(description = "capture the room details and click SELECT ROOM options")
	public void captureTheRoomDetailsAndOption() {

		String hotelName = CommonStep.getText("hotel.detail.txt.title");
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_HOTEL, hotelName);

		CommonStep.waitForVisible("hotel.detail.btn.selectroom");

		CommonStep.click("hotel.detail.btn.selectroom");
		// check if still present
		try {
			$("hotel.detail.btn.selectroom").waitForNotPresent(5000);
		} catch (Exception e) {
		}
		// click retry again
		if ($("hotel.detail.btn.selectroom").isPresent()) {
			$("hotel.detail.btn.selectroom").click();
		}

		MobileUtils.swipeVertically();

		String roomCategoryName = CommonStep.getText("hotel.room.category.detail.txt.name");
		String roomCategoryDiscountedPrice = CommonStep.getText("hotel.room.category.detail.txt.discountedprice");
		String roomCategoryOriginalPrice = CommonStep.getText("hotel.room.category.detail.txt.originalprice");

		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_ROOM_CATEGORY_NAME, roomCategoryName);
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_ROOM_CATEGORY_DISCOUNTEDPRICE,
				roomCategoryDiscountedPrice);
		ConfigurationManager.getBundle().setProperty(Constants.SELECTED_ROOM_CATEGORY_ORIGINALPRICE,
				roomCategoryOriginalPrice);

		CommonStep.click("hotel.detail.btn.continue");
	}
}