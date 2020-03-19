package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientCreationTests extends TestBase {


    @Test(enabled=true)
    public void testUntitledTestCase() {
        Clients before = app.getClientHelper().all();
        File photo = new File("src/test/resources/file.jpg");
        ClientData client = new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withPhoto(photo).withCompany("Auriga").withNickname("Tester").withTelHome("46 58 13 35").withMobile("+7(919)346").withTelWork("347 000").withEmail("mail@.ru").withEmail2("yandex@.ru").withEmail3("googl@.com").withTitle("Job").withGroupname("[none]");
        app.getClientHelper().createClient(client,true);
        app.goTO().homePage();
        Clients after = app.getClientHelper().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(client.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test (enabled=false)
    public void testCurrentDir() {
        File currentDir = new File("."); //определение текущей директории
        File photo = new File("src/test/resources/file.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists()); //выводит статус наличия файла


    }

}
