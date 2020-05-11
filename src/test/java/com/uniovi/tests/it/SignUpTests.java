package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTests extends AbstractTest {

    private static String ID_1 = "11111111A";
    private static String ID_2 = "22222222B";

    private static String LIMIT_SHORT = "-----";
    private static String LIMIT_LONG = "------------------------";
    private static String SHORT = "----";
    private static String LONG = "-------------------------";


    private void fill(String dni, String name, String lastName, String password, String repPassword) {

        navigateToSignUp();
        PO_RegisterView.fillForm(driver, dni, name, lastName, password, repPassword);
        SeleniumUtils.esperarSegundos(driver, 1);
    }


    @Test
    public void TC01_TestSignUp_Correct() {

        fill(ID_1, LIMIT_SHORT, LIMIT_LONG, LIMIT_SHORT, LIMIT_SHORT);

        boolean failed = false;
        try {
            //Comprobamos que entramos en la sección privada
            PO_View.checkElement(driver, "text", "Notas del usuario");
        }
        catch (Exception e) {
            failed = true;
        }

        logout();
        if (failed) {
            Assert.fail();
        }

        logout();
    }


    @Test
    public void TC02_TestSignUp_ShortId() {

        fill(SHORT, "Valid Name", "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC03_TestSignUp_LongId() {

        fill(LONG, "Valid Name", "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC04_TestSignUp_EmptyId() {

        fill("         ", "Valid Name", "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
    }

    @Test
    public void TC05_TestSignUp_RepeatedId() {

        fill(ID_1, "Valid Name", "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
    }


    @Test
    public void TC06_TestSignUp_ShortFirstName() {

        fill(ID_2, SHORT, "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC07_TestSignUp_LongFirstName() {

        fill(ID_2, LONG, "Valid Last name", "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC08_TestSignUp_ShortLastName() {

        fill(ID_2, "Valid Name", SHORT, "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC09_TestSignUp_LongLastName() {

        fill(ID_2, "Valid Name", LONG, "111111", "111111");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
    }


    @Test
    public void TC10_TestSignUp_ShortPassword() {

        fill(ID_2, "Valid Name", "Valid Last name", SHORT, SHORT);

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC11_TestSignUp_LongPassword() {

        fill(ID_2, "Valid Name", "Valid Last name", LONG, LONG);

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
    }

    @Test
    public void TC12_TestSignUp_DifferentRepeatedPassword() {

        fill(ID_2, "Valid Name", "Valid Last name", "111111", "000000");

        // Seguimos en la pagina de login y se ha mostrado el error
        PO_View.checkElement(driver, "text", "Identifícate");
        PO_View.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
    }
}
