package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ClientModificationTests extends TestBase  {

    @Test
    public void testClientModification(){
        app.getNavigationHelper().goToHomePage();
       if (! app.getClientHelper().isThereAClient()){
            app.getClientHelper().createClient(new ClientData("МАША","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", "test1"), true);
        }
        List<ClientData> before = app.getClientHelper().getClientList();
        ClientData client = new ClientData("Arkash2", null  , "Antoshin2", null, null, null, null,null,"[none]");
        app.getClientHelper().selectClient(before.size() -1);
        app.getClientHelper().modificationClient();
        app.getClientHelper().fillClientForm((client), false);
        app.getClientHelper().submitClientModification();
        app.getNavigationHelper().goToHomePage();
        List<ClientData> after = app.getClientHelper().getClientList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
        before.add(client);
        Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        System.out.println("after = " + after + "; " + "before = " + before);
        System.out.println(new HashSet<Object>(after) + " сравним " + new HashSet<Object>(before));
    }
}
