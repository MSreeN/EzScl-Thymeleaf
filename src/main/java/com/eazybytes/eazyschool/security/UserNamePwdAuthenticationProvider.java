package com.eazybytes.eazyschool.security;


import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserNamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mail = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<Person> person = personRepository.findByEmail(mail);
        Person person1 = person.get();
        if(person1 != null && person1.getPersonId() > 1 && person1.getEmail().equals(mail)
        && passwordEncoder.matches(password, person1.getPwd())){
            return new UsernamePasswordAuthenticationToken(person1.getName(), password,
                    getAuthroties(person1.getRole()));
        }
        return null;
    }

    public List<GrantedAuthority> getAuthroties(Roles role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
