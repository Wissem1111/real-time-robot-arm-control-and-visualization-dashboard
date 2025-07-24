package com.robots.internship.robot;


import com.robots.internship.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RobotService implements RobotServiceImpl{

    private final RobotRepository robotRepository;

    @Override
    public Robot saveRobot(Robot robot) {
        return robotRepository.save(robot);
    }

    @Override
    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }

    @Override
    public Optional<Robot> getRobotByRef(int ref) {
        return robotRepository.findRobotByRef(ref);
    }

    @Override
    public Robot updateRobot(int ref, Robot updatedrobot) {
        Robot currentRobot = robotRepository.findRobotByRef(ref)
                .orElseThrow(()-> new RuntimeException ("Robot Not Found"));
        currentRobot.setIpAddress(updatedrobot.getIpAddress());
        currentRobot.setType(updatedrobot.getType());
        currentRobot.setStatus(updatedrobot.getStatus());
        return robotRepository.save(currentRobot);
    }

    @Override
    public void deleteRobot(int ref) {
        Robot robot = robotRepository.findRobotByRef(ref)
                .orElseThrow(()-> new RuntimeException ("Robot Not Found"));
        robotRepository.delete(robot);
    }

    @Override
    public List<Robot> getRobotsByUser(User user) {
        return robotRepository.findByUser(user);
    }

}
