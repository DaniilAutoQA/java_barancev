package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ClientPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelHome("46581335").withTitle("Job").withGroupname("[none]"), true);
        }
    }

    @Test
    public void testClientPhones(){
        ClientData client = app.getClientHelper().all().iterator().next(); // получаем первого попавшегося контакта
        ClientData clientInfoFromEditForm = app.getClientHelper().infoFromEditForm(client);

        assertThat(client.getAllphones(), equalTo(mergePhones(clientInfoFromEditForm)));

    }

    private String mergePhones(ClientData client) {
        return Arrays.asList( client.getTelhome(),client.getMobile(),client.getTelwork())
                .stream().filter((s)-> ! s.equals(""))
                .map(ClientPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
