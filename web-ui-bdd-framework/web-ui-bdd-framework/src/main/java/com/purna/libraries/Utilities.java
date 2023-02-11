package com.purna.libraries;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class Utilities extends BaseClass
{
	
	private static Logger Log = LoggerFactory.getLogger(BaseClass.class);
	
    protected void syncElement(WebDriver driver, WebElement element, String conditionForWait)
    {
        try{
            switch (conditionForWait)
            {
                case "ToVisible":
                    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
                    break;

                case "ToClickable":
                    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
                    break;

                case "ToInvisible":
//                    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(element));
//                    break;

                default:
                    throw new Exception("Invalid Condition " + conditionForWait);
            }
        }catch (Exception e)
        {
            Log.error("Could Not Sync WebElement ", e);
            e.printStackTrace();
        }
    }

    protected void syncMultipleElement(WebDriver driver, List<WebElement> elementsList, String conditionForWait)
    {
        try{
            switch (conditionForWait)
            {
                case "ToVisible":
                    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfAllElements(elementsList));
                    break;

                case "ToInvisible":
//                    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(elementsList));
//                    break;

                default:
                    throw new Exception("Invalid Condition " + conditionForWait);
            }
        }catch (Exception e)
        {
            Log.error("Could Not Sync WebElement ", e);
            e.printStackTrace();
        }
    }

    protected void clickElement(WebDriver driver, WebElement element)
    {
        syncElement(driver, element, "ToVisible");

        try{
            if(!element.isDisplayed()){
                scrollToWebElement(driver, element);
            }
                element.click();
//                ((JavascriptExecutor)driver).executeScript("argument[0].click();", element);
        }catch (Exception e){
            Log.error("Could Not Click WebElement ", e);
            e.printStackTrace();
        }
    }

    protected void scrollToWebElement(WebDriver driver, WebElement element) {
        try{
            ((JavascriptExecutor)driver).executeScript("argument[0].scrollIntoView(true);", element);
        }catch (Exception e){
            Log.error("Could Not Scroll WebElement ", e);
            e.printStackTrace();
        }
    }

    protected void clickElementByJSE(WebDriver driver, WebElement element) {
        syncElement(driver, element, "ToVisible");
        try{
            ((JavascriptExecutor)driver).executeScript("argument[0].click();", element);
        }catch (Exception e){
            Log.error("Clicking WebElement By JavaScriptExecutor Failed ", e);
            e.printStackTrace();
        }
    }

    protected void moveToElement(WebDriver driver, WebElement element)
    {
        try {
            new Actions(driver).moveToElement(element);
        }catch (Exception e) {
            Log.error("Move MouseOver Action WebElement Failed ", e);
            e.printStackTrace();
        }
    }

    protected void sendKeysThruAction(WebDriver driver, WebElement element, String stringToInput)
    {
        try {
            new Actions(driver).sendKeys(element,stringToInput);
        }catch (Exception e) {
            Log.error("SendKeys Thru Action Failed ", e);
            e.printStackTrace();
        }
    }

    protected void selectOptionFromList(WebDriver driver, List<WebElement> webElementList, String optionValue, String actionType)
    {
        try {
            for (WebElement webElement: webElementList){
                if (webElement.getText().trim().equalsIgnoreCase(optionValue.trim())){
                    if(actionType.trim().equalsIgnoreCase("click"))
                        clickElement(driver, webElement);
                    else
                        moveToElement(driver, webElement);
                }
            }
        }catch (Exception e) {
            Log.error("Clicking WebElement From List Failed ", e);
            e.printStackTrace();
        }
    }

    protected void sleepInMilliSeconds(int milliSeconds)
    {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            Log.error("Explicit Sleep Failed ", e);
            e.printStackTrace();
        }
    }

    protected String getRandomString(int stringLength, String dataType)
    {
        StringBuilder builder = new StringBuilder();
        String stringType = null;

        switch (dataType)
        {
            case "Numbers":
                stringType = "0123456789";
                break;

            case "Alpha":
                stringType = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
                break;

            case "AlphaNumbers":
                stringType = "A0a1B2b3C4c5D6d7E8e9F0f1G2g3H4h5I6i7J8j9K0k1L2l3M4m5N6n7O8o9P0p1Q2q3R4r5S6s7T8t9U0u1V2v3W4w5X6x7Y8y9Z0z1";
                break;

            default:
                Log.error("Invalid Datatype For Random String ");
        }
        try{
            while (stringLength != 0)
            {
                int characterIndex = (int) (Math.random() * stringType.length());
                builder.append(stringType.charAt(characterIndex));
            }
        }catch (Exception e){
            Log.error("Random String Generation Failed ", e);
            e.printStackTrace();
        }

        return builder.toString();
    }
    
    
}
