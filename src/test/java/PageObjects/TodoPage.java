package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * This class represents the TodoPage page object.
 * It contains methods to interact with the elements on the todo page.
 */
public class TodoPage {
    @FindBy(how = How.CLASS_NAME, using = "new-todo")
    private WebElement inputNewTodo;

    @FindBy(how = How.XPATH, using = "//a[@class='selected']")
    private WebElement toggleSelected;

    @FindBy(xpath = "//a[@class='completed']")
    private WebElement toggleCompleted;

    @FindBy(xpath = "//a[@class='all']")
    private WebElement toggleAll;
    
    @FindBy(xpath = "//span[@class='todo-count']//strong")
    private WebElement todosLeft;

    @FindAll({@FindBy(xpath = "//ul[@class='todo-list']//label")})
    public List<WebElement> allTodos;

    @FindAll({@FindBy(xpath = "//li[contains(@class,'completed')]//label")})
    public List<WebElement> completedTodos;
    
    /**
     * Adds a new todo item.
     *
     * @param todoToAdd The todo item to add.
     * @return The TodoPage instance.
     */
    public TodoPage addTodo(String todoToAdd) {
        inputNewTodo.sendKeys(todoToAdd);
        inputNewTodo.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Finds the todo element with the given text.
     *
     * @param todoTextToFind The text of the todo element to find.
     * @return The WebElement representing the todo element.
     * @throws Exception if the todo element is not found.
     */
    public WebElement findTodoElement(String todoTextToFind) throws Exception {
    	System.out.println("todotext: "+ todoTextToFind);
    	if(getTodos().contains(todoTextToFind)) {
    		for (int i = 0; i < allTodos.size(); i++) {
                WebElement todo = allTodos.get(i);
                if (todo.getText().equals(todoTextToFind)) {
                	System.out.println("return todo: "+todo);
                    return todo;
                }
                System.out.println("no todo to return!");
            }
    	}
        throw new Exception("---Unable to find todo element to remove");
    }

	/**
	 * Completes a todo item with the given text.
	 *
	 * @param todoTextToRemove The text of the todo item to complete.
	 * @return The TodoPage instance.
	 * @throws Exception if the todo item is not found.
	 */
	public TodoPage completeTodo(String todoTextToComplete) throws Exception {
	    WebElement todoToComplete = findTodoElement(todoTextToComplete);
	    WebElement checkboxTodoToComplete = todoToComplete.findElement(By.xpath("./preceding-sibling::input"));
	    System.out.println("checkboxTodoToComplete: "+ checkboxTodoToComplete);
	    checkboxTodoToComplete.click();
	    return this;
	}
	
	/**
	 * Removes a todo item with the given text.
	 *
	 * @param todoTextToRemove The text of the todo item to remove.
	 * @return The TodoPage instance.
	 * @throws Exception if the todo item is not found.
	 */
	public TodoPage removeTodo(String todoTextToRemove) throws Exception {
	    WebElement todoToRemove = findTodoElement(todoTextToRemove);
	    WebElement checkboxTodoToRemove = todoToRemove.findElement(By.xpath("./following-sibling::button"));
	    checkboxTodoToRemove.click();
	    return this;
	}

    /**
     * Retrieves the list of todo items as strings.
     *
     * @return The list of todo items as strings.
     */
    public List<String> getTodos() {
        List<String> todosString = new ArrayList<String>();

        for (int i = 0; i < allTodos.size(); i++) {
            todosString.add(allTodos.get(i).getText());
        }
        System.out.println(todosString);
        return todosString;
    }
    
    /**
     * Edits a todo item at the specified index.
     *
     * @param todoIndex   The index of the todo item to edit.
     * @param textTodoToBe The new text to set for the todo item.
     * @return The TodoPage instance.
     */
    public TodoPage editTodo(int todoIndex, String textTodoToBe, WebDriver driver) {
        WebElement todo = allTodos.get(todoIndex);
        
        Actions actions = new Actions(driver);
        actions.doubleClick(todo).perform();
        WebElement editableElement = todo.findElement(By.xpath("./../input[@class='edit']"));        
        editableElement.clear();
        editableElement.sendKeys(textTodoToBe);
        editableElement.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Returns the number of todo items.
     *
     * @return The number of todo items.
     */
    public int todoAmount() {
        return allTodos.size();
    }

    /**
     * Removes all todo items.
     *
     * @return The TodoPage instance.
     */
    public TodoPage RemoveAllTodo() {
        for (int i = 0; i < allTodos.size(); i++) {
            allTodos.get(i).findElement(By.xpath("./following-sibling::button")).click();
        }
        return this;
    }

	public List<String> getCompletedTodos() {
		List<String> completedTodosString = new ArrayList<String>();

        for (int i = 0; i < allTodos.size(); i++) {
        	completedTodosString.add(allTodos.get(i).getText());
        }
        System.out.println(completedTodosString);
        return completedTodosString;
	}
	
	public int completedTodoAmount() {
		return completedTodos.size();
	}

	public boolean isTodoCompleted(String completedTodo) {
		if(getCompletedTodos().contains(completedTodo)) {
			return true;
		}
		return false;
	}
	
	public TodoPage toggleCompleted() {
		toggleCompleted.click();
		return this;
	}
	
	public int getTodosLeft() {
		return Integer.parseInt(todosLeft.getText());
	}
	
	
}