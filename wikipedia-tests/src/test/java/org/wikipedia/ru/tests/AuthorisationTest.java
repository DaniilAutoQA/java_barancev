package org.wikipedia.ru.tests;

import org.testng.annotations.Test;
import org.wikipedia.ru.appmanager.ApplicationManager;
import org.wikipedia.ru.appmanager.SessionHelper;

import static org.testng.Assert.assertEquals;

public class AuthorisationTest extends ApplicationManager {



    @Test
    public void testAuthorization(){
        SessionHelper sessionhelper = new SessionHelper(driver, wait);
        sessionhelper.login("TestForWiki", "1234!@#$");
        assertEquals(sessionhelper.getLinkText("TestForWiki"),"TestForWiki");
    }


    @Test
    public void testValidationLogin(){
        SessionHelper sessionhelper = new SessionHelper(driver, wait);
        sessionhelper.login("dfcz", "1234!@#$");
        assertEquals(sessionhelper.getValidationText(), "Введены неверные имя участника или пароль. Попробуйте ещё раз.");
    }

    @Test
    public void testValidationPassoword(){
        SessionHelper sessionhelper = new SessionHelper(driver, wait);
        sessionhelper.login("TestForWiki", "1");
        assertEquals(sessionhelper.getValidationText(), "Введены неверные имя участника или пароль. Попробуйте ещё раз.");
    }


}
