package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import com.sparta.glowupgirls.sakilaproject.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyStaffDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findStaffByUsername(username);
        if (staff == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(new BCryptPasswordEncoder().encode(staff.getPassword()));
        builder.roles("ADMIN");
        return builder.build();
    }
}
