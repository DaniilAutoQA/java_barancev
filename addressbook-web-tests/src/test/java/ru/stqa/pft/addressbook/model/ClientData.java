package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("client")
public class ClientData {
    @XStreamOmitField
    private int id= Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String telhome;
    private String groupname;
    private String mobile;
    private String telwork;
    private String email;
    private String email2;
    private String email3;
    private String allphones;
    private String allEmails;
    private File photo;


    public String getEmail2() {return email2;}

    public String getEmail3() {return email3;}

    public String getAllEmails() {return allEmails;}

    public String getAllphones() {return allphones;}

    public String getFirstname() {return firstname;}

    public String getMiddlename() {return middlename;}

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

    public String getMobile() {return mobile;}

    public String getTelwork() {return telwork;}

    public String getEmail() {return email;}

    public String getGroupname() {return groupname; }

    public int getId() {return id; }

    public File getPhoto() {return photo; }

    public ClientData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ClientData withId(int id) {
        this.id = id;
        return this;
    }

    public ClientData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ClientData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ClientData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ClientData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ClientData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ClientData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ClientData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientData withTelHome(String telhome) {
        this.telhome = telhome;
        return this;
    }

    public ClientData withGroupname(String groupname) {
        this.groupname = groupname;
        return this;
    }

    public ClientData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ClientData withTelWork(String telwork) {
        this.telwork = telwork;
        return this;
    }
    public ClientData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ClientData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ClientData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ClientData withAllPhones(String allphones) {
        this.allphones = allphones;
        return this;
    }

    public ClientData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname);
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


}
