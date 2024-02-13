Feature: adding Langauge functionalty
  @sprint2 @tc1110
  Scenario: adding language
  When user enters valid username and password
    And user clicks on login button
    When user user clicks on admin button
    And user clicks on qualifications option
    When user clicks on languages option
    And user clicks on Add button
    When user enters a language name
    And user clicks on Save button
    Then language should be added successfully
