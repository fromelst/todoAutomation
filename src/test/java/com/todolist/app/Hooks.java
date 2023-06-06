package com.todolist.app;

import FrameworkPackage.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private DriverManager dm = new DriverManager();
	
	public Hooks(DriverManager dm) {
		this.dm = dm;
		
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
	
}
