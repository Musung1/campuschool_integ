package com.example.campuschool_backend.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Description {
    private String education;
    private String history;
    private String description;

    public static Description of(){
        Description description = new Description();
        description.setDescription("");
        description.setEducation("");
        description.setHistory("");
        return description;
    }
}
