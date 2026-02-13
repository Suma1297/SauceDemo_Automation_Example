package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class GoogleTest {
    @Test
    public void openGoogle() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1. Launch Application
        driver.get("https://www.saucedemo.com/");

        // 2. Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 3. Apply Filter Dropdown
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


        // 7. Click Checkout
        //driver.findElement(By.id("checkout")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();


        // 8. Enter Checkout Information
        driver.findElement(By.id("first-name")).sendKeys("Suma");
        driver.findElement(By.id("last-name")).sendKeys("Tester");
        driver.findElement(By.id("postal-code")).sendKeys("560001");

        TakesScreenshot ts1 = (TakesScreenshot) driver;
        File src1 = ts1.getScreenshotAs(OutputType.FILE);
        File dest1 = new File("screenshots/error1.png");
        //FileUtils.copyFile(src1, dest1);
        try {
            FileUtils.copyFile(src1, dest1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("continue")).click();

        // 9. Payment Overview Page
        System.out.println("Payment Summary Displayed");

        // 10. Finish Order
        driver.findElement(By.id("finish")).click();

        // 11. Order Confirmation
        String confirmation =
                driver.findElement(By.className("complete-header")).getText();

        System.out.println("Order Status: " + confirmation);

        //driver.quit();


        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/error.png");
        //FileUtils.copyFile(src, dest);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
