package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

public class ClientDetailsTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData().withLastname("Ivanov").withFirstname("Andrey").withAddress("Moscow").withTelHome("46581335").withMobile("8919890").withTelWork("347900").withEmail("mail@m.ru").withEmail2("ya@y.ru").withEmail3("googl@g.com").withGroupname("[none]"), true);
        }
    }
    @Test(enabled=true)
    public void testClientDetailsForm(){
        ClientData client = app.getClientHelper().all().iterator().next();
       // ClientData clientInfoFromDetailsForm = app.getClientHelper().infoFromDetailsForm(client);
    }
}
