package com.adebisi.task_seven.model;

import com.adebisi.task_seven.DTO.AdminDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="admin")
@NoArgsConstructor
public class Admin {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;

    public Admin(AdminDTO adminDTO){
        email = adminDTO.getEmail();
        password = adminDTO.getPassword();
    }

}
