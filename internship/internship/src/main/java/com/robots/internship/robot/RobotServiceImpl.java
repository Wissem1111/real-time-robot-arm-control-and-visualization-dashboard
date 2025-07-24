package com.robots.internship.robot;


import com.robots.internship.user.User;

import java.util.List;
import java.util.Optional;

public interface RobotServiceImpl {
    Robot saveRobot(Robot robot);
    List<Robot> getAllRobots();
    Optional<Robot> getRobotByRef(int ref);
    Robot updateRobot(int ref,Robot robot);
    void deleteRobot(int ref);
    List<Robot> getRobotsByUser(User user);
}
