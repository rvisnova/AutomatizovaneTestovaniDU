package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {
    public static final String EMAILOVA_ADRESA = "antonin.madera@email.com";
    public static final String HESLO = "Heslotonda1";
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
    private void klikniNaTlacitko(String identifikaceTlacitka) {
        WebElement tlacitko = prohlizec.findElement(By.xpath(identifikaceTlacitka));
        tlacitko.click();
    }
    private void otevriPrihlasovaciStranku() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
    }
    @AfterEach
    public void tearDown() {
        prohlizec.quit();
    }
}
