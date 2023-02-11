package com.purna.libraries;

import org.openqa.selenium.WebDriver;

import com.purna.pages.CustDashboardPage;
import com.purna.pages.CustomerPage;
import com.purna.pages.DashboardPage;
import com.purna.pages.LoginPage;
import com.purna.pages.SupplierDashboardPage;
import com.purna.pages.SupplierPage;

public class PageObjectManager extends BaseClass
{
    private WebDriver driver;
    private LoginPage onLoginPage;
    private DashboardPage onDashboardPage;
    private CustomerPage onCustomerPage;
    private CustDashboardPage onCustDashboardPage;
    private SupplierDashboardPage onSupplierDashboardPage;
    private SupplierPage onSupplierPage;

    public PageObjectManager(WebDriver driver)
    {
        this.driver = driver;
    }
    
	public LoginPage getLoginPage() 
	{	
			return (onLoginPage == null) ? onLoginPage = new LoginPage(driver) : onLoginPage;	
	}
	
	public DashboardPage getDashboardPage() 
	{	
			return (onDashboardPage == null) ? onDashboardPage = new DashboardPage(driver) : onDashboardPage;	
	}
	
	public CustomerPage getCustomerPage() 
	{	
			return (onCustomerPage == null) ? onCustomerPage = new CustomerPage(driver) : onCustomerPage;	
	}
	
	public CustDashboardPage getCustDashboardPage() 
	{	
			return (onCustDashboardPage == null) ? onCustDashboardPage = new CustDashboardPage(driver) : onCustDashboardPage;	
	}
	
	public SupplierDashboardPage getSupplierDashboardPage() 
	{	
			return (onSupplierDashboardPage == null) ? onSupplierDashboardPage = new SupplierDashboardPage(driver) : onSupplierDashboardPage;	
	}
	
	public SupplierPage getSupplierPage() 
	{	
			return (onSupplierPage == null) ? onSupplierPage = new SupplierPage(driver) : onSupplierPage;	
	}
}
