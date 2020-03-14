package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Clients extends ForwardingSet<ClientData>{

    private Set<ClientData> delegate;

    public Clients(Clients clients) {
        this.delegate = new HashSet<ClientData>(clients.delegate);

    }

    public Clients() {
        this.delegate = new HashSet<ClientData>();
    }

    @Override
    protected Set<ClientData> delegate() {
        return delegate;
    }

    public Clients withAdded(ClientData client){
        Clients clients = new Clients(this);
        clients.add(client);
        return clients;
    }
    public Clients withOut(ClientData client){
        Clients clients = new Clients(this);
        clients.remove(client);
        return clients;
    }
}
