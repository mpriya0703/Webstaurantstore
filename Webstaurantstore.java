package webelement_Package;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Webstaurantstore {

    public static void main(String[] args) throws InterruptedException {
        SoftAssertions softAssertions = new SoftAssertions();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\naren\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.webstaurantstore.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("searchval")).sendKeys("stainless work table");
        driver.findElement(By.xpath("//*[@id='searchForm']/div/button")).click();
        List<WebElement> listOfTableLabels = driver.findElements(By.xpath("(//*[@id='details']/a[contains(text(),'Table')])"));
        for (WebElement ele : listOfTableLabels) {
            softAssertions.assertThat(ele.getText()).as(ele.getText() + ":does not contains table value").contains("Table");
        }
        softAssertions.assertAll();
        int lastItem = listOfTableLabels.size();
        String lastItemLabel = driver.findElement(By.xpath("(//*[@id='details']/a[contains(text(),'Table')])[" + lastItem + "]")).getText();
        System.out.println("lastItemLabel:" + lastItemLabel);
        driver.findElement(By.xpath("(//*[@id='details']/a[contains(text(),'Table')])[" + lastItem + "]/following::div[@class='add-to-cart']//div/input[@name='addToCartButton']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'View Cart')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[contains(text(),'Empty Cart')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[contains(text(),'Empty Cart')]")).click();
        driver.close();


    }
}
