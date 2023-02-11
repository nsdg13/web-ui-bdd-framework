package com.purna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierPage {
	
	public SupplierPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="sr_no")
	public WebElement txtBox_vendorCode;
	
	@FindBy(id="supplier_name")
	public WebElement txtBox_supplierName;
	
	@FindBy(id="contact_no")
	public WebElement txtBox_contactNoSupplier;
	
	@FindBy(id="address")
	public WebElement txtBox_address;
	
	@FindBy(id="email_id")
	public WebElement txtBox_emailId;
	
	@FindBy(id="contact_person")
	public WebElement txtBox_contactPerson;
	
	@FindBy(id="cont_no")
	public WebElement txtBox_contactNoContPers;
	
	@FindBy(id="gst_no")
	public WebElement txtBox_gstNo;
	
	@FindBy(id="pan_no")
	public WebElement txtBox_panNo;
	
	@FindBy(name="submit")
	public WebElement btn_save;
	
	@FindBy(xpath="//a[text()='Supplier Details']")
	public WebElement btn_supplierDetails;
}
