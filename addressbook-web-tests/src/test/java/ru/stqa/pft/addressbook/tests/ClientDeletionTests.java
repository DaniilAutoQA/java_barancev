package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.List;

public class ClientDeletionTests extends TestBase {

    @Test
    public void testClientDeletion() throws InterruptedException {
        app.getNavigationHelper().goToHomePage();
        List<ClientData> before = app.getClientHelper().getClientList();
        if (! app.getClientHelper().isThereAClient()){
            app.getClientHelper().createClient(new ClientData("МАША","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", "test1"), true);
        }
        app.getClientHelper().selectClient(before.size() -1);
        app.getClientHelper().deleteClient();
        List<ClientData> after = app.getClientHelper().getClientList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
