package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ClientData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String telhome;
    private final String groupname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname, lastname);
    }

    public ClientData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String telhome, String groupname) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;

        this.title = title;
        this.company = company;
        this.address = address;
        this.telhome = telhome;
        this.groupname = groupname;
    }

    public ClientData(int id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String telhome, String groupname) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.telhome = telhome;
        this.groupname = groupname;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", telhome='" + telhome + '\'' +
                ", groupname='" + groupname + '\'' +
                '}';
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getTelhome() {
        return telhome;
    }

    public String getGroupname() {
        return groupname;
    }

    public int getId() {return id; }
}
