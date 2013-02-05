package com.in6k.domain;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.in6k.form.EmployeeForm;
import com.in6k.persistence.Identifier;
import com.in6k.persistence.ProviderFactory;
import com.in6k.util.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;

public class EmployeeModel implements Identifier {
    Integer id;
    String name;
    String lastName;
    String email;
    String password;
    String birthDate;

    ProviderFactory.ProviderType providerType;

    public EmployeeModel(){
    }

    public EmployeeModel(ProviderFactory.ProviderType providerType){
        this.providerType = providerType;
    }

    public EmployeeModel(EmployeeForm ef, ProviderFactory.ProviderType providerType) {
        id = Integer.parseInt(ef.getId());
        name = ef.getName();
        lastName = ef.getLastName();
        email = ef.getEmail();
        password = ef.getPassword();
        birthDate = ef.getBirthDate();

        this.providerType = providerType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void save() throws IOException {
        ProviderFactory.create(providerType).save(this);
    }

    public Collection<Identifier> load() throws IOException, SQLException {
        return ProviderFactory.create(providerType).load();
    }

    public Identifier getEmployeeById(Integer id) throws SQLException {
        return ProviderFactory.create(providerType).getEntityById(id);
    }

    public void delete(Integer id) throws SQLException {
        ProviderFactory.create(providerType).delete(id);
    }

    public  void update(Integer id, Identifier identifier) throws SQLException {
        ProviderFactory.create(providerType).update(id, identifier);
    }

    @Override
    public String getIdentifier() {
        return this.getEmail();
    }
}