package com.infostretch.hs.steps.android;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.steps.common.HomePageSteps;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class AndroidHomePageSteps extends HomePageSteps {

    /**
     * Launch AUT on target platform
     */
    @QAFTestStep(description = "user launches application")
    public void launchApp() {
        super.launchApp();
        //if auto login popup in andoid 10+ from google saved credentials then click on cancel
        if ($("login.btn.signinwith.cancel").isPresent()) {
            $("login.btn.signinwith.cancel").click();
        }
    }
}
