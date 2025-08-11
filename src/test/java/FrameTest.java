import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class FrameTest {

    WebDriver driver;

    private final By IFRAME = By.tagName("iframe");
    private final By PARAGRAPH = By.tagName("p");
    private final String EXPECTED_TEXT = "Your content goes here.";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkIFrameContent() {
driver.get("https://the-internet.herokuapp.com/iframe");
        // 1. Переключаемся на iframe
        WebElement iframe = driver.findElement(IFRAME);
        driver.switchTo().frame(iframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 2. Находим параграф и проверяем текст
        WebElement paragraph = wait.until(ExpectedConditions.presenceOfElementLocated(PARAGRAPH));
        String actualText = paragraph.getText();
        // 3. Проверяем соответствие текста
        Assert.assertEquals(actualText, EXPECTED_TEXT,
                "Текст внутри iframe не соответствует ожидаемому");
        // 4. Возвращаемся к основному контенту
        driver.switchTo().defaultContent();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}


