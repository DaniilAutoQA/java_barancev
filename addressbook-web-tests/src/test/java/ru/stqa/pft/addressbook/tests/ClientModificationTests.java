package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientModificationTests extends TestBase  {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelhome("46581335").withTitle("Job").withGroupname("[none]"), true);
        }
    }

    @Test(enabled=false)
    public void testClientModification(){
        Set<ClientData> before = app.getClientHelper().all();
        ClientData modifiedClient = before.iterator().next();
        ClientData client = new ClientData().withId(modifiedClient.getId()).withLastname("Kaprin").withFirstname("Nicon").withMiddlename("Petrovbich").withAddress("Piter").withCompany("Spartak").withNickname("Coutch").withTelhome("46500").withTitle("Footboll").withGroupname("[none]");
        app.getClientHelper().modifyClient(client);
        Set<ClientData> after = app.getClientHelper().all();
        //Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedClient);
        before.add(client);
       // Assert.assertEquals(before, after);
        System.out.println("after = " + after + "; " + "before = " + before);

    }

}
