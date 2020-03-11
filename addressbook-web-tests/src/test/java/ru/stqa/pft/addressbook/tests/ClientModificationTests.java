package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ClientModificationTests extends TestBase  {

    @Test
    public void testClientModification(){
        app.getNavigationHelper().goToHomePage();
       if (! app.getClientHelper().isThereAClient()){
            app.getClientHelper().createClient(new ClientData("МАША","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", "test1"), true);
        }
        List<ClientData> before = app.getClientHelper().getClientList();
        app.getClientHelper().selectClient(before.size() -1);
        app.getClientHelper().modificationClient();
        app.getClientHelper().fillClientForm(new ClientData("Saha","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", null), false);
        app.getClientHelper().submitClientModification();
        System.out.println(before);
    }
}
