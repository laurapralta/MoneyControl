package com.moneyControl.proyecto.models;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name="profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profile;

    private String image;
    private long phone;
    @ManyToOne
    @JoinColumn(name = "id")
    Employee user;
    private Date creatdAt;
    private Date updateAt;

    public Profile(String image, long phone, Employee user, Date creatdAt, Date updateAt) {
        this.image = image;
        this.phone = phone;
        this.user = user;
        this.creatdAt = creatdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id_profile;
    }

    public void setId(int id) {
        this.id_profile = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public Date getCreatdAt() {
        return creatdAt;
    }

    public void setCreatdAt(Date creatdAt) {
        this.creatdAt = creatdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
