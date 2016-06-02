package pwr.po.webcrawler.web.controller.pwr.po.webcrawler.web.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class RegisterTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void shouldRegisterNewUserWhenCorrectDataProvided() {
        routeToRegisterForm();
        fillRegisterFormWithCorrectData();
        submitForm();
        checkIfWarningIsShown();
        checkIfWarningHasInfoLike("Na Twój adres e-mail został wysłany link aktywacyjny, sprawdź maila aby aktywować konto");
    }

    @Test
    public void shouldShowWarningWhenPasswordAreNotEqual() {
        routeToRegisterForm();
        fillRegisterFormWithNotEqualPasswords();
        submitForm();
        checkIfWarningIsShown();
        checkIfWarningHasInfoLike("Podane hasła nie są takie same.");
    }

    @Test
    public void shouldShowWarningWhenEmailExistsInDatabase() {
        routeToRegisterForm();
        fillRegisterFormWithCorrectData();
        submitForm();
        checkIfWarningIsShown();
        checkIfWarningHasInfoLike("Podany mail/username jest już zarejestrowany w systemie");
    }

    @Test
    public void shouldShowWarningWhenPasswordNotValid() {
        routeToRegisterForm();
        fillRegisterFormInvalidPassword();
        submitForm();
        checkIfWarningIsShown();
        checkIfWarningHasInfoLike("Hasło nie może być krótsze niz 8 znaków powinno zawierać małe litery, wielkie litery i cyfry!");
    }

    private void typeDataInInput(String inputId, String value) {
        WebElement element = driver.findElement(By.id(inputId));
        element.sendKeys(value);
    }

    private void checkIfWarningHasInfoLike(String actual) {
        assertEquals(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")).getText(), actual);
    }

    private void checkIfWarningIsShown() {
        assertNotNull(driver.findElement(By.xpath("html/body/div/div/div/div/section/h1")));
    }

    private void routeToRegisterForm() {
        driver.manage().window().maximize();
        driver.get("https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-493126854002/frontend/index.html#/?_k=ma60oa");
        driver.findElement(By.xpath("html/body/div/div/div/header/nav/div/ul/li/a")).click(); //klikniecie w SignIn
    }

    private void submitForm() {
        driver.findElement(By.xpath("html/body/div/div/div/header/nav/div/ul/li/ul/li/div/a")).click();
    }

    private void fillRegisterFormWithNotEqualPasswords() {
        typeDataInInput("username","rkpieniazek");
        typeDataInInput("email","rkpieniazek@gmail.com");
        typeDataInInput("password","innehaselo124!");
        typeDataInInput("password2","innehaselo124!");
    }

    private void fillRegisterFormWithCorrectData() {
        typeDataInInput("username","rkpieniazek");
        typeDataInInput("email","rkpieniazek@gmail.com");
        typeDataInInput("password","Haslo123!");
        typeDataInInput("password2","Haslo123!");
    }

    private void fillRegisterFormInvalidPassword() {
        typeDataInInput("username","rkpieniazek");
        typeDataInInput("email","rkpieniazek@gmail.com");
        typeDataInInput("password","aaa");
        typeDataInInput("password2","aaa");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
