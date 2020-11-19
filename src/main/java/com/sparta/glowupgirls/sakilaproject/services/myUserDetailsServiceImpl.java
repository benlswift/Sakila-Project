package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import com.sparta.glowupgirls.sakilaproject.repositories.CustomerRepositories;
import com.sparta.glowupgirls.sakilaproject.repositories.StaffRepository;
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
    @Autowired
    private StaffRepository staffRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepositories.findCustomerByEmail(username);
        Staff staff = staffRepository.findStaffByUsername(username);

        if (customer == null&& staff ==null) {
            throw new UsernameNotFoundException("Could not find user");
        } else if (customer!= null) {
            User.UserBuilder builder = null;
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode((customer.getFirstName() + customer.getLastName()).toLowerCase()));
            builder.roles("USER");
            return builder.build();
        } else {
            User.UserBuilder builder = null;
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(staff.getPassword()));
            builder.roles("ADMIN");
            return builder.build();
        }
    }

}
