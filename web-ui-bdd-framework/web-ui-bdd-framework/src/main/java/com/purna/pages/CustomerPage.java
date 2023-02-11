package com.purna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {
	
	public CustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="sr_no")
	public WebElement textBox_custNo;
	
	@FindBy(id="customer_name")
	public WebElement textBox_custName;
	
	@FindBy(id="contact_no")
	public WebElement textBox_contNo;
	
	@FindBy(id="billing_address")
	public WebElement textBox_billAdress;
	
	@FindBy(id="myButton")
	public WebElement checkBox_address;
	
	@FindBy(id="email")
	public WebElement textBox_email;
	
	@FindBy(id="contact_person")
	public WebElement textBox_contPerson;
	
	@FindBy(id="gst_no")
	public WebElement textBox_gstNo;
	
	@FindBy(id="pan_no")
	public WebElement textBox_panNo;
	
	@FindBy(id="btn")
	public WebElement btn_save;
	
	@FindBy(linkText="Customer Details")
	public WebElement btn_custDetails;
	
}
