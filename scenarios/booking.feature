Feature: Hotels booking from Make My Trip Application
    The user should be able to book a hotel into the Make My Trip application from given check-in, out & payment details.

Background:
    Given a user launches application
    And login to the application with '${username}' and '${password}'
    Then verify user should logged into the application

Scenario:
    When a user navigates to the '${menu}'
    And select a '${location}'
    And select a '${checkin}' and '${checkout}' dates
    And select rooms details with '${adults}' & '${children}'
    And select guests details with '${adults}' & '${children}'
    And select travelling '${for}'
    And search for the options
    Then user should see list of available options

	When a user on search result page and filter by price by setting '${minimum}' value
	And apply filter by user '${rating}' & above
	Then verify filters applied and user should see filter result page

	When a user scroll and select the '${entry}' item from filter result page
	And capture the room details and '${select_room}' option
	And user fills traveller information
	And selects two options from commonly requested option
	And uncheck the donation checkbox
	And click on PAY NOW option
	Then verify user should see booking summary page with correct requested data
