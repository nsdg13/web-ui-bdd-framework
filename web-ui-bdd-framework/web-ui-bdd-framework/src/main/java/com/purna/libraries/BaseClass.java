package com.purna.libraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;
import java.lang.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass implements Constants
{
    private WebDriver driver = null;
    protected static Properties configProperties = null;
    private static Logger Log = LoggerFactory.getLogger(BaseClass.class);

    protected WebDriver getWebDriver()
    {
        readConfigData();

        try {

            String browser = configProperties.getProperty("browser");
            switch (browser)
            {
                case "Chrome":                 
                        driver = new ChromeDriver(getChromeOptions());
                        break;
                case "Edge":                   
                        driver = new EdgeDriver();  
                        break;
                case "InternetExplorer":                   
                        driver = new InternetExplorerDriver();                   
                        break;

                default: throw new Exception("Invalid Value Provided For Browser");
            }
        }
        catch (Exception e)
        {
            Log.error("Loading WebDriver failed ", e);
           e.printStackTrace();
        }

        Log.info("Driver Loaded Successfully.");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(configProperties.getProperty("implicit_wait"))));
        driver.manage().deleteAllCookies();
        Log.info("Implicit Wait Set as " + configProperties.getProperty("implicit_wait") +  " Seconds");

        return driver;
    }

    private void readConfigData()
    {
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(PROPERTIES_FILE_PATH));
            configProperties = new Properties();
            configProperties.load(reader);
            reader.close();
            Log.info("Read Properties File Successfully");
        }
        catch (FileNotFoundException e)
        {
            Log.error("Properties File Could Not Find ", e);
            e.printStackTrace();
        }
        catch(Exception e)
        {
            Log.error("Reading Properties File Failed ", e);
            e.printStackTrace();
        }
    }

    private ChromeOptions getChromeOptions(){
//        System.setProperty(configProperties.getProperty("chromeDriver.property"), configProperties.getProperty("chromeDriver.path"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability("useAutomationExtension ", false);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("applicationCacheEnabled", false);
        chromeOptions.merge(capabilities);

        return chromeOptions;
    }

    protected void openLoginPage(WebDriver driver, TestContext testContext ,String testCaseID, String moduleName)
    {
        driver.get(configProperties.getProperty("url"));
        testContext.setMapTestData(getTestData(moduleName, testCaseID));
    }

    private HashMap<String, String> getTestData(String moduleName, String testCaseID) {
        HashMap<String, String> testData = new HashMap<>();
        try
        {
            Object object = new JSONParser().parse(new FileReader(configProperties.getProperty("testdata.filePath")));
            JSONObject entireJsonObject = (JSONObject) object; //converting into JSON Object
            JSONObject parentJsonObject = (JSONObject) entireJsonObject.get("testData");

//            JSONObject testData = (JSONObject) parentJsonObject.get(testCaseID);
//            testData.forEach((key,value) -> testData.put(key.toString(),value.toString())) ;

            ((JSONObject) parentJsonObject.get("commonTestData")).forEach((key,value) -> testData.put(key.toString(),value.toString()));

            ((JSONObject) parentJsonObject.get(testCaseID)).forEach((key,value) -> testData.put(key.toString(),value.toString()));

            Log.info("Test Data Read Successfully");
        } catch (IOException e) {
            Log.error("Could Not Read JSON File ", e);
            e.printStackTrace();
        } catch (ParseException e) {
            Log.error("Could Not Parse JSON File ", e);
            e.printStackTrace();
        } catch (Exception e) {
            Log.error("Mapping Test Data Failed ", e);
            e.printStackTrace();
        }

        return testData;
    }

    public void captureScreenshot(WebDriver driver, TestContext context, boolean appendTestStatus){
        try
        {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String tcStatus = "";
            if(appendTestStatus)
            {
                tcStatus = "FAILED";
                if(!context.getScenario().isFailed()){
                    tcStatus = "PASSED";
                }
            }

            String fileName = context.getTestCaseID() + "_" + context.getScenario().getName() + " " + getDate("forFileName") + "_" + tcStatus;
            File destination = new File(configProperties.get("screenshotFolder.path") + getDate("forFolderName") + "/" + fileName + ".png");
            FileUtils.copyFile(source,destination);
        }
        catch (IOException e){
            Log.error("Copy File From Source To Destination Failed ", e);
            e.printStackTrace();
        }
        catch (Exception e){
            Log.error("Screenshot Capture Failed ", e);
            e.printStackTrace();
        }
    }

    private String getDate(String dateForWhat)
    {
        try{
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat dateFormat;

            switch (dateForWhat)
            {
                case "forFileName":
                    dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
                    break;

                case "forFolderName":
                    dateFormat = new SimpleDateFormat("dd_MM_yyyy");
                    break;

                default:
                    throw new Exception("Invalid value for: " + dateForWhat);
            }

            dateFormat.setCalendar(calendar);
            return dateFormat.format(calendar.getTime());
        }
        catch (Exception e)
        {
            Log.error("Getting Date Format Failed ", e);
            e.printStackTrace();
        }
		return dateForWhat;
    }

    //TODO: impement this
    private String getJSONFileForModule(String moduleName)
    {
        String jsonFileName = null;

        try
        {
            switch (moduleName)
            {
                case "Login":
                    jsonFileName = "jsonFileName for Login Module";
                    break;

                case "Accounts":
                    jsonFileName = "jsonFileName for Accounts Module";
                    break;

                default:
                    throw new Exception("Invalid Module Name");
            }
        }
        catch (Exception e)
        {
            Log.error("?????????? JSON File For Module Name");
            Log.error("Exception is: ", e);
        }

        return jsonFileName;
    }

    protected void closeBrowser(TestContext testContext){
        if(testContext.getScenario().isFailed())
        {
            Log.info("-------------------------------------------------------------");
            Log.info("Test Case " + testContext.getTestCaseID() + " is FAILED");
            Log.info("-------------------------------------------------------------");
        }
        else
        {
            Log.info("-------------------------------------------------------------");
            Log.info("Test Case " + testContext.getTestCaseID() + " is PASSED");
            Log.info("-------------------------------------------------------------");
        }

        if(configProperties.getProperty("captureScreenshot.switch") != null)
		    captureScreenshot(driver, testContext, true);

        try{
            if(testContext.getDriver() != null){
                testContext.getDriver().quit();
            }
            Log.info("Driver Quit Successfully");
            Log.info("-------------------------------------------------------------");
            Log.info("					END OF TEST");
            Log.info("-------------------------------------------------------------");
        }
        catch (Exception e){
            Log.info("Quiting Driver Failed", e);
            e.printStackTrace();
        }
    }
}