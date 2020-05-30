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
        String driverName = ApplicationProperties.DRIVER_NAME.getStringVal();

        //if not a remote driver or appium driver then download and setup driver
        if (ConfigurationManager.getBundle().getBoolean(AUTO_DRIVER_MANAGER, false)
            && requiredDriverFile(driverName)) {
            //rename chromeDriver to CHROME and find DriverManagerType instance
            DriverManagerType driverType = DriverManagerType
                .valueOf(driverName.toUpperCase().replace("DRIVER", ""));
            WebDriverManager.getInstance(driverType).setup();
        }
    }

    /**
     * @param driverName driver.name property value
     * @return weaather specified driver required driver file or not
     */
    private static boolean requiredDriverFile(String driverName) {
        driverName = driverName.toLowerCase();
        return !(
            driverName.contains("remote")
                || driverName.contains("appium")
                || driverName.contains("android")
                || driverName.contains("ios"));

    }
}
