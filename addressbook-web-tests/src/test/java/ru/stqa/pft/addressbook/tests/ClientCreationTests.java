package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientCreationTests extends TestBase {


    @Test(enabled=true)
    public void testUntitledTestCase() {
        Clients before = app.getClientHelper().all();
        ClientData client = new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelhome("46581335").withTitle("Job").withGroupname("[none]");
        app.getClientHelper().createClient(client,true);
        app.goTO().homePage();
        Clients after = app.getClientHelper().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(client.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
