package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE.getValue());
    Optional<Person> existedPersons = personRepository.findByEmail(person.getEmail());
        if(existedPersons.isPresent()){
            return false;
        }
        person.setRole(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        Person person1  = personRepository.save(person);
        if(person1 != null && person1.getPersonId() > 0){
            return true;
        }
        return false;
    }
}
