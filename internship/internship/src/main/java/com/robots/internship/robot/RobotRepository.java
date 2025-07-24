package com.robots.internship.robot;


import com.robots.internship.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RobotRepository extends JpaRepository<Robot, Integer> {
    Optional<Robot> findRobotByRef(int ref);
    List<Robot> findByUser(User user);

}
