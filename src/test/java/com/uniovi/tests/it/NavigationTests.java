package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NavigationTests extends AbstractTest {

    private void identify(String username, String password) {

        // logout si ya estamos identificados
        logout();
        navigateToLogin();
        PO_LoginView.fillForm(driver, username, password);
        SeleniumUtils.esperarSegundos(driver, 1);
    }

    @Test
    public void TC01_TestLoginPage_NotIdentified() {
        navigateToLogin();
        PO_View.checkElement(driver, "text", "Identifícate");
    }

    @Test
    public void TC02_TestSignUpPage_NotIdentified() {
        navigateToSignUp();
        PO_View.checkElement(driver, "text", "Registráte como usuario");
    }

    @Test
    public void TC03_TestLoginPage_RoleStudent() {

        identify("99999990A", "123456");

        // No podemos navegar a login
        SeleniumUtils.textoNoPresentePagina(driver, "Identifícate");

        // Navegar a la fuerza
        driver.navigate().to(URL + "/login");

        // Redirige a la pagina privada de Alumno
        PO_View.checkElement(driver, "text", "Notas del usuario");
    }

    @Test
    public void TC04_TestSignUpPage_RoleProfessor() {

        identify("99999993D", "123456");

        // No podemos navegar a signup
        SeleniumUtils.textoNoPresentePagina(driver, "Registrate");

        // Navegar a la fuerza
        driver.navigate().to(URL + "/signup");

        // Redirige a la pagina privada de Profesor
        PO_View.checkElement(driver, "text", "Notas del usuario");
    }

    @Test
    public void TC05_TestHomePage_RoleAdmin() {

        identify("99999988F", "123456");

        navigateToHome();

        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
    }

    @Test
    public void TC06_TestHomePage_NotIdentified() {

        // logout si ya estamos identificados
        logout();

        driver.navigate().to(URL);

        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
    }

    @Test
    public void TC07_TestListMarksPage_RoleStudent() {

        identify("99999990A", "123456");

        PO_NavView.selectMarksMenuListOption(driver);

        SeleniumUtils.textoPresentePagina(driver, "Notas");
    }

    @Test
    public void TC08_TestListMarksPage_NotIdentified() {

        // logout si ya estamos identificados
        logout();

        // No podemos navegar a la lista
        try {
            PO_NavView.selectMarksMenuListOption(driver);
            Assert.fail();
        }
        catch (Exception e) {
        }

        // Navegar a la fuerza
        driver.navigate().to(URL + "/mark/list");

        // Nos ha redirigido a login
        PO_View.checkElement(driver, "text", "Identifícate");
    }


    @Test
    public void TC09_TestAddMarksPage_RoleProfessor() {

        identify("99999993D", "123456");

        PO_NavView.selectMarksAdd(driver);

        SeleniumUtils.textoPresentePagina(driver, "Agregar Nota");
    }

    @Test
    public void TC10_TestAddMarksPage_RoleStudent() {

        identify("99999990A", "123456");

        // No podemos navegar a la lista
        try {
            PO_NavView.selectMarksMenuAddOption(driver);
            Assert.fail();
        }
        catch (Exception e) {
        }

        // Navegar a la fuerza
        driver.navigate().to(URL + "/mark/add");

        // Nos ha redirigido al inicio
        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
    }

    @Test
    public void TC11_TestAddMarksPage_NotIdentified() {

        // logout si ya estamos identificados
        logout();

        // No podemos navegar a la lista
        try {
            PO_NavView.selectMarksMenuAddOption(driver);
            Assert.fail();
        }
        catch (Exception e) {
        }

        // Navegar a la fuerza
        driver.navigate().to(URL + "/mark/list");

        // Nos ha redirigido a login
        PO_View.checkElement(driver, "text", "Identifícate");
    }

}
