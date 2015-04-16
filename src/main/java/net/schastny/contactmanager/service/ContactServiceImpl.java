package net.schastny.contactmanager.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.schastny.contactmanager.dao.ContactDAO;
import net.schastny.contactmanager.domain.Contact;
 
@Service
public class ContactServiceImpl implements ContactService {
 
    @Autowired
    private ContactDAO contactDAO;
 
    @Transactional
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }
 
    @Transactional
    public List<Contact> listContact() {
 
        return contactDAO.listContact();
    }
 
    @Transactional
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
    }

    @Transactional
    public void updateContact(Contact contact){
        contactDAO.updateContact(contact);
    }

    @Transactional
    public Contact getContactById(int id) {
        return this.contactDAO.getContactById(id);
    }


}
