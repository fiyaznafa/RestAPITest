package com.sel.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SelTest {

	
	@Test
	public void testSel() {
		WebDriver driver = new ChromeDriver();
		Select sel = new Select(driver.findElement(By.id("d")));
	}
}
