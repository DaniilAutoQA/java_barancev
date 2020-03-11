package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ClientCreationTests extends TestBase {


    @Test
    public void testUntitledTestCase() {
        List<ClientData> before = app.getClientHelper().getClientList();
        ClientData client = new ClientData("Arkash2", null  , "Antoshin2", null, null, null, null,null,"[none]");
        app.getClientHelper().createClient(client,true);
        app.getNavigationHelper().goToHomePage();
        List<ClientData> after = app.getClientHelper().getClientList();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(client);
        Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        System.out.println(before.size());
        System.out.println(after.size());
        // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Assert.assertEquals(before, after);

    }

}
