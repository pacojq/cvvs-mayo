package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NavigationTests extends AbstractTest {

    private void identify(String username, String password) {

        // Logout si ya estamos identificados
        try {
            Logout();
            SeleniumUtils.esperarSegundos(driver, 1);
        }
        catch (Exception e) {
        }

        NavigateToLogin();
        PO_LoginView.fillForm(driver, username, password);
        SeleniumUtils.esperarSegundos(driver, 1);
    }

    @Test
    public void TC1_TestLoginPage_NotIdentified() {
        NavigateToLogin();
        PO_View.checkElement(driver, "text", "Identifícate");
    }

    @Test
    public void TC2_TestSignUpPage_NotIdentified() {
        NavigateToSignUp();
        PO_View.checkElement(driver, "text", "Registráte como usuario");
    }

    @Test
    public void TC3_TestLoginPage_RoleStudent() {

        identify("99999990A", "123456");

        // No podemos navegar a login
        SeleniumUtils.textoNoPresentePagina(driver, "Identifícate");

        // Navegar a la fuerza
        driver.navigate().to(URL + "/login");

        // Redirige a la pagina privada de Alumno
        PO_View.checkElement(driver, "text", "Notas del usuario");
    }

    @Test
    public void TC4_TestSignUpPage_RoleProfessor() {

        identify("99999993D", "123456");

        // No podemos navegar a signup
        SeleniumUtils.textoNoPresentePagina(driver, "Registrate");

        // Navegar a la fuerza
        driver.navigate().to(URL + "/signup");

        // Redirige a la pagina privada de Profesor
        PO_View.checkElement(driver, "text", "Notas del usuario");
    }

    @Test
    public void TC5_TestHomePage_RoleAdmin() {

        identify("99999988F", "123456");

        NavigateToHome();

        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
    }

    @Test
    public void TC6_TestHomePage_NotIdentified() {

        NavigateToHome();

        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
    }

}
