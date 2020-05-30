package com.infostretch.hs.utils;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.keys.ApplicationProperties;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriverCommandAdapter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Capabilities;

public class WebDriverListener extends QAFWebDriverCommandAdapter {

    private static final String AUTO_DRIVER_MANAGER = "webdriver.manage.auto";

    @Override
    public void beforeInitialize(Capabilities desiredCapabilities) {
        String driverName = ApplicationProperties.DRIVER_NAME.getStringVal().toLowerCase();

        //if not a remote driver or appium driver then download and setup driver
        if (ConfigurationManager.getBundle().getBoolean(AUTO_DRIVER_MANAGER, false)
            && !driverName.contains("remote") && !driverName.contains("appium")) {

            //rename chromeDriver to CHROME and find DriverManagerType instance
            DriverManagerType driverType = DriverManagerType
                .valueOf(driverName.replace("driver", "").toUpperCase());
            WebDriverManager.getInstance(driverType).setup();
        }
    }
}
