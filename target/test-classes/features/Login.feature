Feature: Login functionality
  @sprint3 @regression @tc1101
  Scenario: valid admin login
    #Given user is navigated to HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is successfully logged in

    @regression @tc1102
    Scenario: Valid ess login
     # Given user is navigated to HRMS application
      When user enters ess username and ess password
      And user clicks on login button
      Then user is successfully logged in

  @tc1103
  Scenario:
   # Given user is navigated to HRMS application
    When user enter invalid username and password
    And user clicks on login button
    Then error message displayed

  @tc1105
    Scenario Outline: Invalid login functionality
      When user enters different "<username>" and "<password>" and verify the "<error>" for it
      Examples:
        | username | password | error |
        |admin     |cristiano |Invalid credentials|
        |ronaldo   |Hum@nhrm1233|Invalid credentials|
        |          |Hum@nhrm123|Username cannot be empty|
        |admin     |           |Password cannot be empty|


