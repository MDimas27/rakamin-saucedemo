package com.rakamin.stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rakamin.config.env;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout {

	@And("user should be on the inventory page")
	public void user_should_be_on_the_inventory_page() {
		WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));
		String inventoryPageURL = "https://www.saucedemo.com/inventory.html";
		env.driver.get(inventoryPageURL);
	}

	@When("user clicks the logout button")
	public void user_clicks_the_logout_button() {
		// Implement code to click the burger menu button
		WebElement burgerMenuButton = env.driver.findElement(By.id("react-burger-menu-btn"));
		burgerMenuButton.click();

		WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));

		// Implement code to click the logout button
		WebElement logoutButton = env.driver.findElement(By.id("logout_sidebar_link"));
		logoutButton.click();
	}

	@Then("user should be logged out and on the login page")
	public void user_should_be_logged_out_and_on_the_login_page() {
		String loginPageURL = "https://www.saucedemo.com/";
		Assert.assertEquals(loginPageURL, env.driver.getCurrentUrl());
	}

}
