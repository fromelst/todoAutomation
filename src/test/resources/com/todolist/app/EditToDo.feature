
@EditToDo @AutoTests
Feature: Editing a todo

  Scenario: Editing a todo
    Given creating a todo##Hoe kan ik deze gebruiken van de feature file CreateTodo,?
    When a todo gets changed to something else "<newTodoText>"
    Then the text of that todo is something else "<newTodoText>"

    Examples:
    | newTodoText |
    | Like this   |