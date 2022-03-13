package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


// !!don't delete (ctrl+shift+o) ??? WebDriverManager ??? the import is considered unused by the IDE, but it is needed for the webDriverManager
 import io.github.bonigarcia.wdm.WebDriverManager;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSuccess {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
//        System.setProperty("webdriver.chrome.driver", "./drivers/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {

//        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldPlaceAnOrder() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пу-Пкин Вася");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+12345678912");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']"))
                .getText().trim();
        String expected = "demoFAIL";

        assertEquals(expected, actual);

    }
}
