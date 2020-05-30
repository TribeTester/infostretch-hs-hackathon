package com.infostretch.hs.components;

import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import java.util.Date;

public interface ICalendar extends QAFWebElement {
    void selectDate(Date date);
}
