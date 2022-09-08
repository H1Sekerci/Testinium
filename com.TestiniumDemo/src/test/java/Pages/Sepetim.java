package Pages;

import TextFile.WriteTxt;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Sepetim extends BasePage {

    final static Logger logger = Logger.getLogger(Sepetim.class);

    WriteTxt objWriteText = new WriteTxt();

    public Sepetim(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(how = How.ID, using = "quantitySelect0-key-0")
    private WebElement drpAdet;
    @FindBy(how = How.XPATH, using = "//select[@id='quantitySelect0-key-0']//option[@value='2']")
    private WebElement drpSecim2Adet;
    @FindBy(how = How.XPATH, using = "//li[@class='m-orderSummary__item -grandTotal']//span[@class='m-orderSummary__value']")
    private WebElement lblUrunSepetFiyat;
    @FindBy(how = How.ID, using = "removeCartItemBtn0-key-0")
    private WebElement btnSepetSil;
    @FindBy(how = How.XPATH, using = "//strong[@class='m-empty__messageTitle' and text()='Sepetinizde Ürün Bulunmamaktadır']")
    private WebElement lblSepetBos;
    @FindBy(how = How.ID, using = "notifyMessage")
    private WebElement lblGuncellendi;


    AnaSayfa objAnasayfa = new AnaSayfa(driver,wait);

    public void sepetIslemleri() throws IOException {
        String urunListeFiyat = objWriteText.readFiyatText();
        String urunSepetFiyat = lblUrunSepetFiyat.getText().split(" ")[0].toString();
        Assert.assertEquals(urunListeFiyat,urunSepetFiyat);
        logger.info("Ürün liste fiyatı ile Sepet fiyatı aynıdır.");
        drpAdet.click();
        drpSecim2Adet.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        lblGuncellendi.isDisplayed();
    }

    public void  sepetSil(){
        btnSepetSil.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        lblSepetBos.isDisplayed();
    }
}
