package Pages;

import ExcelFile.ReadExcel;
import TextFile.WriteTxt;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class AnaSayfa extends BasePage{

    final static Logger logger = Logger.getLogger(AnaSayfa.class);
    public AnaSayfa(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    WriteTxt objWriteTxt = new WriteTxt();


    @FindBy(how = How.XPATH, using = "//input[@class='default-input o-header__search--input']")
    private WebElement txtArama;
    @FindBy(how = How.XPATH, using = "(//div[@class='m-productImageList'])[1]")
    private WebElement rastgeleUrun; // sayfada listenen ürünlerden 1. si seçilmektedir. Hangi ürünlerin listelendiği önemli değil
    @FindBy(how = How.XPATH, using = "//span[@class='o-productDetail__description']")
    private WebElement lblUrunBilgisi;
    @FindBy(how = How.XPATH, using = "//ins[@id='priceNew']")
    private WebElement lblUrunFiyat;
    @FindBy(how = How.ID, using = "addBasket")
    private WebElement btnSepeteEkle;
    @FindBy(how = How.XPATH, using = "(//span[contains(@class,'m-variation__item') and not(contains(@class,'-disabled'))])[1]")
    private WebElement btnAktifBeden;
    @FindBy(how = How.XPATH, using = "//a[@title='Sepetim']")
    private WebElement btnSepetim;

    public static String urunListeFiyat ="";

    public void BeymenArama(String aranacakKelime){
        txtArama.sendKeys(aranacakKelime);
    }

    public void BeymenAramaGirisi(String aranacakKelime){
        txtArama.sendKeys(aranacakKelime);
        new Actions(driver)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public void BeymenAramaTemizle(){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait1.until(ExpectedConditions.elementToBeClickable(txtArama));
        txtArama.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));// txtArama.clear() nedense çalışmadığı için
    }

    public void UrunSecVeSepeteEkle() throws IOException {
        logger.info("Listelenen ürünlerden ilk ürün seçilmektedir. Ürün değişse bile seçilen sıra aynı kalmaktadır.");
        rastgeleUrun.click();
        urunListeFiyat = lblUrunFiyat.getText();
        objWriteTxt.writeText(lblUrunBilgisi.getText(),lblUrunFiyat.getText());
        btnAktifBeden.click();
        logger.info("Seçilen Beden " + btnAktifBeden.getText());
        btnSepeteEkle.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        btnSepetim.click();
    }

}
