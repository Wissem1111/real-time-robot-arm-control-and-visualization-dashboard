package com.robots.internship.robot.controller;



import com.robots.internship.robot.model.Robot;
import com.robots.internship.robot.service.RobotServiceImpl;
import com.robots.internship.robot.model.RobotStatus;
import com.robots.internship.user.model.User;
import com.robots.internship.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/robots")
@AllArgsConstructor
public class RobotController {

    private final RobotServiceImpl robotService;
    private final UserRepository userRepository;


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<Robot> createRobot(@RequestBody Robot robot, Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(robotService.saveRobot(robot, user));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<Robot>> getRobots() {
        return ResponseEntity.ok(robotService.getAllRobots());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Robot> getRobot(@PathVariable int id) {
        return ResponseEntity.ok(robotService.getRobotById(id));
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Robot> updateRobot(@PathVariable int id, @RequestBody Robot robot) {
        return ResponseEntity.ok(robotService.updateRobot(id, robot));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRobot(@PathVariable int id) {
        robotService.deleteRobot(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/my")
    public ResponseEntity<List<Robot>> getMyRobots(Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(robotService.getRobotsByUser(user));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Robot> updateStatus(@PathVariable int id, @RequestBody RobotStatus status) {
        return ResponseEntity.ok(robotService.updateRobotStatus(id, status));
    }

}
