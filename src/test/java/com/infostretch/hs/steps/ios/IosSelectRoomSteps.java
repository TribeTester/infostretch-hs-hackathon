package com.infostretch.hs.steps.ios;

import com.infostretch.hs.steps.common.SelectRoomSteps;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class IosSelectRoomSteps extends SelectRoomSteps {
	@QAFTestStep(description = "capture the room details and click SELECT ROOM options")
	public void captureTheRoomDetailsAndOption() {

		CommonStep.click("hotel.detail.btn.selectroom");
		CommonStep.click("hotel.detail.btn.continue");
	}
}
