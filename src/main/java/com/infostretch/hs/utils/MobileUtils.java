package com.infostretch.hs.utils;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

import com.qmetry.qaf.automation.keys.ApplicationProperties;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.util.ExpectedCondition;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class MobileUtils {

    public static QAFWebDriver getDriver() {
        return new WebDriverTestBase().getDriver();
    }

    /**
     * @return current {@link AndroidDriver} instance
     */
    public static AndroidDriver<org.openqa.selenium.WebElement> getAndroidDriver() {
        return (AndroidDriver<org.openqa.selenium.WebElement>) new WebDriverTestBase().getDriver()
            .getUnderLayingDriver();
    }

    /**
     * @return current {@link AppiumDriver} instance
     */
    public static AppiumDriver<org.openqa.selenium.WebElement> getMobileDriver() {
        return (AppiumDriver<org.openqa.selenium.WebElement>) new WebDriverTestBase().getDriver()
            .getUnderLayingDriver();
    }

    /**
     * @param targetContext
     */
    public static void switchContext(String targetContext) {
        Set<String> contexts = getMobileDriver().getContextHandles();
        for (String context : contexts) {
            if (context.toLowerCase().contains(targetContext.toLowerCase())) {
                getMobileDriver().context(context);
                break;
            }
        }
    }

    /**
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param duration
     */
    public static void dragAndDrop(int startX, int startY, int endX, int endY, Duration duration) {
        TouchAction<?> touchAction = new TouchAction<>(MobileUtils.getMobileDriver());
        touchAction.press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(endX, endY)).release().perform();
    }

    /**
     * @param startEle
     * @param endEle
     * @param duration
     */
    public static void dragAndDrop(QAFWebElement startEle, QAFWebElement endEle,
        Duration duration) {
        Point startEleLoc = startEle.getLocation();
        Dimension startEleSize = startEle.getSize();
        Point dropEleLoc = endEle.getLocation();
        Dimension dropEleSize = endEle.getSize();
        int startX = startEleLoc.getX() + (startEleSize.getWidth() / 2);
        int startY = startEleLoc.getY() + (startEleSize.getHeight() / 2);
        int dropX = dropEleLoc.getX() + (dropEleSize.getWidth() / 2);
        int dropY = dropEleLoc.getY() + (dropEleSize.getHeight() / 2);
        TouchAction<?> touchAction = new TouchAction<>(MobileUtils.getMobileDriver());
        touchAction.press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(dropX, dropY)).release().perform();
    }

    public static void tap(int x, int y) {
        TouchAction<?> touchAction = new TouchAction<>(MobileUtils.getMobileDriver());
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    public static void waitForContext(final String targetContext, long... timeout) {
        long waitTime = ApplicationProperties.SELENIUM_WAIT_TIMEOUT.getIntVal();
        if (timeout != null && timeout.length > 0) {
            waitTime = timeout[0];
        }
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getMobileDriver())
            .withTimeout(Duration.of(waitTime, ChronoUnit.MILLIS))
            .pollingEvery(Duration.ofSeconds(2));
        wait.until(new ExpectedCondition<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                Set<String> contexts = getMobileDriver().getContextHandles();
                for (String context : contexts) {
                    if (context.toLowerCase().contains(targetContext.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static boolean isAndroid() {
        return getDriver().getCapabilities().getCapability("platformName").toString()
            .equalsIgnoreCase("android");
    }

    public static void swipeVertically() {
        Dimension dsize = getDriver().manage().window().getSize();
        int startY = (int) (dsize.height * 0.75);
        int endY = (int) (dsize.height * 0.25);
        int x = (dsize.width / 2);
        dragAndDrop(x, startY, x, endY, Duration.ofSeconds(2));
    }

    public static QAFWebElement findElementByScrolling(String locator, int maxScroll) {
        int count = 0;
        boolean elementFound = $(locator).isPresent();
        while (!elementFound && count < maxScroll) {
            swipeVertically();
            elementFound = $(locator).isPresent();
            if (elementFound) {
                //swipe once more to make element in center
                swipeVertically();
                break;
            }
            count += 1;
        }
        return $(locator);
    }
}
