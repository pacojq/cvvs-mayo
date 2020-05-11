package com.uniovi.tests;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.util.TestUtils;
import org.aspectj.lang.annotation.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractTest {

    protected static WebDriver driver;
    protected static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckdriver);
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    //Antes de cada prueba se navega al URL home de la aplicación
    @Before
    public void setUp() throws Exception {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @After(value = "")
    public void tearDown() throws Exception {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
	@BeforeClass
	static public void begin() {
        driver = getDriver(TestUtils.PathFirefox65, TestUtils.Geckdriver024);
	}

    //Al finalizar la última prueba
    @AfterClass
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }


    protected void NavigateToHome() {
        PO_HomeView.clickOption(driver, "home", "class", "btn btn-primary");
    }

    protected void NavigateToLogin() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }

    protected void NavigateToSignUp() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }

    protected void Logout() {
        PO_NavView.clickOption(driver, "logout", "text", "Identifícate");
    }
}
