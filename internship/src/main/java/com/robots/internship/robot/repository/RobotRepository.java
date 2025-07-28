package com.robots.internship.robot.repository;


import com.robots.internship.robot.model.Robot;
import com.robots.internship.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RobotRepository extends JpaRepository<Robot, Integer> {
    Optional<Robot> findRobotById(int ref);
    List<Robot> findByCreatedBy(User user);
}
