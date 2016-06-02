package pwr.po.webcrawler.web.controller.pwr.po.webcrawler.web.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginTest {

   private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    public void shouldBeAbleToLogin() {
        routeToLoginForm();
        fillFormWithCorrectData();
        submitForm();
        checkIfLoggedSuccessInfoIsShown();
    }

    @Test
    public void shouldNotBeAbleToLoginIfPasswordIsIncorrect() {
        routeToLoginForm();
        fillFormWithInCorrectData();
        submitForm();
        checkIfLoggedErrorInfoIsShown();
    }

    private void typeDataInInput(String inputId, String value) {
        WebElement element = driver.findElement(By.id(inputId));
        element.sendKeys(value);
    }

    private void routeToLoginForm() {
        driver.manage().window().maximize();
        driver.get("https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-493126854002/frontend/index.html#/?_k=ma60oa");
        driver.findElement(By.id("sign-in-button")).click(); //klikniecie w SignIn
    }

    private void submitForm() {
        driver.findElement(By.xpath("html/body/div/div/div/header/nav/div/ul/li/ul/li/div/a")).click();
    }

    private void fillFormWithInCorrectData() {
        typeDataInInput("email","rkpieniazek@gmail.com");
        typeDataInInput("password","nieprawidloweHaslo!");
    }

    private void fillFormWithCorrectData() {
        typeDataInInput("email","rkpieniazek@gmail.com");
        typeDataInInput("password","Haslo124!");
    }

    private void checkIfLoggedErrorInfoIsShown() {
        assertNotNull(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")));
        assertEquals(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")).getText(),"Nieprawidłowy email lub hasło!");
    }

    private void checkIfLoggedSuccessInfoIsShown() {
        assertNotNull(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")));
        assertEquals(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")).getText(),"Zalogowano!");
    }

}
