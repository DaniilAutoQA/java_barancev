package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ClientData> clients = generateClients(count);
        if (format.equals("csv")){
            saveAsCsv(clients, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(clients, new File(file));
        }
        else if (format.equals("json")){
            saveAsJson(clients, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }


    }

   private List<ClientData> generateClients(int count) {
        List<ClientData> clients = new ArrayList<ClientData>();
        for(int i=0; i < count; i++){
            clients.add(new ClientData().withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i)).withMiddlename(String.format("Petrovbich%s", i)).withAddress(String.format("address %s", i))
                    .withCompany(String.format("company %s", i)).withNickname(String.format("nickname %s", i)).withTelHome(String.format("4568652 %s", i)).withMobile(String.format("8919890 %s", i))
                        .withTelWork(String.format("3495 %s", i)).withEmail(String.format("yandex@.ru %s", i)).withEmail2(String.format("mail@.ru %s", i)).withEmail3(String.format("google@.com %s", i)).withTitle(String.format("title %s", i)).withGroupname("[none]"));
        }
        return clients;
    }

    private void saveAsCsv(List<ClientData> clients, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file);){
            for (ClientData group : clients){
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", group.getFirstname(), group.getLastname(), group.getMiddlename(), group.getAddress()
                        ,group.getCompany(), group.getNickname(), group.getTelhome(), group.getMobile(), group.getTelwork(),group.getEmail(), group.getEmail2(), group.getEmail3(), group.getTitle(), group.getGroupname()));
            }
        }
    }

    private void saveAsXml(List<ClientData> clients, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ClientData.class);
        String xml = xStream.toXML(clients);
        try(Writer writer = new FileWriter(file);){
            writer.write(xml);
        }
    }

    private void saveAsJson(List<ClientData> clients, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clients);
        try(Writer writer = new FileWriter(file);) {
            writer.write(json);
        }
    }

}
