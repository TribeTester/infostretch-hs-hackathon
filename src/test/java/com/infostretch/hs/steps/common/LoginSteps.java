package com.infostretch.hs.steps.common;

import com.qmetry.qaf.automation.step.QAFTestStep;

public class LoginSteps {

    /**
     * Login with provided username and password
     *
     * @param username
     * @param password
     */
    @QAFTestStep(description = "login to the application with {username} and {password}")
    public void login(String username, String password) {
    }


    /**
     * Verify user is logged in application
     */
    @QAFTestStep(description = "verify user should logged into the application")
    public void verifyLoggedIn() {
    }
}
