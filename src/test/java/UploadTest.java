import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class UploadTest {
    WebDriver driver;
    String relativePath2 = "src/main/resources/testfile.txt";
    File testFile2 = new File(relativePath2);

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
    public void checkUploadTest() {
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.xpath("//input[@type='file']")).//Находим поле для загрузки файла и передаём абсолютный путь к файлу
                sendKeys(testFile2.getAbsolutePath());

        driver.findElement(By.cssSelector("[type=submit]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(//Ожидание названия загруженного файла
                By.id("uploaded-files"), "testfile.txt"));
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "testfile.txt", "Имя файла не совпадает");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
