package com.moneyControl.proyecto.models;

import org.apache.catalina.User;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Enterprise")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empresa;

    private String name;
    private int nit;
    private int phone;
    private String address;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Enterprise() {
    }

    public Enterprise(String name, int nit, int phone, String address,LocalDate createAt,LocalDate updateAt) {

        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.address = address;
        this.createAt= createAt;
        this.updateAt= updateAt;
    }

    public int getId() {
        return id_empresa;
    }

    public void setId(int id) {
        this.id_empresa = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }
}
