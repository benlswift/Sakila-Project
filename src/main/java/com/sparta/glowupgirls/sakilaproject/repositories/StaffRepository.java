package com.sparta.glowupgirls.sakilaproject.repositories;

import com.sparta.glowupgirls.sakilaproject.entities.Customer;
import com.sparta.glowupgirls.sakilaproject.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer> {

    Staff findStaffByUsername(String username);
}
