package sg.edu.nus.iss.app.workshopday14.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshopday14.model.Contact;
import sg.edu.nus.iss.app.workshopday14.service.AddressbookService;

@Controller
// @RequestMapping
public class AddressBookController {
    @Autowired
    private AddressbookService addrbkSvc;

    @GetMapping(value="/")
    public String showContactForm(Model model){
        model.addAttribute("contact", new Contact());
        return "contactform";
    }

    @PostMapping(path="/contact")
    public String saveContact(@Valid Contact contact, BindingResult result, Model model,HttpServletResponse response){
        
        if(result.hasErrors()){
            return "contactform";
        }
        addrbkSvc.saveContact(contact);
        model.addAttribute("contact", contact);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "contact";
    }

    @GetMapping("/contact")
    public String getAllContact(Model model, @RequestParam(name="startIndex") Integer startIndex){
        List<Contact> result = addrbkSvc.findAll(startIndex);
        model.addAttribute("contacts", result);
        return "list";
    }

    @GetMapping(path="/contact/{contactId}")
    public String getContactDetails(Model model, @PathVariable(value="contactId") String contactId){
        Contact ctc = addrbkSvc.findById(contactId);
        model.addAttribute("contact",ctc);

        return "contact";
    }
}
