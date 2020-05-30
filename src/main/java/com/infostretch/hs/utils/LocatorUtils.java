package com.infostretch.hs.utils;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class LocatorUtils {

    /**
     * @param key  locator key
     * @param args arguments to replace in locator value
     * @return
     */
    public static QAFWebElement getDynamicLocator(String key, Object... args) {
        return $(String.format(ConfigurationManager.getBundle().getString(key, key), args));
    }
}
