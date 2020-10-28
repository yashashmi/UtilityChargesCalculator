#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Calculate energy Charges
  

  Scenario: Number of units is less than 50
    Given I have consumed 40 units in current month
    When I submit the input units
    Then I should be able to view total energy charges as 162

  Scenario: Number of units is between 51 and 150
    Given I have consumed 60 units in current month
    When I submit the input units
    Then I should be able to view energy charges for first 50 units as 202.5
    And energy charges for remaining 10 units as 49.5
    And total energy charges as 252
    
  Scenario: Number of units is between 151 and 300
    Given I have consumed 200 units in current month
    When I submit the input units
    Then I should be able to view energy charges for first 50 units as 202.5
    And energy charges for next 100 units as 495
    And energy charges for remaining 50 units as 315
    And total energy charges as 1012.5

  Scenario: Number of units is above 300
    Given I have consumed 400 units in current month
    When I submit the input units
    Then I should be able to view energy charges for first 50 units as 202.5
    And energy charges for next 100 units as 495
    And energy charges for next 150 units as 945
    And energy charges for remaining 100 units is 650
    And total energy charges as 2292.5
    
  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
