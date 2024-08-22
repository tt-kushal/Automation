Feature: Shopping Automation
  Scenario: Testing Authentication
    Given I visit the Website
    When I click the SignIn button
    And I specify the credentials and click login
    Then I can log into the website

  Scenario: adding two products
    Given I visit the Website
    When I add two products
    And add i proceed to checkout
    And i confirm address,payment and final order
    Then the products are bought