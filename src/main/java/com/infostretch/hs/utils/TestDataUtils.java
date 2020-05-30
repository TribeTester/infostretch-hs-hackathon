package com.infostretch.hs.utils;

import com.qmetry.qaf.automation.keys.ApplicationProperties;
import java.util.HashMap;
import java.util.Map;
import org.testng.ITestResult;

public class TestDataUtils {

    /**
     * Get current testdata set as a Map
     *
     * @return
     */
    public static Map<String, Object> getCurrentTestcaseTestData() {
        Object[] paramsObj = ((ITestResult) ApplicationProperties.CURRENT_TEST_RESULT.getObject())
            .getParameters();
        if (paramsObj != null && paramsObj.length > 0 && paramsObj[0] instanceof Map) {
            return (Map<String, Object>) paramsObj[0];
        }
        return new HashMap<>();
    }
}
