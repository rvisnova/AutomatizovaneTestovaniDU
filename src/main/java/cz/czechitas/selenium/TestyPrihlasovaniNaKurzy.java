package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    private void vyplnHeslo(String heslo) {
        WebElement password = prohlizec.findElement(By.xpath("//input [@id = 'password']"));
        password.sendKeys(heslo);
    }
    private void vyplnPrihlasovaciEmail(String emailovaAdresa) {
        WebElement password = prohlizec.findElement(By.xpath("//input [@id = 'email']"));
        password.sendKeys(emailovaAdresa);
    }
    private void vyplnPole(String identifikacePole, String textKVyplneni) {
        WebElement vyplnovaciPoleTermin = prohlizec.findElement
                (By.xpath(identifikacePole));
        vyplnovaciPoleTermin.sendKeys(textKVyplneni);
    }
    @AfterEach
    public void tearDown() {
        prohlizec.quit();
    }
}
