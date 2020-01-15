package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ClientDeletionTests extends TestBase {

    @Test
    public void testClientDeletion() throws InterruptedException {
        app.getNavigationHelper().goToHomePage();
        app.getClientHelper().selectClient();
        app.getClientHelper().deleteClient();


    }
}
