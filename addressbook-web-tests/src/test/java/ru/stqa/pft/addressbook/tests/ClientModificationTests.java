package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

public class ClientModificationTests extends TestBase  {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelHome("46581335").withTitle("Job").withGroupname("[none]"), true);
        }
    }

    @Test(enabled=false)
    public void testClientModification(){
        Clients before = app.getClientHelper().all();
        ClientData modifiedClient = before.iterator().next();
        ClientData client = new ClientData().withId(modifiedClient.getId()).withLastname("Kaprin").withFirstname("Nicon").withMiddlename("Petrovbich").withAddress("Piter").withCompany("Spartak").withNickname("Coutch").withTelHome("46500").withTitle("Footboll").withGroupname("[none]");
        app.getClientHelper().modifyClient(client);
        Clients after = app.getClientHelper().all();
        //Assert.assertEquals(after.size(), before.size());
        //before.remove(modifiedClient);
        //before.add(client);
       // Assert.assertEquals(before, after);
        //MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedClient).withAdded(client)));
        System.out.println("after = " + after + "; " + "before = " + before);

    }

}
