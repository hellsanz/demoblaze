package tests;

import com.aventstack.extentreports.utils.FileUtil;
import cucumber.api.java.en.Given;
import helpers.Helper;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.PageLogin;
import java.io.File;


public class Tests {
    private WebDriver driver;
    private boolean register = false;
    private String userName = "";
    private String password = "";
    private boolean login = false;
    private String visit = "https://www.demoblaze.com/";

    //! STARTING TEST *******************************************************************************
    @Before
    public void configure() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();        
        driver.get(visit);
    }

    @Test
    public void starting() throws Exception {
      Helper helper = new Helper();
      PageLogin pages = new PageLogin(driver);

      helper.takingScreenShot(driver,"Starting");//? taking a ScreenShot

      this.userName = helper.user_Alphanumeric();
      this.password = helper.pass_Alphanumeric();

      if (pages.register(this.userName, this.password)) {
	      if (pages.login(this.userName, this.password)) {
              pages.logOut();
              helper.sleeper(1);
              pages.addToCartProcedure();
          }
      }
      //*** agregar un producto al carrito
      //verificar que el producto se agrega al carrito
      //verificar que el precio del producto se agrega al carrito
      //verificar que el precio total del carrito se actualiza correctamente
      //agregar los asserts para los casos de prueba
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if (!result.isSuccess()) {
            Helper helper = new Helper();
            helper.takingScreenShot(driver,"fail_"+result.getMethod().getMethodName());
        }
        driver.close();
    }
    //! ENDING TEST *******************************************************************************
  }