package com.sparta.glowupgirls.sakilaproject.services;

import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import com.sparta.glowupgirls.sakilaproject.repositories.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff getStaffByUsername(String staffUsername) {
        return staffRepository.findStaffByUsername(staffUsername);
    }
}
