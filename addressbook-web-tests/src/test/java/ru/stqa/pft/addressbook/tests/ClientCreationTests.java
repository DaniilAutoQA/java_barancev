package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/clients.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new ClientData().withFirstname(split[0]).withLastname(split[1]).withMiddlename(split[2]).withAddress(split[3]).withCompany(split[4])
                    .withNickname(split[5]).withTelHome(split[6]).withMobile(split[7]).withTelWork(split[8]).withEmail(split[9]).withEmail2(split[10]).withEmail3(split[11]).withTitle(split[12]).withGroupname(split[13])});
            line = reader.readLine();
        }
        return list.iterator();
        }
    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/clients.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ClientData.class);
        List<ClientData> groups = (List<ClientData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/clients.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ClientData> groups = gson.fromJson(json, new TypeToken<List<ClientData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validGroupsFromJson")
    public void testUntitledTestCase(ClientData client) {
        Clients before = app.getClientHelper().all();
        //File photo = new File("src/test/resources/file.jpg");
        //ClientData client = new ClientData().withLastname("Ivanov").withFirstname("Andrey").withMiddlename("Petrovbich").withAddress("Moscow").withPhoto(photo).withCompany("Auriga").withNickname("Tester").withTelHome("46 58 13 35").withMobile("+7(919)346").withTelWork("347 000").withEmail("mail@.ru").withEmail2("yandex@.ru").withEmail3("googl@.com").withTitle("Job").withGroupname("[none]");
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
