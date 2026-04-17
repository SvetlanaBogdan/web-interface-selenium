import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    @BeforeAll
    public static void setupAll() {
        // один раз скачиваем и настраиваем драйвер для Chrome
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void shouldSubmitFormSuccessfully() {

        // настраиваем браузер для headless-режима
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        // создаём драйвер
        WebDriver driver = new ChromeDriver(options);

        // открываем страницу
        driver.get("http://localhost:9999");

        // вводим имя (русские буквы)
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Крошка Картошка");

        // вводим телефон
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("+79991234567");

        // ставим галочку
        driver.findElement(By.cssSelector("[data-test-id='agreement']"))
                .click();

        // нажимаем кнопку
        driver.findElement(By.cssSelector("button")).click();

        // проверяем сообщение об успехе
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']"))
                .getText();

        assertTrue(text.contains("успешно отправлена"));

        // закрываем браузер
        driver.quit();
    }
}