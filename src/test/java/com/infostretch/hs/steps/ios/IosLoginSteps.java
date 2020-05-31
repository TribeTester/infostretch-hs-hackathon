package com.infostretch.hs.steps.ios;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.infostretch.hs.steps.common.LoginSteps;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class IosLoginSteps extends LoginSteps {
	/**
     * Login with provided username and password
     *
     * @param username
     * @param password
     */
    @QAFTestStep(description = "login to the application with {username} and {password}")
    public void login(String username, String password) {
    	
    	if ($("system.alert.allow.button").isPresent()) {
    		$("system.alert.allow.button").click();
    	}
       
        natigateToLoginScreen();
        
        if ($("login.btn.email.clear").isPresent()) {
            CommonStep.click("login.btn.email.clear");
        }
        
        CommonStep.sendKeys(username, "login.input.email");
        CommonStep.click("login.btn.continue");
        CommonStep.click("login.btn.via.password");
        CommonStep.sendKeys(password, "login.input.password");
        CommonStep.click("login.btn.submit");
        
        $("navigate.back.button").click();
    }
    
    private void natigateToLoginScreen() {
    	if ($("home.icon.profile").isPresent()) {
        	$("home.icon.profile").click();
        	$("home.login.signup.section").click();
        }
    }
}
