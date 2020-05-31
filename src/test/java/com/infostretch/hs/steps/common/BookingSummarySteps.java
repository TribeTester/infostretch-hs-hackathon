package com.infostretch.hs.steps.common;

import static com.infostretch.hs.utils.LocatorUtils.$;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import com.infostretch.hs.utils.Constants;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.StringMatcher;

public class BookingSummarySteps {

    @QAFTestStep(description = "verify user should see booking summary page with correct requested data")
    public void verifyBookingSummary() {
        //verify hotel name
//        String expectedHotelName = getBundle().getString(Constants.SELECTED_HOTEL);
//        $("booking.review.txt.hotel.name")
//            .verifyText(StringMatcher.containsIgnoringCase(expectedHotelName));

        //verify selected city
        String expectedCity = getBundle().getString(Constants.SELECTED_CITY).split(",")[0];
        $("booking.review.txt.hotel.address")
            .verifyText(StringMatcher.containsIgnoringCase(expectedCity));

        //click on expand button
        CommonStep.click("booking.review.btn.expand.details");

        //verify expected guest
        int expectedGuestCounts = getBundle().getInt(Constants.SELECTED_GUESTS_COUNT);
        $("booking.review.txt.guests")
            .verifyText(StringMatcher.containsIgnoringCase(String.valueOf(expectedGuestCounts)));

        //verify expected rooms
        int expectedRoomCounts = getBundle().getInt(Constants.SELECTED_ROOMS_COUNT);
        $("booking.review.txt.rooms")
            .verifyText(StringMatcher.containsIgnoringCase(String.valueOf(expectedRoomCounts)));

        //verify checkin date
        String expectedCheckinDate = getBundle().getString(Constants.SELECTED_CHECKIN_DATE);
        $("booking.review.txt.checkin.date")
            .verifyText(StringMatcher.containsIgnoringCase(String.valueOf(expectedCheckinDate)));

        //verify checkout date
        String expectedCheckoutDate = getBundle().getString(Constants.SELECTED_CHECKOUT_DATE);
        $("booking.review.txt.checkout.date")
            .verifyText(StringMatcher.containsIgnoringCase(String.valueOf(expectedCheckoutDate)));
    }

}
