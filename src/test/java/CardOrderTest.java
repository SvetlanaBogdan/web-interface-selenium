import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    @BeforeAll
    public static void setupAll() {
        // один раз скачиваем и настраиваем драйвер для Chrome
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void shouldOpenPage() {

        // настраиваем браузер для запуска в headless-режиме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        // создаём экземпляр браузера Chrome с настройками
        WebDriver driver = new ChromeDriver(options);

        // открываем страницу приложения
        driver.get("http://localhost:9999");

        // получаем заголовок страницы
        String actualTitle = driver.getTitle();

        // проверяем, что страница содержит нужный текст
        assertTrue(actualTitle.contains("Заявка"));

        // закрываем браузер
        driver.quit();
    }
}