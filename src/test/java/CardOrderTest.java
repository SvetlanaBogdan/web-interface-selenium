import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    WebDriver driver; // переменная драйвера, будет использоваться во всех методах

    @BeforeAll
    public static void setupAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");


        driver = new ChromeDriver(options);


        driver.get("http://localhost:9999");
    }

    @Test
    void shouldSubmitFormSuccessfully() {

        // вводим имя (только русские буквы по условию)
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Крошка Картошка");


        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("+79991234567");

        // ставим галочку согласия
        driver.findElement(By.cssSelector("[data-test-id='agreement']"))
                .click();

        // нажимаем кнопку отправки формы
        driver.findElement(By.cssSelector("button")).click();

        // получаем текст сообщения об успешной отправке
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']"))
                .getText();

        // проверяем, что сообщение содержит ожидаемый текст
        assertTrue(text.contains("Ваша заявка успешно отправлена"));
    }

    @AfterEach
    void tearDown() {
        // закрываем браузер после каждого теста
        driver.quit();
    }
}