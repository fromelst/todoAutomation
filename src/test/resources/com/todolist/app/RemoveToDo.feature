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
@RemoveTodo @AutoTests
Feature: Removing a todo

  Scenario Outline: Removing a todos
    Given There are no todos ##kan de start zijn voor iedere test?
    And a certain amount 4 of todos gets added
    When a todo gets removed
    Then there will one todo less
    And the removed todo is not present
    
    #Examples:
    #|amountToDoStart|
    #|4							|
    #|7							|
