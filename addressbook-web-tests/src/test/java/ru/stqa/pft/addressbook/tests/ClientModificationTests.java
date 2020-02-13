package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

public class ClientModificationTests extends TestBase  {

    @Test
    public void testClientModification(){
        app.getNavigationHelper().goToHomePage();
        app.getClientHelper().selectClient();
        app.getClientHelper().modificationClient();
        app.getClientHelper().fillClientForm(new ClientData("Saha","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", null), false);
        app.getClientHelper().submitClientModification();
    }
}
