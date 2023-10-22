package com.rakamin.stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rakamin.config.env;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart {

	public int duration = 10;
	WebDriverWait wait = new WebDriverWait(env.driver, Duration.ofSeconds(duration));

	@Given("I am on the Saucedemo products page")
	public void i_am_on_the_saucedemo_products_page() {
		// Uji URL untuk memastikan bahwa Anda berada di halaman yang benar
		if (!env.driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
			throw new IllegalStateException("Anda tidak berada di halaman yang diharapkan.");
		}
	}

	@When("I add a product with the name {string} to the cart")
	public void i_add_product_to_cart(String productName) {
		WebElement product = env.driver.findElement(By.className("inventory_item_name"));
		String productText = product.getText();

		if (productText.equals(productName)) {
			WebElement addButton = env.driver.findElement(By.className("btn_inventory"));
			addButton.click();
		} else {
			throw new NoSuchElementException("Product not found: " + productName);
		}
	}

	@Then("I should see the product in the cart")
	public void i_should_see_the_product_in_the_cart() {
		WebElement cartBadge = env.driver.findElement(By.className("shopping_cart_badge"));
		assert cartBadge.isDisplayed();
	}

	@And("I have a product {string} in my cart")
	public void i_have_a_product_in_my_cart(String productName) {
		i_am_on_the_saucedemo_products_page();
		i_add_product_to_cart(productName);
	}

	@When("I remove the product from the cart")
	public void i_remove_the_product_from_the_cart() {
		WebElement cartIcon = env.driver.findElement(By.className("shopping_cart_link"));
		cartIcon.click();
		// Mencari tombol "Remove" berdasarkan atribut data-test
		WebElement removeButton = env.driver
				.findElement(By.cssSelector("button[data-test='remove-sauce-labs-backpack']"));

		// Mengklik tombol "Remove" untuk menghapus produk
		removeButton.click();
	}

	@Then("I should not see the product in the cart")
	public void i_should_not_see_the_product_in_the_cart() {
		try {
	        WebElement cartBadge = env.driver.findElement(By.className("shopping_cart_badge"));
	        assert !cartBadge.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // Penanganan jika elemen tidak ditemukan, yang menunjukkan produk telah dihapus dari keranjang belanja.
	        System.out.println("Product has been successfully removed from the cart.");
	    }
	}

}
