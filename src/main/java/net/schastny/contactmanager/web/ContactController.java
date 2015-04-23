package net.schastny.contactmanager.web;

import java.util.Map;

import net.schastny.contactmanager.dao.ContactDAO;
import net.schastny.contactmanager.dao.ContactDAOImpl;
import net.schastny.contactmanager.domain.Contact;
import net.schastny.contactmanager.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String listContacts(Model model) {
//        model.addAttribute("contact", new Contact());
//        model.addAttribute("contactList", this.contactService.listContact());
//
//        return "contact";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getContact(@RequestParam(value = "pageNum", required = false) int page,
                             @RequestParam(value = "pageSize", required = false) int pageSize, ModelMap model) {
        model.addAttribute("contact", new Contact());
        int count = this.contactService.getCountContact();
        model.addAttribute("currentPage", page);
        model.addAttribute("currentPageSize", pageSize);
        model.addAttribute("noOfPages", (int) Math.ceil(count / pageSize));
        model.addAttribute("contactList", this.contactService.getContact(page, pageSize));
        return "contact";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact) {
        Integer contactId = contact.getId();
        if (contact.getId() == null) {
            //new contact, add it
            this.contactService.addContact(contact);
        } else {
            //existing contact, call update
            this.contactService.updateContact(contact);
        }
        return "redirect:/index";
    }

    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") int contactId) {
        this.contactService.removeContact(contactId);
        return "redirect:/index";
    }

    @RequestMapping("/update/{contactId}")
    public String editPerson(@PathVariable("contactId") int id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("listContact", this.contactService.listContact());
        return "contact";
    }


}
