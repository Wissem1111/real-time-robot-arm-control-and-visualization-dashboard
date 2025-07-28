package com.robots.internship.robot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robots.internship.user.model.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ref;

    private String ipAddress;

    private String type;

    @Enumerated(EnumType.STRING)
    private RobotStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User createdBy;

}
