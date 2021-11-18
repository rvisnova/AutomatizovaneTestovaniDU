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
    @Test
    public void registrovanyRodicMusiBytSchopenSePrihlasit() {
        otevriPrihlasovaciStranku();
        klikniNaTlacitko("//a [contains(@href, 'prihlaseni')]");

        vyplnPrihlasovaciUdaje();
        klikniNaTlacitko("//button [contains(@class, 'btn-primary')]");

        WebElement potvrzeniPrihlaseni = prohlizec.findElement(By.xpath("//h1[contains (text(), Prihlášky)]"));
        Assertions.assertNotNull(potvrzeniPrihlaseni);
        System.out.println("Prihlaseni bylo uspesne");

    }
    @Test
    public void rodicMusiBytSchopenVybratKurzPrihlasitSeDoAplikaceAPrihlasitDiteDoKurzu(){

        otevriPrihlasovaciStranku();
        klikniNaTlacitko("//a [contains(@href, 'trimesicni-kurzy-webu')]");
        klikniNaTlacitko("//a [contains (@class, 'btn') and contains (text(),'Vytvořit přihlášku')]");

        vyplnPrihlasovaciUdaje();
        klikniNaTlacitko("//button [contains(@class, 'btn-primary')]");

        klikniNaTlacitko("//div [contains(text(), 'termín')]");
        vyplnPole("//div/input [@class='form-control']", "0" + "\n");

        vyplnPole("//input [@id='forename']", "Ivanka");
        vyplnPole("//input [@id='surname']", "Maderova");
        vyplnPole("//input [@id='birthday']", "1.1.2010");

        klikniNaTlacitko("//label [@for = 'payment_transfer']");
        klikniNaTlacitko("//label [@for = 'terms_conditions']");
        klikniNaTlacitko("//input [@value= 'Vytvořit přihlášku']");

        WebElement seznamPrihlasek = prohlizec.findElement
                (By.xpath("//h1 [contains(text(), 'Ivanka Maderova')]"));
        Assertions.assertNotNull(seznamPrihlasek);

    }

    @Test
    public void rodicMusiBytSchopenSePrihlasitVybratKurzAPrihlasitDite(){

        otevriPrihlasovaciStranku();
        klikniNaTlacitko("//a [contains(@href, 'prihlaseni')]");

        vyplnPrihlasovaciUdaje();
        klikniNaTlacitko("//button [contains(@class, 'btn-primary')]");

        klikniNaTlacitko("//a [contains(@class, 'btn') and contains(@href, 'pridat')]");

        klikniNaTlacitko("//a [contains(@href, 'trimesicni-kurzy-webu')]");
        klikniNaTlacitko("//a [contains (@class, 'btn') and contains (text(),'Vytvořit přihlášku')]");

        klikniNaTlacitko("//div [contains(text(), 'termín')]");
        vyplnPole("//div/input [@class='form-control']", "0" + "\n");

        vyplnPole("//input [@id='forename']", "Ivanka");
        vyplnPole("//input [@id='surname']", "Maderova");
        vyplnPole("//input [@id='birthday']", "1.1.2010");

        klikniNaTlacitko("//label [@for = 'payment_transfer']");
        klikniNaTlacitko("//label [@for = 'terms_conditions']");
        klikniNaTlacitko("//input [@value= 'Vytvořit přihlášku']");

        WebElement seznamPrihlasek = prohlizec.findElement
                (By.xpath("//h1 [contains(text(), 'Ivanka Maderova')]"));
        Assertions.assertNotNull(seznamPrihlasek);
    }
    private void vyplnPrihlasovaciUdaje() {
        vyplnPrihlasovaciEmail(EMAILOVA_ADRESA);
        vyplnHeslo(HESLO);
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
