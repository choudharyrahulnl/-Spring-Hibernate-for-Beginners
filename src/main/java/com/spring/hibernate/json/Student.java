package com.spring.hibernate.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) // if there is unknown property (present in json but not in java class) just ignore
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private boolean active;
    private Address address;
    private String[] languages;
}
