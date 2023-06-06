package TestDriverAndTodopage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import FrameworkPackage.DriverManager;
import Other.RandomTodoGenerator;
import PageObjects.TodoPage;


public class Testclass {
	public static void main(String[] args) throws Exception {
        RandomTodoGenerator todoGenerator = new RandomTodoGenerator();

		DriverManager dm = new DriverManager();
		dm.setFirefoxDriver();
		WebDriver driver = dm.getDriver();

		TodoPage todoP = new TodoPage();
		
		PageFactory.initElements(driver, todoP);
		
		String todoString1 = todoGenerator.generateRandomTodo();
		String todoString2 = todoGenerator.generateRandomTodo();
		String todoString3 = todoGenerator.generateRandomTodo();

		
		todoP.addTodo(todoString1);
		todoP.addTodo(todoString2);
		todoP.addTodo(todoString3);
		todoP.getTodos();
		todoP.completeTodo(todoString1);
		todoP.completeTodo(todoString3);
        Thread.sleep(3000);
        
        //WebDriverWait wait = new WebDriverWait(driver, 10);

        //wait.until(ExpectedConditions.visibilityOfAllElements(todoP.allTodos));

	
        Thread.sleep(3000);
        todoP.editTodo(1, "dit is de todoedit!", driver);
		//todoP.RemoveAllTodo();
        Thread.sleep(3000);

		dm.killDriver();
	}	
}
