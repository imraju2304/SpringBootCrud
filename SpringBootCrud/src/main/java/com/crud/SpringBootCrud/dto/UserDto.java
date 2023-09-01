package com.crud.SpringBootCrud.dto;

import com.crud.SpringBootCrud.entities.Gender;
import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private Boolean active;

}
