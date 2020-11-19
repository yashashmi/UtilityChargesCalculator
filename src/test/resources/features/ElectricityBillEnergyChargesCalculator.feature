Feature: Calculate energy Charges

  Scenario: Number of units is less than 50
    Given I have consumed 40 units in current month
    When I submit the input units
    Then I should see total energy charges as 162.0

  Scenario: Number of units is between 51 and 150
    Given I have consumed 60 units in current month
    When I submit the input units
    Then I should see energy charges for first slab as 202.5
    And I should see energy charges for second slab as 49.5
    And I should see total energy charges as 252.0

  Scenario: Number of units is between 151 and 300
    Given I have consumed 200 units in current month
    When I submit the input units
    Then I should see energy charges for first slab as 202.5
    And I should see energy charges for second slab as 495.0
    And I should see energy charges for third slab as 315.0
    And I should see total energy charges as 1012.5

  Scenario: Number of units is above 300
    Given I have consumed 400 units in current month
    When I submit the input units
    Then I should see energy charges for first slab as 202.5
    And I should see energy charges for second slab as 495.0
    And I should see energy charges for third slab as 945.0
    And I should see energy charges for last slab as 650.0
    And I should see total energy charges as 2292.5
