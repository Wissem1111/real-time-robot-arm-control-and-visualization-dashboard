package com.robots.internship.robot.service;


import com.robots.internship.robot.model.Robot;
import com.robots.internship.robot.model.RobotStatus;
import com.robots.internship.user.model.User;

import java.util.List;

public interface RobotService {
    Robot saveRobot(Robot robot, User user);
    List<Robot> getAllRobots();
    Robot getRobotById(int id);
    Robot updateRobot(int id, Robot robot);
    void deleteRobot(int id);
    List<Robot> getRobotsByUser(User user);
    Robot updateRobotStatus(int id, RobotStatus newStatus);
}

