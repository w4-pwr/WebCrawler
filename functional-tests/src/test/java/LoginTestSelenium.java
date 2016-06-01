
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginTestSelenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://s3-us-west-2.amazonaws.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginTestSelenium() throws Exception {
    driver.get(baseUrl + "/elasticbeanstalk-us-west-2-493126854002/frontend/index.html#/?_k=6o0g5d");
    driver.findElement(By.cssSelector("span.hidden-xs")).click();
    driver.findElement(By.cssSelector("li.user-header.sign-in > #email")).clear();
    driver.findElement(By.cssSelector("li.user-header.sign-in > #email")).sendKeys("testtest@gmail.com");
    driver.findElement(By.cssSelector("li.user-header.sign-in > #password")).clear();
    driver.findElement(By.cssSelector("li.user-header.sign-in > #password")).sendKeys("testtest");
    driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
