package com.moneyControl.proyecto.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="moneymovement")
public class Money_movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_movement;
    private long amount;
    private String concept;

    @ManyToOne
    @JoinColumn(name="id")
    private Employee user;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;

    public Money_movement() {
    }

    public Money_movement(long amount, String concept, Employee user, Date createAt) {
        this.amount = amount;
        this.concept = concept;
        this.user = user;
        this.createAt= createAt;
    }

    public int getId_movement() {
        return id_movement;
    }

    public void setId_movement(int id_movement) {
        this.id_movement = id_movement;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


}