package com.in6k.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import com.in6k.domain.EmployeeModel;
import org.hibernate.Session;

import com.in6k.util.HibernateUtil;

public class DbProvider implements DataProvider {

    public DbProvider() {
    }

    @Override
    public void save(Identifier identifier) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(identifier);
        session.getTransaction().commit();

        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Identifier getEntityById(Integer id) throws SQLException {
        Session session = null;
        Identifier identifier = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            identifier = (Identifier) session.load(EmployeeModel.class, id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findById'", JOptionPane.OK_OPTION);

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return identifier;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Identifier> load() throws IOException, SQLException{
        Session session = null;
        List<Identifier> list = new ArrayList<Identifier>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Identifier.class).list();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'getAll'", JOptionPane.OK_OPTION);

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return list;
    }

    public  void update(Integer id, Identifier identifier) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(identifier);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Update Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        Session session = null;
        Identifier identifier = getEntityById(id);

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(identifier);
            session.getTransaction().commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Delete Error", JOptionPane.OK_OPTION);

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}