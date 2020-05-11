package com.uniovi.tests.it;

import com.uniovi.tests.AbstractTest;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import org.junit.Test;

public class LanguageTests extends AbstractTest {


    @Test
    public void ChangeLanguageToEnglish() {
        PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
        PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
                PO_Properties.getENGLISH());
        // SeleniumUtils.esperarSegundos(driver, 2);
    }

}
