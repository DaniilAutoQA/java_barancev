package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withAddress("Moscow").withTelHome("46581335").withGroupname("[none]"), true);
        }
    }

    @Test
    public void testClientEmails(){
        ClientData client = app.getClientHelper().all().iterator().next();
        ClientData clientInfoFromEditForm = app.getClientHelper().infoFromEditForm(client);

        assertThat(client.getAllEmails(), equalTo(mergePhones(clientInfoFromEditForm)));
    }
    private String mergePhones(ClientData client) {
        return Arrays.asList( client.getEmail(),client.getEmail2(),client.getEmail3())
                .stream().filter((s)-> ! s.equals(""))
                //.map(ClientMailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

   // public static String cleaned(String phone){
 ///       return phone.replaceAll("\\s", "").replaceAll("[-()]","");
 //   }
}
