package com.robots.internship.robot.service;



import com.robots.internship.robot.model.Robot;
import com.robots.internship.robot.model.RobotStatus;
import com.robots.internship.robot.repository.RobotRepository;
import com.robots.internship.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;


    @Override
    public Robot saveRobot(Robot robot, User user) {
        robot.setCreatedBy(user);
        return robotRepository.save(robot);
    }


    @Override
    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }



    @Override
    public Robot getRobotById(int id) {
        Robot robot = robotRepository.findRobotById(id)
                .orElseThrow(() -> new RuntimeException("Robot not found"));
        return robot;
    }

    @Override
    public Robot updateRobot(int id, Robot updatedRobot) {
        Robot existing = robotRepository.findRobotById(id)
                .orElseThrow(() -> new RuntimeException("Robot not found"));
        existing.setIpAddress(updatedRobot.getIpAddress());
        existing.setStatus(updatedRobot.getStatus());
        return robotRepository.save(existing);
    }



    @Override
    public void deleteRobot(int id) {
        Robot robot = robotRepository.findRobotById(id)
                .orElseThrow(()-> new RuntimeException ("Robot not found"));
        robotRepository.delete(robot);
    }


    @Override
    public List<Robot> getRobotsByUser(User user) {
        return robotRepository.findByCreatedBy(user);
    }


    public Robot updateRobotStatus(int id, RobotStatus newStatus) {
        Robot robot = robotRepository.findRobotById(id)
                .orElseThrow(() -> new RuntimeException("Robot not found with id: " + id));
        robot.setStatus(newStatus);

        Robot updatedRobot = robotRepository.save(robot);
        return updatedRobot;
    }

}
