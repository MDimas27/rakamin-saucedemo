package com.rakamin.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rakamin.config.env;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Checkout {

	@When("User clicks the cart badge")
	public void user_clicks_the_cart_badge() {
		 // Klik cart badge
		WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));
	    WebElement cartBadge = env.driver.findElement(By.cssSelector(".shopping_cart_badge"));
	    cartBadge.click();
	}

	@And("User is on the Cart page")
	public void user_is_on_the_cart_page() {
		// Pastikan pengguna berada di halaman keranjang belanja
		WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("cart.html"));
	}

	@And("User clicks the Checkout button")
	public void user_clicks_the_checkout_button() {
		// Klik tombol Checkout
        WebElement checkoutButton = env.driver.findElement(By.cssSelector(".checkout_button"));
        checkoutButton.click();
	}

	@And("User fills out the checkout form with {string}, {string}, and {string}")
	public void user_fills_out_the_checkout_form_with_valid_information(String firstName, String lastName, String zipCode) {
	    // Isi formulir checkout dengan informasi dari parameter
	    WebElement firstNameElement = env.driver.findElement(By.id("first-name"));
	    WebElement lastNameElement = env.driver.findElement(By.id("last-name"));
	    WebElement zipCodeElement = env.driver.findElement(By.id("postal-code"));

	    firstNameElement.sendKeys(firstName);
	    lastNameElement.sendKeys(lastName);
	    zipCodeElement.sendKeys(zipCode);
	}

	@And("User clicks the Continue button")
	public void user_clicks_the_continue_button() {
		 // Klik tombol Continue
        WebElement continueButton = env.driver.findElement(By.cssSelector(".cart_button"));
        continueButton.click();
	}

	@Then("User should be on the Checkout Overview page")
	public void user_should_be_on_the_checkout_overview_page() {
		// Periksa apakah pengguna berada di halaman Overview dengan memeriksa judulnya
        WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
	}

	@And("User verifies the details in the overview")
	public void user_verifies_the_details_in_the_overview() {
		 WebElement cartList = env.driver.findElement(By.className("cart_list"));
		    WebElement summaryInfo = env.driver.findElement(By.className("summary_info"));

		    // Jika ingin mengambil elemen-elemen di dalam 'cart_list' atau 'summary_info':
		    List<WebElement> cartItems = cartList.findElements(By.className("cart_item"));
		    List<WebElement> summaryLabels = summaryInfo.findElements(By.className("summary_info_label"));
		    List<WebElement> summaryValues = summaryInfo.findElements(By.className("summary_value_label"));

		    // Selanjutnya, Anda dapat mengakses isi elemen-elemen tersebut seperti yang Anda inginkan, misalnya:
		    String itemDescription = cartItems.get(0).findElement(By.className("inventory_item_desc")).getText();
		    String paymentInfo = summaryValues.get(1).getText();
	}

	@And("User completes the checkout process")
	public void user_completes_the_checkout_process() {
		// Implementasi untuk menyelesaikan proses checkout (misalnya, klik tombol "Finish")
        WebElement finishButton = env.driver.findElement(By.cssSelector(".cart_button"));
        finishButton.click();
	}

	@And("User sees the order confirmation message")
	public void user_sees_the_order_confirmation_message() {
		// Periksa apakah pesan konfirmasi pesanan muncul
        WebElement confirmationMessage = env.driver.findElement(By.cssSelector(".complete-header"));
        assertEquals("Thank you for your order!", confirmationMessage.getText());     
	}

	@And("User decides to cancel the checkout process")
	public void user_decides_to_cancel_the_checkout_process() {
		// Implementasi untuk membatalkan proses checkout (misalnya, klik tombol "Cancel")
        WebElement cancelButton = env.driver.findElement(By.cssSelector(".cart_cancel_link"));
        cancelButton.click();
	}

	@Then("User is redirected back to the shopping cart page")
	public void user_is_redirected_back_to_the_shopping_cart_page() {
		 // Periksa apakah pengguna diarahkan kembali ke halaman keranjang belanja
        WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("cart.html"));
	}



}
