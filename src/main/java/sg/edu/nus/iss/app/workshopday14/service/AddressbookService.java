package sg.edu.nus.iss.app.workshopday14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.workshopday14.model.Contact;
import sg.edu.nus.iss.app.workshopday14.repository.AddressbookRepository;

@Service
public class AddressbookService {
    @Autowired
    private AddressbookRepository addrbookRepo;

    public void saveContact(final Contact ctc){
        addrbookRepo.save(ctc);        
    }

    public Contact findById(String contactId){
        return addrbookRepo.findById(contactId);
    }

    public List<Contact> findAll(int startIndex){
        return addrbookRepo.findAll(startIndex);    
    }

    
    
}
