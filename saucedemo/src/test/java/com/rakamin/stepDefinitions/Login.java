package com.rakamin.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rakamin.config.env;
import com.rakamin.objectRepository.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

	public int duration = 10;
	WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(duration));
	LoginPage elementLogin = new LoginPage();

	@Given("user on the Saucedemo login page")
	public void user_on_the_Saucedemo_login_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementLogin.getBtn_login()));
	}

	@When("user input (.*) as username$")
	public void user_input_username(String username) {
		WebElement field_userName = env.driver.findElement(elementLogin.getField_username());
		field_userName.isDisplayed();
		field_userName.sendKeys(username);
	}

	@And("user input (.*) as password$")
	public void user_input_password(String password) {
		WebElement field_password = env.driver.findElement(elementLogin.getField_password());
		Assert.assertTrue(field_password.isEnabled());
		field_password.sendKeys(password);
	}

	@And("user click enter")
	public void user_click_enter() {
		WebElement field_password = env.driver.findElement(elementLogin.getField_password());
		field_password.sendKeys(Keys.ENTER);
	}

	@Then("user verify (.*) login result$")
	public void user_verify_login_result(String status) {
		WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));

		try {
			if (status.equals("successful")) {
				WebElement productsHeader = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
				// Mengecek teks pada elemen
				assertEquals("Products", productsHeader.getText());
			} else if (status.equals("unsuccessful")) {
				WebElement errorButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-button")));
				assertNotNull(errorButton);
			} else {
				fail("Invalid login status provided: " + status);
			}
		} catch (TimeoutException e) {
			fail("Login verification failed: " + e.getMessage());
		}

	}
}
