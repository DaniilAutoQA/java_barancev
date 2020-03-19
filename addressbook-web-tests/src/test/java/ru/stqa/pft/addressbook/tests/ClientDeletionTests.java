package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ClientDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelHome("46581335").withTitle("Job").withGroupname("[none]"), true);
        }
    }

    @Test(enabled=true)
    public void testClientDeletion() {
        Clients before = app.getClientHelper().all();
        ClientData deletedClient = before.iterator().next();
        app.getClientHelper().selectClientByid(deletedClient.getId());
        app.getClientHelper().deleteClient();
        app.goTO().switchTo();
        Clients after = app.getClientHelper().all();
        assertThat(after.size(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.withOut(deletedClient)));
    }
}
