package com.spring.hibernate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "course")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = {"id", "title"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @Column(name = "title", length = 128)
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}