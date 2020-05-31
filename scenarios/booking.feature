Feature: Hotel Booking
  The user should be able to book a hotel into the Make My Trip application from given check-in, out & payment details.

  Background:
    Given user launches application
    And login to the application with '${username}' and '${password}'
    Then verify user should logged into the application

  @key:hotel.booking
  Scenario: Hotel booking from Make My Trip Application
    When user navigates to the '${menu}'
    And select a location '${location}'
    
    #TODO: Need to change this step for random dates
    And select a '${checkin}' and '${checkout}' dates
    
    And add '${rooms}' rooms for '${adults}' adults & '${children}' children each
    
    And select travelling for '${tripType}'
    And search for the options
    Then user should see list of available options
	
	When user on search result page and filter by price by setting '${minimum}' value
	And apply filter by user rating '${rating}' & above
	Then verify filters applied and user should see filter result page

    When user scroll and select the '${entry}' item from filter result page
    And capture the room details and '${select_room}' option
    And user fills traveller information
    And selects two options from commonly requested option
    And uncheck the donation checkbox
    And click on PAY NOW option
    Then verify user should see booking summary page with correct requested data
