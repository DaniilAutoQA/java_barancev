package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.List;
import java.util.Set;

public class ClientDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelhome("46581335").withTitle("Job").withGroupname("[none]"), true);
        }
    }

    @Test(enabled=false)
    public void testClientDeletion() throws InterruptedException {
        Set<ClientData> before = app.getClientHelper().all();
        ClientData deletedClient = before.iterator().next();
        app.getClientHelper().selectClientByid(deletedClient.getId());
        app.getClientHelper().deleteClient();
        Set<ClientData> after = app.getClientHelper().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedClient);
        Assert.assertEquals(after, before);
    }
}
