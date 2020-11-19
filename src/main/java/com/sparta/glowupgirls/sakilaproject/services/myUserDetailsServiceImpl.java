package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class myUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepositories customerRepositories;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepositories.findCustomerByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(new BCryptPasswordEncoder().encode((customer.getFirstName() + customer.getLastName()).toLowerCase()));
        builder.roles("USER");
        return builder.build();
    }
}
