package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTests extends AbstractTest {


    //PR05. Prueba del formulario de registro. registro con datos correctos
    @Test
    public void TC1_TestSignUpCorrect() {
        //Vamos al formulario de registro
        NavigateToSignUp();

        //Rellenamos el formulario.
        PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777",
                "77777");


        boolean failed = false;
        try {
            //Comprobamos que entramos en la sección privada
            PO_View.checkElement(driver, "text", "Notas del usuario");
        }
        catch (Exception e) {
            failed = true;
        }

        Logout();
        if (failed) {
            Assert.fail();
        }

        Logout();
    }


    // TODO casos de prueba: DNI repetido, nombre corto, contraseña invalida...



    //PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... paginationpagination-centered, Error.signup.dni.length
    @Test
    public void PR06() {

        NavigateToSignUp();

        // Rellenamos el formulario.
        PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
        PO_View.getP();

        // COmprobamos el error de DNI repetido.
        PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
        // Rellenamos el formulario.
        PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");

        // COmprobamos el error de Nombre corto .
        PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
        // Rellenamos el formulario.
        PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");
    }

}
