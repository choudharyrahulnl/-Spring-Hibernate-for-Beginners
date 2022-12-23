package com.spring.hibernate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "instructor_detail")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(of = {"id", "youtubeChannel", "hobby"})
@EqualsAndHashCode(of = {"id"})
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @Column(name = "youtube_channel", length = 128)
    private String youtubeChannel;

    @Size(max = 45)
    @Column(name = "hobby", length = 45)
    private String hobby;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

}