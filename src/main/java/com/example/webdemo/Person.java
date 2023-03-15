package com.example.webdemo;
import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "persons")
public class Person {
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
//    public Person(){}
//    public Person(Integer id, String name){
//        this.id = id;
//        this.name = name;
//    }
    public Integer getId(){return this.id;}
    public String getName(){return this.name;}
    public String toString(Person person){
        return String.valueOf(person.getId()) + person.getName();
    }

}
