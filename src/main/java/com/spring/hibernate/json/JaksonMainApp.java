package com.spring.hibernate.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JaksonMainApp {

    public static void main(String[] args) throws IOException {

        try {

            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read json from file
            Student student = mapper.readValue(new File("data/read/student.json"), Student.class);

            // print object
            log.info(student.toString());

            // write json to a file
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("data/write/student.json"), student);
        } catch (Exception ex) {
            log.error("Error while reading the file " + ex.getMessage());
        }
    }
}
