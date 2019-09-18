package com.demoNopeCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Utils<idValue> extends BasePage {

    //method to findElement(By, by) and click() to Click on any element of the webpage
    public void clickButton(By by)
    {
        driver.findElement(by).click();
    }

    //findElement(By, by) with sendKeys()
    public void enterText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    //getText() is a method that gets you the inner text of the web element
    public String getText(By by)
    {
        return driver.findElement(by).getText();
    }

    //if the element to be clicked is a submit button
    public void submit(By by)
    {
        driver.findElement(by).submit();
    }

    // get() to open a URL in the current browser
    public void openWeb(String url)
    {
        driver.get(url);
    }

    //method to maximise the screen
    public void maximisescreen()

    {
        driver.manage().window().fullscreen();
    }

    //method to navigate back
    public void navigateBack()
    {
        driver.navigate().back();
    }

    //method to navigate forward
    public void navigateForward()
    {
        driver.navigate().forward();
    }

    //method closes all the windows that were opened by the WebDriver instance
    public void quit() {
        driver.quit();
    }

    //select the dropdown using "select by index"
    public static void SelectIndex(By by, int IndexValue) {
        Select selObj = new Select(driver.findElement(by));
        selObj.selectByIndex(IndexValue);
    }

    //select the dropdown using "select by value"
    public static void SelectValue(By by, String Value) {
        Select selObj = new Select(driver.findElement(by));
        selObj.selectByValue(Value);
    }

    //select the dropdown using "select by visible text"
    public static void SelectVisibleText(By by, String VisibleText) {
        Select selObj = new Select(driver.findElement(by));
        selObj.selectByVisibleText(VisibleText);
    }

    //implicit wait
    public void implicitwait() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //RandomDate method to make email address dynamic
    public static String randomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }

    //getAttribute()method
    public void getAttribute(By by, String attName) {
        WebElement searchAttribute = driver.findElement(by);
        System.out.println("Value of Attribute(" + attName + ") is :- " + searchAttribute.getAttribute(attName));
    }

    //Used to get the value of any CSS property of the web element
    public void cssProperty(By by, String propertyName) {
        WebElement cssProperty = driver.findElement(by);
        System.out.println("Value of cssProperty(" + propertyName + ") is :- " + cssProperty.getCssValue(propertyName));

    }
    //navigate to url
    public void navigate(String url)
    {
        driver.navigate().to(url);
    }
    //to get all elements in List
    public List<WebElement> getskuList(By by)
    {
        return driver.findElements(by);
    }

    //method to close current browser
    public void closeCurrentWeb(){
        driver.close();
    }

    //method to delete all cookies
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }

    //wait for clickable
    public static void waitForclickable(By by,long time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    //wait for element visible
    public static void waitForelementVisible(By by,long time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    //checking web element displayed or not
    public boolean isElementDisplayed(By by)
    {
        return driver.findElement(by).isDisplayed();
    }

    //checking webelement is enable
    public boolean ElementEnable(By by)
    {
        return driver.findElement(by).isEnabled();
    }

}



















