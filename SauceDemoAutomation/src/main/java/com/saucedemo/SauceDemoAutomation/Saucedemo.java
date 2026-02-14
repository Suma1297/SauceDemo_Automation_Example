package com.saucedemo.SauceDemoAutomation;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Saucedemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //3. Apply Filter Dropdown
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");

        // 4. Add First 3 Products to Cart
        List<WebElement> addButtons =
                driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

        for (int i = 0; i < 3; i++) {
            addButtons.get(i).click();
        }

        // 5. Open Cart
        driver.findElement(By.className("shopping_cart_link")).click();
        
        driver.findElement(By.id("checkout")).click();

        // 8. Enter Checkout Information
        driver.findElement(By.id("first-name")).sendKeys("Suma");
        driver.findElement(By.id("last-name")).sendKeys("Tester");
        driver.findElement(By.id("postal-code")).sendKeys("560001");

        driver.findElement(By.id("continue")).click();

        // 9. Payment Overview Page
        System.out.println("Payment Summary Displayed");

        // 10. Finish Order
        driver.findElement(By.id("finish")).click();

        // 11. Order Confirmation
        String confirmation =
                driver.findElement(By.className("complete-header")).getText();

        System.out.println("Order Status: " + confirmation);

        driver.quit();

	}

}
