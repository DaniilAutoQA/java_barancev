package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ClientModificationTests extends TestBase  {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().homePage();
        if (app.getClientHelper().getClientList().size()==0){
            app.getClientHelper().createClient(new ClientData("МАША","Ivanov","Petrovi4", "Godzila", "Work","HH","Taganrof", "2314", "test1"), true);
        }
    }

    @Test(enabled=false)
    public void testClientModification(){
        List<ClientData> before = app.getClientHelper().getClientList();
        int index = before.size() - 1;
        ClientData client = new ClientData(before.get(index).getId(),"Arkash2", null  , "Antoshin2", null, null, null, null,null,"[none]");
        app.getClientHelper().modifyClient(index, client);
        List<ClientData> after = app.getClientHelper().getClientList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(client);
        Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        System.out.println("after = " + after + "; " + "before = " + before);
        System.out.println(new HashSet<Object>(after) + " сравним " + new HashSet<Object>(before));
    }

}
