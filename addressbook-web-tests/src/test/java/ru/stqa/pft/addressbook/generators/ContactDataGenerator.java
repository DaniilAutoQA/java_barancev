package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        save(clients, new File(file));

    }

    private List<ClientData> generateClients(int count) {
        List<ClientData> clients = new ArrayList<ClientData>();
        for(int i=0; i < count; i++){
            clients.add(new ClientData().withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i)).withAddress(String.format("address %s", i)));
        }
        return clients;
    }

    private void save(List<ClientData> clients, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ClientData group : clients){
            writer.write(String.format("%s;%s;%s\n", group.getFirstname(), group.getLastname(), group.getAddress()));
        }
        writer.close();
    }

}
