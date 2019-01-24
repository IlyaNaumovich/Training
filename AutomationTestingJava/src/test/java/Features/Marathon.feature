@Marathon
Feature: Marathon feature file contains scenarios related to marathonbet.com

  Background:
    Given I am on the main Marathon page

    @Login
  Scenario: Negative login (imperative)
    When I enter "Привет!" as a username
    And I enter "World" as a password
    And I click login button
    Then I should see the notification of wrong credentials

  @Login @Regression
  Scenario: Negative login (declarative)
    When I login with Hello and World credentials
    Then I should see the notification of wrong credentials

  @Regression
  Scenario Outline: Negative login (data provider)
    When I enter "<username>" as a username
    And I enter "<password>" as a password
    And I click login button
    Then I should see the notification of wrong credentials
    Examples:
      | username | password |
      | Hello    | World    |
      | Привет   | Валет    |
      | 123      | asd1     |




