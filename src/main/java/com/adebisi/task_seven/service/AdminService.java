package com.adebisi.task_seven.service;

import com.adebisi.task_seven.DTO.AdminDTO;
import com.adebisi.task_seven.model.Admin;
import com.adebisi.task_seven.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminService {


    private AdminRepo adminRepo;

    @Autowired
    public AdminService(AdminRepo adminRepo){
        this.adminRepo = adminRepo;
    }

    public Admin checkLogins(AdminDTO adminDTO){
        Optional<Admin> admin = adminRepo.findAdminByEmailAndPassword(adminDTO.getEmail(), adminDTO.getPassword());
        if(admin.isPresent()){
            return admin.get();
        }else{
            return null;
        }
    }

}
