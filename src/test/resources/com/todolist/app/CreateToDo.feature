@CreateTodo @AutoTests
Feature: Creating a to do

Scenario Outline: 
Given there are no todos 
When the user added a todo "Automation is fun!"
Then the todo list contains the todo "Automation is fun!"
And there is only one todo in the todolist
Examples:
|todoText						|
|Automation is fun!	|