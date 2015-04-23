package net.schastny.contactmanager.dao;

import java.util.List;

import net.schastny.contactmanager.domain.Contact;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addContact(Contact contact) {
        sessionFactory.getCurrentSession().persist(contact);
    }

    @SuppressWarnings("unchecked")
    public List<Contact> listContact() {

        return sessionFactory.getCurrentSession().createQuery("from Contact")
                .list();
    }

    @Override
    public void removeContact(int id) {
        Contact contact = (Contact) sessionFactory.getCurrentSession().load(Contact.class, id);
        if (null != contact) {
            sessionFactory.getCurrentSession().delete(contact);
        }

    }

    @Override
    public Contact getContactById(int id) {
        Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, new Integer(id));
        return contact;
    }

    @Override
    public void updateContact(Contact contact) {
        sessionFactory.getCurrentSession().update(contact);
    }


    public List<Contact> getContactList(int page, int pageSize) {
        //int limit;//elPerPage
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from Contact");
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return (List<Contact>) query.list();
    }

    @Override
    public int getCountContact() {
       int count =((Long)sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Contact").uniqueResult()).intValue();
        return count;
    }


}
