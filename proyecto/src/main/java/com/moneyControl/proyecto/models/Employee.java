package com.moneyControl.proyecto.models;

import org.springframework.context.annotation.Role;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="Employee")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nombre;
        private String correo;
        @ManyToOne
        @JoinColumn(name = "id_empresa")
        private Enterprise enterprise;
        @ManyToOne
        @JoinColumn(name="id_profile")
        private Profile profile;
        private String rol;
        private Boolean estado;

    private LocalDate createAt;
        private LocalDate updateAt;
        private String password;

        public Employee() {
        }

        public Employee(String nombre, String correo, Enterprise empresa, String rol,Profile profile,LocalDate createAt,LocalDate updateAt,String password,Boolean estado) {
            this.nombre = nombre;
            this.correo = correo;
            this.enterprise = empresa;
            this.rol = rol;
            this.profile=profile;
            this.createAt= createAt;
            this.updateAt= updateAt;
            this.password=password;
            this.estado= estado;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public Enterprise getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(Enterprise enterprise) {
            this.enterprise = enterprise;
        }

        public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}


