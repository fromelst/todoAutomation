@AutoTest @ViewCompletedTodo
Feature: Viewing completed todos

@ViewCompletedTodoEmpty
  Scenario: View of completed todos with no todos done
    Given there are todos
    |todo1|
    |todo2|
    |todo3|
    When the view changes to completed todos
    Then no todos are shown
    And the site shows 3 items are left todo
    
@ViewCompletedTodoItems
  Scenario: View of completed todos with some todos done
    Given there are todos
    |todo1|
    |todo2|
    |todo3|
    When the user completed todo number 2
    And the view changes to completed todos
    Then the completed todos are shown
    And the site shows 2 items are left todo
