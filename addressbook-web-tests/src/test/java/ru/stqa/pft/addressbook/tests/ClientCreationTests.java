package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import java.util.Set;

public class ClientCreationTests extends TestBase {


    @Test(enabled=true)
    public void testUntitledTestCase() {
        Set<ClientData> before = app.getClientHelper().all();
        ClientData client = new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withCompany("Auriga").withNickname("Tester").withTelhome("46581335").withTitle("Job").withGroupname("[none]");
        app.getClientHelper().createClient(client,true);
        app.goTO().homePage();
        Set<ClientData> after = app.getClientHelper().all();
        Assert.assertEquals(after.size(), before.size()+1);

        //сортировка не нужна проверка по множеству
        /*
        Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        System.out.println(before.size());
        System.out.println(after.size());
        */
        // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        client.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(client);
        Assert.assertEquals(before, after);

    }

}
