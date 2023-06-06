package com.todolist.app;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import FrameworkPackage.DriverManager;
import Other.RandomTodoGenerator;
import PageObjects.TodoPage;



public class StepDefinitions {
    private int initialTodoCount;
//    private int initialCompletedTodoCount;
//    private int completedTodoNumber;

    RandomTodoGenerator todoGenerator = new RandomTodoGenerator();
    String todoString;
    String todoStringEdited;

    private DriverManager dm;

    TodoPage todoP;
    WebDriver driver;

    public StepDefinitions(DriverManager _dm) {
        this.dm = _dm;
        this.driver = dm.getDriver();
        this.todoP = new TodoPage();
        PageFactory.initElements(driver, todoP);
    }
    @Before
	public void beforeScenario(Scenario scenario)
	{
		dm.setFirefoxDriver();
		dm.getDriver();
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		dm.killDriver();
	}
	//feature 1 add todo
    @Given("there are no todos")
    public void there_are_no_todos() {
    	//assertTrue(todoP.getTodos().size() ==0 && todoP.getCompletedTodos().size() ==0, "There is at least one todo");
        //assertTrue(todoP.getTodos().size(), 0,"There is at least one todo");
    }

    @When("the user added a todo {string}")
    public void the_user_added_a_todo(String todoString) {
    	System.out.println("-----------------todoString: "+todoString);
    	this.todoString = todoString;
    	todoP.addTodo(todoString);
    }

    @Then("the todo list contains the todo {string}")
    public void the_todo_list_contains_the_todo(String todoString){
    	
        assertTrue(todoP.getTodos().contains(todoString), "The todo " + todoString + " is not displayed");
    }

    @Then("there is only one todo in the todolist")
    public void there_is_only_one_todo_in_the_todolist() {
        assertEquals(todoP.todoAmount(), 1, "The todo list does not contain only one todo");
    }
    
    //feature2 remove todo
    //is hetzelfde als feature 3, hoe dit aanpakken?
    @Given("a certain amount {int} of todos gets added")
    public void a_certain_amount_gets_added(int amount) {
        for (int i = 0; i < amount; i++) {
            String todoString = "todo "+ (i+1);
            todoP.addTodo(todoString);
        }
        initialTodoCount = amount;
    }

    @When("a todo gets removed")
    public void a_todo_gets_removed() throws Exception {
        String todoToRemove = todoP.getTodos().get(0);
        this.todoString = todoToRemove;
        todoP.removeTodo(todoToRemove);
    }

    @Then("there will one todo less")
    public void there_will_be_one_todo_less() {
        int updatedTodoCount = todoP.todoAmount();
        assertEquals(updatedTodoCount, initialTodoCount - 1, "There is not one todo less");
    }

    @Then("the removed todo is not present")
    public void the_removed_todo_is_not_present() {
        assertTrue(todoP.getTodos().contains(todoString), "The removed todo is still present");
    }
    
    //Feature 3 completing a todo
//    @Given("there are {int} todos")
//    public void there_are_todos(int todoCount, List<String> todosList) {
//
//        for (int i = 1; i <= todoCount; i++) {
//            String todoString = "Todo " + (i+1);
//            todoP.addTodo(todoString);
//        }
//
//        initialTodoCount = todoCount;
//        initialCompletedTodoCount = 0;
//    }
    @Given("there are todos")
    public void there_are_todos(List<String> todosList) {

        for (int i = 1; i <= todosList.size(); i++) {
            todoP.addTodo(todosList.get(i));
        }
    }

    @When("the user completed todo number {int}")
    public void the_user_completed_todo_number(int todoNumber) throws Exception {
        String todoToComplete = todoP.getCompletedTodos().get(todoNumber-1);
        todoP.completeTodo(todoToComplete);
    }

    @Then("the todo number {int} is completed")
    public void the_completed_todo_is_completed(int todoNumber) throws Exception {
        String completedTodo = todoP.getCompletedTodos().get(todoNumber-1);
        assertTrue(todoP.isTodoCompleted(completedTodo), "The completed todo is not marked as completed");
    }

    @Then("the number of completed todos is {int}")
    public void the_number_of_completed_todos_is(int expectedCompletedTodoCount) {
        int updatedCompletedTodoCount = todoP.completedTodoAmount();
        assertEquals(updatedCompletedTodoCount, expectedCompletedTodoCount, "The number of completed todos is incorrect");
    }
    
    //Feature4 editing a todo
    @Given("creating a todo")
    public void creating_a_todo() {
    	this.todoString = todoGenerator.generateRandomTodo();
    	the_user_added_a_todo(this.todoString);
    }

    @When("a todo gets changed to something else {string}")
    public void a_todo_gets_changed_to_something_else(String newTodoText) {
    	todoStringEdited = newTodoText;
        todoP.editTodo(0, todoStringEdited, this.driver);
    }

    @Then("the text of that todo is something else {string}")
    public void the_text_of_that_todo_is_something_else(String newTodoText) {
        assertTrue(todoP.getTodos().contains(newTodoText), "The todo did not change");
    }
    
    
    //Feature5 

    @When("the view changes to completed todos")
    public void the_view_changes_to_completed_todos() {
        todoP.toggleCompleted();
    }

    @Then("no todos are shown")
    public void no_todos_are_shown() {
        assertTrue(todoP.getCompletedTodos().size() == 0, "At least one todo is completed");
    }

    @And("the site shows {int} items are left todo")
    public void the_site_shows_how_many_items_are_left_todo(int todosLeft) {
        assertTrue(todoP.getTodosLeft() == todosLeft, "The site does not show the correct amount of todos left");
    }

    @Then("the completed todos are shown")
    public void the_completed_todos_are_shown() {
        //Assert.assertTrue(todoP.getCompletedTodos().get(completedTodoNumber-1) == todoP. , "The site does not show the correct amount of todos left");
    }
}
