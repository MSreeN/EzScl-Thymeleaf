package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;


import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String displayContact(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

//    @PostMapping("/saveMsg")
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String
//                                    email, @RequestParam String subject, @RequestParam String message){
//        log.info("Name: "+ name);
//        log.info(("Mobile Num: " + mobileNum));
//        log.info("Email "+ email);
//        log.info("Subject "+ subject);
//        log.info("Message "+message);
//        return new ModelAndView("redirect:/contact");
//    }

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);

        return "redirect:/contact";
    }

    @GetMapping("/displayMessages")
    public ModelAndView displayMessages(){
        List<Contact> contactMsgs = contactService.findMessageWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return  modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMsg(@PathVariable int id, Authentication authentication){
        contactService.updateMsg(id, authentication.getName());
        return  "redirect:/displayMessages";
    }
}
