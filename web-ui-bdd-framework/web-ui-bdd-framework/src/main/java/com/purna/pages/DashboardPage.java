package com.purna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPage {
	
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//i[@class='menu-icon fa fa-desktop']")
	public  WebElement link_mainMenu;
	
	@FindBy(xpath="//a[@href='purchase_item_dashboard.php']")
	public WebElement link_inventory;
	
	@FindBy(xpath="//h4[text()='Sale Invoice Details']")
	public WebElement text_salesInvoiceDetails;
	
	@FindBy(name="btn_new_party")
	public WebElement btn_newSaleInvoice;
	
	@FindBy(xpath="//i[@class='menu-icon fa fa-desktop']")
	public
	WebElement link_main;
	
	@FindBy(xpath="//a[@href='supplier_dashboard.php']")
	public
	WebElement link_supplier;
	
	@FindBy(xpath = "//a[contains(@href,'employee_dashboard')]")
	public WebElement link_employee;
	
	@FindBy(xpath="//a[@href='view_supplier_balance.php']")
	public WebElement link_paymentSummary;
	
	@FindBy(xpath = "//span[text()=' Sales ']")
	public WebElement link_sales;
	
	@FindBy(xpath = "//a[@href='sale_dashboard.php']")
	public WebElement link_salesInvoice;
	
	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement link_edit;
	
	@FindBy(xpath="//a[contains(@href,'customer_dashboard')]")
	public WebElement link_customer;
	
	@FindBy(linkText="Purchase")
	public WebElement link_purchase;
	
	@FindBy(xpath="//a[contains(@href,'debit_note_dashboard.php')]")
	public WebElement link_debitNote;
}
