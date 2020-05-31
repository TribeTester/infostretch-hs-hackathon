package com.infostretch.hs.steps.common;

import static com.infostretch.hs.utils.MobileUtils.findElementByScrolling;
import static com.infostretch.hs.utils.MobileUtils.getDriver;

import com.infostretch.hs.utils.LocatorUtils;
import com.infostretch.hs.utils.TestDataUtils;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import java.util.List;
import java.util.Map;

public class BookingSteps {

    @QAFTestStep(description = "user fills traveller information")
    public void fillTravellerInformation() {
        Map<String, Object> testdata = TestDataUtils.getCurrentTestcaseTestData();

        //fill primary guest title
        CommonStep.click("guest.select.title");
        LocatorUtils.getDynamicLocator("guest.select.title.option",
            testdata.get("guest.primary.title")).click();

        //fill primary guest first name
        CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.firstName")),
            "guest.input.first.name");

        //fill primary guest last name
        CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.lastName")),
            "guest.input.last.name");

        //fill primary guest mobile number
        CommonStep.sendKeys(String.valueOf(testdata.get("guest.primary.mobile")),
            "guest.input.mobile.no");

    }

    @QAFTestStep(description = "selects two options from commonly requested option")
    public void selectsCommonlyRequestedOption() {
        findElementByScrolling("guest.link.special.request", 4).click();
        List<QAFWebElement> checkboxes = getDriver()
            .findElements("guest.checkbox.special.request");
        for (int i = 0; i < checkboxes.size() && i < 2; i++) {
            checkboxes.get(i).click();
        }
        CommonStep.click("guest.btn.special.request.done");
    }

    @QAFTestStep(description = "uncheck the donation checkbox")
    public void uncheckTheDonationCheckbox() {
        QAFWebElement donateCheckbox = findElementByScrolling("booking.checkbox.donation", 4);
        if (Boolean.parseBoolean(donateCheckbox.getAttribute("checked"))) {
            donateCheckbox.click();
        }
    }

    @QAFTestStep(description = "click on PAY NOW option")
    public void clickOnPayNowOption() {
        CommonStep.click("booking.btn.continue");
    }

}
