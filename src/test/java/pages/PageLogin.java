package pages;

import helpers.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageLogin {
	
	private WebDriver driver;
	Helper helper = new Helper();
	
	//!WEB ELEMENTS
	private WebElement buttonSingUp;
	private WebElement singUpButton;
	private WebElement buttonLogIn;
	private WebElement loginButton;
	private WebElement buttonLogOut;
	private WebElement WEcategoryLaptop;
	private WebElement webElementProduct;
	private WebElement webElementAddToCart;
	private WebElement webElementCart;
	
	//! OBJECT LOCATORS
	//*** SING UP
	private By Buttonsingin = By.id("signin2");				//Button SING UP
	private By boxSingUpUser = By.id("sign-username");		//text box user name
	private By boxSingUpPassword = By.id("sign-password");	//text box password
	private By ButtonConfirmSingUp = By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]");//Button Confirm Sing Up
	
	//*** LOGIN
	private By buttonLogin = By.id("login2");				//Button LOGIN
	private By boxLoginUser = By.id("loginusername");		//text box user name
	private By boxLoginPassword = By.id("loginpassword");	//text box password
	private By buttonConfirmLogin = By.xpath("//div[@id='logInModal']/div/div/div[@class='modal-footer']/button[@class='btn btn-primary']");
	
	//*** LOG OUT
	private By ButtonLogOut = By.xpath("//a[@id='logout2']");
	//*** Page Objects
	private By categoryLaptopButton = By.xpath("/html/body/div[5]/div/div[1]/div/a[3]");

	private  int selectedItem = 1;// 1 = product1, 2 = product2, 3 = product3, 4 = product4, 5 = product5, 6 = product6
	private By productSelected = By.xpath("/html/body/div[5]/div/div[2]/div/div["+selectedItem+"]/div/div/h4/a");
	private By addToCartButton = By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a");

	private By goToCartButton = By.xpath("/html/body/nav/div/div/ul/li[4]/a");

	//? METHODS
	public PageLogin(WebDriver driver) {
		this.driver = driver;				
	}
	//*** SIMPLE FUNCTIONS
	public boolean register(String user, String pass) {
		try {
			buttonSingUp = driver.findElement(Buttonsingin);
			buttonSingUp.click();
			helper.implicitSleeper(driver);

			driver.findElement(boxSingUpUser).sendKeys(user);  //loading text box user
			driver.findElement(boxSingUpPassword).sendKeys(pass);  //loading text box password

			//click on button confirm sing up
			singUpButton = driver.findElement(ButtonConfirmSingUp);
			singUpButton.click();

			//check if user is registered
			helper.sleeper(1);
			helper.singUpCheck(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
			return true;
		} catch (Exception e) {
			helper.takingScreenShot(driver,"notRegister");//? taking a ScreenShot
			Assert.fail("No se pudo registrar");
			return false;
		}
	}
	public boolean login(String user, String pass) {
		try {
			buttonLogIn = driver.findElement(buttonLogin);
			buttonLogIn.click();
			helper.implicitSleeper(driver);

			//LOGIN USER AND PASS
			driver.findElement(boxLoginUser).sendKeys(user);
			driver.findElement(boxLoginPassword).sendKeys(pass);

			//click on button confirm login
			loginButton = driver.findElement(buttonConfirmLogin);
			loginButton.click();
			helper.sleeper(1);
			return true;
		} catch (Exception e) {
			helper.takingScreenShot(driver,"notLogin");//? taking a ScreenShot
			Assert.fail("No se pudo loguear");
			return false;
		}
	}
	public void logOut() {
		try {
			helper.sleeper(2);
			buttonLogOut = driver.findElement(ButtonLogOut);
			buttonLogOut.click();
		} catch (Exception e) {
			helper.takingScreenShot(driver,"notLogOut");//? taking a ScreenShot
			Assert.fail("No se pudo desloguear");
		}
	}
	public void addToCartProcedure() {
		try {
			//*** click on laptop category
			helper.sleeper(1);
			WEcategoryLaptop = driver.findElement(categoryLaptopButton);
			WEcategoryLaptop.click();

			//*** click on product
			helper.sleeper(1);
			webElementProduct = driver.findElement(productSelected);
			webElementProduct.click();

			//*** add to cart
			helper.sleeper(1);
			webElementAddToCart = driver.findElement(addToCartButton);
			webElementAddToCart.click();

			//*** check if product is added to cart with alert message
			helper.sleeper(1);
			helper.addToCartAlert(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();

			//*** go to cart
			helper.sleeper(1);
			webElementCart = driver.findElement(goToCartButton);
			webElementCart.click();

			//*** check if product is added to cart with button "Go to cart"




		} catch (Exception e) {
			helper.takingScreenShot(driver,"notSelectProduct");//? taking a ScreenShot
			Assert.fail("No se pudo seleccionar el producto");
		}
	}
}
