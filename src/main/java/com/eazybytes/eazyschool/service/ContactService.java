package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.EazySchoolConstants;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@SessionScope
@Data
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN.getValue());
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS.getValue());
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMessageWithOpenStatus() {
        List<Contact> c = contactRepository.findMsgWithStatus(EazySchoolConstants.OPEN.getValue());
        System.out.println(c);
        return c;
    }

    public boolean updateMsg(int id, String name){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(id, EazySchoolConstants.CLOSE.getValue(), name);
        if(result > 0){
            isUpdated = true;

        }
        return  isUpdated;
    }
}
