package com.crud.SpringBootCrud.dto;

import com.crud.SpringBootCrud.entities.Gender;
import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Data
public class RegistrationDto {
    private String name;
    private String email;
    private String password;
    private Gender gender;
}
