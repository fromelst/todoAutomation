@CompleteTodo @AutoTests
Feature: Completing a todo

Scenario Outline: 
Given there are todos
    |todo1|
    |todo2|
    |todo3|
When the user completed todo number 3
Then the todo number 3 is completed
And the number of completed todos is 1