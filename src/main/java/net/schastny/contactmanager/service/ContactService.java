package net.schastny.contactmanager.service;

import java.util.List;
import net.schastny.contactmanager.domain.Contact;

public interface ContactService {

	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);

	public void updateContact(Contact contact);

	public Contact getContactById(int id);

	public List<Contact> getContact(int page, int pageSize);

	public int getCountContact();

}
