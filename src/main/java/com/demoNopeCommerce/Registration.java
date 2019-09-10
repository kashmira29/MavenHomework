package com.demoNopeCommerce;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Registration {
    protected static WebDriver driver;

    public static String randomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());

    }

    //before method to open the browser
    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\Browserdrive\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the window browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //open the website
        driver.get("https://demo.nopcommerce.com/");

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test(priority = 0)
    public void userShouldBeRegisteredSuccessfully() {

        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter first name
        driver.findElement(By.id("FirstName")).sendKeys("venus");
        //enter last name
        driver.findElement(By.id("LastName")).sendKeys("patel");
        //enter email
        driver.findElement(By.name("Email")).sendKeys("krp" + randomDate() + "@bmail.com");
        //enter password
        driver.findElement(By.id("Password")).sendKeys("test123");
        //re-enter same password to confirm
        driver.findElement(By.id("ConfirmPassword")).sendKeys("test123");
        //click on register
        driver.findElement(By.name("register-button")).click(); //assert
        String expectedMessage = "Your registration completed";
        String actualMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);

    }

    @Test(priority = 1)
    public void userShouldBeAbleToSendReferedaLinkToFriend() {
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter first name
        driver.findElement(By.id("FirstName")).sendKeys("venus");
        //enter last name
        driver.findElement(By.id("LastName")).sendKeys("patel");
        //enter email
        driver.findElement(By.name("Email")).sendKeys("krp" + randomDate() + "@bmail.com");
        //enter password
        driver.findElement(By.id("Password")).sendKeys("test123");
        //re-enter same password to confirm
        driver.findElement(By.id("ConfirmPassword")).sendKeys("test123");
        //click on register
        driver.findElement(By.name("register-button")).click();
        //continue to site
        driver.findElement(By.name("register-continue")).click();
        //click on category Computers
        driver.findElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")).click();
        //select notebooks
        //driver.findElement(By.xpath("//h2/a[@href=\"/notebooks\"]")).click();
        driver.findElement(By.linkText("Notebooks")).click();
        //click on Apple pro 13inch
        driver.findElement(By.xpath("//h2/a[@href=\"/apple-macbook-pro-13-inch\"]")).click();
        //email to friend
        driver.findElement(By.className("email-a-friend")).click();
        //enter friend's email
        driver.findElement(By.className("friend-email")).sendKeys("abcxyz@gmail.com");
        //enter personal message
        driver.findElement(By.id("PersonalMessage")).sendKeys("This is test");
        //click on submit
        driver.findElement(By.name("send-email")).click();
        String expectedMessage = "Your message has been sent.";
        String actualMessage = driver.findElement(By.className("result")).getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(priority = 2)
    public void userShouldBeNavigateToCameraAndPhotopage() {
        //go to electronic category
        driver.findElement(By.linkText("Electronics")).click();
        //click on camera &photo
        driver.findElement(By.linkText("Camera & photo")).click();
        //compare the result
        String expectedMessage = "Camera & photo";
        String actualMessage = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @Test(priority =3)
    public  void userShouldbeabletoSelectJewelryByPrice() {
        //go to Jewelry category
        driver.findElement(By.linkText("Jewelry")).click();
        //select price$700-&3000
        driver.findElement(By.xpath("//a[@href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]")).click();
        //find min range
        String minrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"][1]")).getText();
        //replace $ using string  replace() method
        String minrange1 = String.valueOf(minrange.replace("$", ""));
        //convert double to int
        double minrange2 = Double.valueOf(minrange1);
        //find max range
        String maxrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"][2]")).getText();
        String maxrange1 = String.valueOf(maxrange.replace("$", ""));
        String maxrange2 = String.valueOf(maxrange1.replace(",", ""));
        double maxrange3 = Double.valueOf(maxrange2);
        //Expected result value
        String myvalue = driver.findElement(By.xpath("//span[@class=\"price actual-price\"]")).getText();
        String myvalue1 = String.valueOf(myvalue.replace("$", ""));
        String myvalue2 = String.valueOf(myvalue1.replace(",", ""));
        double myvalue3 = Double.valueOf(myvalue2);

        Assert.assertTrue(myvalue3 >= minrange2 && myvalue3 <= maxrange3);

    }

         @Test(priority = 4)
         public void userShouldBeAbleToAddItemInBasket(){
            //Click on book category
            driver.findElement(By.linkText("Books")).click();
            //click on book Fahrenheit 451 by Ray Bradbury
            driver.findElement(By.linkText("Fahrenheit 451 by Ray Bradbury")).click();
            //add the item in to cart
            driver.findElement(By.id("add-to-cart-button-37")).click();
            //click on close
            driver.findElement(By.className("close")).click();
            driver.navigate().back();
            //select the book Pride and Prejudice
            driver.findElement(By.linkText("Pride and Prejudice")).click();
            //add to cart the item
            driver.findElement(By.id("add-to-cart-button-39")).click();
            driver.findElement(By.className("close")).click();
            //go to the shopping cart
            driver.findElement(By.linkText("Shopping cart")).click();

            String expected = "FR_451_RB";
            String actual = driver.findElement(By.xpath("//span[text()=\"FR_451_RB\"]")).getText();
            Assert.assertEquals(expected,actual);

            String expectedMessage = "PRIDE_PRJ";
            String actualMessage = driver.findElement(By.xpath("//span[text()=\"PRIDE_PRJ\"]")).getText();
            Assert.assertEquals(expectedMessage,actualMessage);
    }

}


