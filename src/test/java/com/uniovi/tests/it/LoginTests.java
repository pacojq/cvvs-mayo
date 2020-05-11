package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;
import org.aspectj.lang.annotation.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTests extends AbstractTest {


    @Test
    public void TC1_TestValidLogin() {

        NavigateToLogin();

        PO_LoginView.fillForm(driver, "99999990A", "123456");
        SeleniumUtils.esperarSegundos(driver, 1);

        boolean failed = false;
        try {
            // Comprobamos que entramos en la pagina privada de Alumno
            PO_View.checkElement(driver, "text", "Notas del usuario");
        }
        catch (Exception e) {
            failed = true;
        }

        Logout();

        if (failed) {
            Assert.fail();
        }
    }

    @Test
    public void TC2_TestIncorrectPassword() {

        NavigateToLogin();

        PO_LoginView.fillForm(driver, "99999990A", "potato");
        SeleniumUtils.esperarSegundos(driver, 1);

        // Seguimos en la pagina de login
        PO_View.checkElement(driver, "text", "Identifícate");

        // Se ha mostrado el error
        PO_View.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
    }

    @Test
    public void TC3_TestNonExistentUser() {

        NavigateToLogin();

        PO_LoginView.fillForm(driver, "MrPotato", "123456");
        SeleniumUtils.esperarSegundos(driver, 1);

        // Seguimos en la pagina de login
        PO_View.checkElement(driver, "text", "Identifícate");

        // Se ha mostrado el error
        PO_View.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
    }
}
