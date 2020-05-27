package org.wikipedia.ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wikipedia.ru.appmanager.ApplicationManager;
import org.wikipedia.ru.appmanager.SessionHelper;
import org.wikipedia.ru.model.PanelNavigation;

import static org.testng.AssertJUnit.assertEquals;


public class PanelMenuToolsTests extends ApplicationManager {


    @Test
    public void  testContentMenuTools(){
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        Assert.assertEquals(panelnavigation.getMenuItem().size(), panelnavigation.menu.size());
        Assert.assertEquals(panelnavigation.getMenuItem(), panelnavigation.menu);
    }

    @Test
    public void testUntitledLinksHere() {
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        panelnavigation.goToMenuItem("Ссылки сюда");
        assertEquals(driver.getTitle(), "Страницы, ссылающиеся на «Википедия:Алфавитный указатель» — Википедия");
    }

    @Test
    public void testUntitledRecentChangesLinked(){
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        panelnavigation.goToMenuItem("Связанные правки");
        assertEquals(driver.getTitle(), "Связанные правки — Википедия");
    }

    @Test
    public void testUntitledSpecialPages(){
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        panelnavigation.goToMenuItem("Служебные страницы");
        assertEquals(driver.getTitle(), "Служебные страницы — Википедия");
    }

    @Test
    public void testUntitledPermanentLink(){
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        panelnavigation.goToMenuItem("Постоянная ссылка");
        assertEquals(driver.getTitle(), "Википедия:Алфавитный указатель — Википедия");
    }

    @Test
    public void testUntitledInfoPages(){
        PanelNavigation panelnavigation = new PanelNavigation(driver, wait);
        panelnavigation.goToMenuItem("Сведения о странице");
        assertEquals(driver.getTitle(), "Сведения о странице «Википедия:Алфавитный указатель» — Википедия");
    }

}
