package com.demoNopeCommerce;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Registration extends Utils {

    Loadprop loadprop = new Loadprop();

    //before method to open the browser
    @BeforeMethod

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\Browserdrive\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the window browser screen
        maximisescreen();
        implicitwait();
        //open the website
        openWeb(loadprop.getProperty("url"));
    }

    @AfterMethod
    public void closeBrowser()
    {
       quit();

    }

    @Test(priority = 0)
    public void userShouldBeRegisteredSuccessfully()
    {
        //click on register button
        clickButton(By.xpath("//a[@class='ico-register']"));
        //enter first name
        enterText(By.id("FirstName"),loadprop.getProperty("FirstName"));
        //enter last name
        enterText(By.id("LastName"),loadprop.getProperty("LastName"));
        //enter date of birth
        SelectIndex(By.name("DateOfBirthDay"),5);
        //enter the month
        SelectValue(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),loadprop.getProperty("DateOfBirthMonth"));
        //enter the year
        SelectVisibleText(By.name("DateOfBirthYear"),loadprop.getProperty("DateOfBirthYear"));
        //enter email
        enterText(By.name("Email"),loadprop.getProperty("FirstName"));
        enterText(By.name("Email"),randomDate());
        enterText(By.name("Email"),loadprop.getProperty("Email"));
        //enter password
        enterText(By.id("Password"),loadprop.getProperty("Password"));
        //re-enter same password to confirm
        enterText(By.id("ConfirmPassword"),loadprop.getProperty("ConfirmPassword"));
        //submit the form
        submit(By.name("register-button"));
        //expected result
        String expectedMessage = loadprop.getProperty("expectedMessageRegistraion");
        //actual message
        String actualMessage = getText(By.xpath("//div[@class='result']"));
        //assert method
        Assert.assertEquals(actualMessage, expectedMessage);

    }



    @Test(priority = 1)
    public void userShouldBeAbleToSendReferedaLinkToFriend() {
        //click on register button
        clickButton(By.xpath("//a[@class='ico-register']"));
        //enter first name
        enterText(By.id("FirstName"),loadprop.getProperty("FirstName"));
        //enter last name
        enterText(By.id("LastName"),loadprop.getProperty("LastName"));
        //enter date of birth
        //SelectValue(By.name("DateOfBirthDay"),loadprop.getProperty("DateOfBirthDay"));
        SelectIndex(By.name("DateOfBirthDay"),5);
        //enter the month
        SelectValue(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),loadprop.getProperty("DateOfBirthMonth"));
        //enter the year
        SelectVisibleText(By.name("DateOfBirthYear"),loadprop.getProperty("DateOfBirthYear"));
        //enter email
        //driver.findElement(By.name("Email")).sendKeys("krp" + randomDate() + "@bmail.com");
        enterText(By.name("Email"),loadprop.getProperty("FirstName"));
        enterText(By.name("Email"),randomDate());
        enterText(By.name("Email"),loadprop.getProperty("Email"));
        //enter password
        enterText(By.id("Password"),loadprop.getProperty("Password"));
        //re-enter same password to confirm
        enterText(By.id("ConfirmPassword"),loadprop.getProperty("ConfirmPassword"));
        //submit the form
        submit(By.name("register-button"));
        //continue to site
        clickButton(By.name("register-continue"));
        //click on category Computers
        clickButton(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]"));
        //select notebooks
        clickButton(By.xpath("//h2/a[@href=\"/notebooks\"]"));
        //click on Apple pro 13inch
        clickButton(By.xpath("//h2/a[@href=\"/apple-macbook-pro-13-inch\"]"));
        //email to friend
        clickButton(By.className("email-a-friend"));
        //enter friend's email
        enterText(By.className("friend-email"),loadprop.getProperty("friend-email"));
        //enter personal message
        enterText(By.id("PersonalMessage"),loadprop.getProperty("PersonalMessage"));
        //click on submit
        clickButton(By.name("send-email"));
        String expectedMessage = loadprop.getProperty("expectedMessagerefferedLink");
        String actualMessage = getText(By.className("result"));

        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test(priority = 2)
    public void userShouldBeNavigateToCameraAndPhotopage() {

        //go to electronic category
        clickButton(By.linkText("Electronics"));
        //click on camera &photo
        //driver.findElement(By.linkText("Camera & photo")).click();
        clickButton(By.linkText("Camera & photo"));
        //compare the result
        String expectedMessage = loadprop.getProperty("expectedMessageCmaeraPhoto");
        //String actualMessage = driver.findElement(By.tagName("h1")).getText();
        String actualMessage = getText(By.tagName("h1"));

        Assert.assertEquals(expectedMessage, actualMessage);


    }
    @Test(priority =3)
    public  void userShouldbeabletoSelectJewelryByPrice() {
        //go to Jewelry category
        clickButton(By.linkText("Jewelry"));
        //select price$700-&3000
        clickButton(By.xpath("//a[@href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]"));
        //find min range
        String minrange  = getText(By.xpath("//span[@class=\"PriceRange\"][1]"));
        //replace $ using string  replace() method
        String minrange1 = String.valueOf(minrange.replace("$", ""));
        //convert double to int
        double minrange2 = Double.valueOf(minrange1);
        //find max range
        String maxrange = getText(By.xpath("//span[@class=\"PriceRange\"][2]"));
        String maxrange1 = String.valueOf(maxrange.replace("$", ""));
        String maxrange2 = String.valueOf(maxrange1.replace(",", ""));
        double maxrange3 = Double.valueOf(maxrange2);
        //Expected result value
        String myvalue = getText(By.xpath("//span[@class=\"price actual-price\"]"));
        String myvalue1 = String.valueOf(myvalue.replace("$", ""));
        String myvalue2 = String.valueOf(myvalue1.replace(",", ""));
        double myvalue3 = Double.valueOf(myvalue2);

        Assert.assertTrue(myvalue3 >= minrange2 && myvalue3 <= maxrange3);

    }

         @Test(priority = 4)
         public void userShouldBeAbleToAddItemInBasket(){
            //Click on book category
             clickButton(By.linkText("Books"));
            //click on book Fahrenheit 451 by Ray Bradbury
             clickButton(By.linkText("Fahrenheit 451 by Ray Bradbury"));
            //add the item in to cart
             clickButton(By.id("add-to-cart-button-37"));
            //click on close
             clickButton(By.className("close"));
            //driver.navigate().back();
             navigateBack();
            //select the book Pride and Prejudice
             clickButton(By.linkText("Pride and Prejudice"));
            //add to cart the item
             clickButton(By.id("add-to-cart-button-39"));
             clickButton(By.className("close"));
            //go to the shopping cart
             clickButton(By.linkText("Shopping cart"));

             //to catch all web elements into list
            List<WebElement> skuList= getskuList(By.className("sku-number"));

            //myList contains all the web elements
            //if you want to get all elements text into array list
            List<String> sku_elements=new ArrayList<>();

            for(int i=0; i<skuList.size(); i++){

                //loading text of each element in to array all_elements_text

                sku_elements.add(skuList.get(i).getText());

             //to print directly
             System.out.println(skuList.get(i).getText());
            }

            String expected1 [] = {"FR_451_RB", "PRIDE_PRJ"};
            System.out.println("Expected SKUs:" + Arrays.toString(expected1));
            String actual1[] = sku_elements.toArray(new String[sku_elements.size()]);
            System.out.println("Actual SKUs:" + Arrays.toString(actual1));
            Assert.assertEquals(expected1,actual1);
    }
}





