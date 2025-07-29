import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DemoTest {

    @Test

    public void seleniumTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

    }
}
