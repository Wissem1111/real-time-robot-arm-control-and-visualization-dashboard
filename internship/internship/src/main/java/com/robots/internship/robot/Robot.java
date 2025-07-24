package com.robots.internship.robot;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robots.internship.user.User;
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
    private int ref;

    private String ipAddress;

    private String type;

    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
