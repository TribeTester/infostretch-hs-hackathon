package com.infostretch.hs.components.android;

import com.infostretch.hs.components.ICalendar;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import java.util.Date;

public class AndroidCalendar extends QAFWebComponent implements ICalendar {

    public AndroidCalendar() {
        super("");
    }

    @Override
    public void selectDate(Date date) {
    }
}
