package com.lisong.learn.core.io;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public class Exercise31 extends ArrayList<Person> {

    private static void writeToXml(List<Person> people, String fileName) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            Element root = new Element("people");
            Document doc = new Document(root);
            for(Person person : people)
                root.appendChild(person.toXML());
            Serializer serializer = new Serializer(out, Charset.defaultCharset().name());
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(doc);
            serializer.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readFromXml(String fileName) throws Exception {
        Document doc = new Builder().build(new File(fileName));
        Element people = doc.getRootElement();
        for(Element person : people.getChildElements()) {
            print(new Person(person));
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "JavaCore/src/main/java/com/lisong/learn/core/io/file/exercise31.xml";
        fileName= new File(fileName).getCanonicalPath();
        List<Person> people = new ArrayList<>(Arrays.asList(
        new Person(new PersonName("Ming", "Li"), "Shanghai", "liming@163.com", "18929590920"),
        new Person(new PersonName("Lei", "Wang"), "Beijing", "leiwang@yahoo.com", "18029390110"),
        new Person(new PersonName("Han", "Sun"), "Guangzhou", "hansun@gmail.com", "13815215436")));
        writeToXml(people, fileName);
        readFromXml(fileName);
    }
}

class Person {

    private PersonName name;
    private String address;
    private String email;
    private String phone;

    Person(PersonName name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    Person(Element person) {
        name = new PersonName(person.getFirstChildElement("name"));
        address = person.getFirstChildElement("address").getValue();
        email = person.getFirstChildElement("email").getValue();
        phone = person.getFirstChildElement("phone").getValue();
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + name +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    Element toXML() {
        Element person = new Element("person");
        Element address = new Element("address");
        address.appendChild(this.address);
        Element email = new Element("email");
        email.appendChild(this.email);
        Element phone = new Element("phone");
        phone.appendChild(this.phone);
        person.appendChild(name.toXML());
        person.appendChild(address);
        person.appendChild(email);
        person.appendChild(phone);
        return person;
    }
}

class PersonName {
    private String first;
    private String last;

    PersonName(String first, String last) {
        this.first = first;
        this.last = last;
    }

    PersonName(Element name) {
        first = name.getFirstChildElement("first").getValue();
        last = name.getFirstChildElement("last").getValue();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "first='" + first + '\'' +
                ", last='" + last + '\'';
    }

    Element toXML() {
        Element name = new Element("name");
        Element first = new Element("first");
        first.appendChild(this.first);
        Element last = new Element("last");
        last.appendChild(this.last);
        name.appendChild(first);
        name.appendChild(last);
        return name;
    }
}