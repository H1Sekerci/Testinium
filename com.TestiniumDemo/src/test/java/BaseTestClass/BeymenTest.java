package BaseTestClass;

import ExcelFile.ReadExcel;
import Pages.AnaSayfa;
import Pages.Sepetim;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BeymenTest {
    final static Logger logger = Logger.getLogger(BeymenTest.class);
    static WebDriver driver;
    static WebDriverWait wait;
    ReadExcel objExcel = new ReadExcel();
    AnaSayfa objAnasayfa = new AnaSayfa(driver,wait);
    Sepetim objSepetim = new Sepetim(driver,wait);
    @BeforeClass
    public static void setUp(){
        logger.info("Test Başlatıldı");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.beymen.com");
    }

    @AfterClass
    public static void  CloseBrowser(){

        driver.close();
        logger.info("Test Tamamlandı");
    }

    @Test
    public void BeymenTest() throws Exception {

        logger.info("Yönlendirilen Sayfa : " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
        objAnasayfa.BeymenArama(objExcel.readData(0)); // şort için 0
        objAnasayfa.BeymenAramaTemizle();
        objAnasayfa.BeymenAramaGirisi(objExcel.readData(1)); // gömlek için 1
        objAnasayfa.UrunSecVeSepeteEkle();
        objSepetim.sepetIslemleri();
        objSepetim.sepetSil();
    }
}
