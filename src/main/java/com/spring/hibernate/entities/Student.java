package com.spring.hibernate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "student", indexes = {
        @Index(name = "idx_student_id", columnList = "id")
})
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id", "email"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Email(message = "Not a valid email")
    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

}