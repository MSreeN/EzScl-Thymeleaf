package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PersonRepository personRepository;

    public boolean createNewPerson(Person person){
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE.getValue());
        person.setRole(role);
        Person person1  = personRepository.save(person);
        if(person1 != null && person1.getPersonId() > 1){
            return true;
        }
        return false;
    }
}
