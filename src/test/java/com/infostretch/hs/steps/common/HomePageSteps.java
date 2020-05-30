package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class HomePageSteps {

    /**
     * Launch AUT on target platform
     */
    @QAFTestStep(description = "user launches application")
    public void launchApp() {
    }

    @QAFTestStep(description = "user navigates to the {menu}")
    public void navigateToMenu(String menu) {
    }

}
