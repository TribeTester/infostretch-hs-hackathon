Feature: Hotel Booking
  The user should be able to book a hotel from MMT application by providing check-in, out & payment details.

  Background:
    Given user launches application
	When login to the application with '${username}' and '${password}'
	Then verify user should logged into the application

  @key:hotel.booking
  Scenario: Hotel booking from Make My Trip Application
    When user navigates to the '${menu}'
    And select a location '${location}'
    And select a '${checkin}' and '${checkout}' dates
    And add '${rooms}' rooms for '${adults}' adults & '${children}' children each
    And select travelling for '${tripType}'
    And search for the options

    When user navigates to Sort and Filter page
    And user filters by price by setting '${minimum}' value
    And apply filter by user rating '${rating}'
    Then user applys filter

    When user scroll and select the '${entry}' item from filter result page
    And capture the room details and click SELECT ROOM options
    And user fills traveller information
    And selects two options from commonly requested option
    And uncheck the donation checkbox
    And click on PAY NOW option
    Then verify user should see booking summary page with correct requested data