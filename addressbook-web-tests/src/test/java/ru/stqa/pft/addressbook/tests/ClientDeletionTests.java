package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.List;

public class ClientDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData("МАША","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", "test1"), true);
        }
    }

    @Test(enabled=false)
    public void testClientDeletion() throws InterruptedException {
        List<ClientData> before = app.getClientHelper().getClientList();
        app.getClientHelper().selectClient(before.size() -1);
        app.getClientHelper().deleteClient();
        List<ClientData> after = app.getClientHelper().getClientList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
