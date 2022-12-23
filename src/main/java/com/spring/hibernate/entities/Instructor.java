package com.spring.hibernate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(of = {"id", "firstName", "lastName", "email"})
@EqualsAndHashCode(of = {"id"})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    /**
     * Convenience Method for BiDirectional Mapping
     */
    public void add(Course course) {
        if(courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

}