package com.robots.internship.robot;



import com.robots.internship.user.User;
import com.robots.internship.user.UserRepository;
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

    private final RobotService robotService;
    private final UserRepository userRepository;


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<Robot> createRobot(@RequestBody final Robot robot, Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        robot.setUser(user);
        return ResponseEntity.ok(robotService.saveRobot(robot));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<Robot>> getRobots() {
        return ResponseEntity.ok(robotService.getAllRobots());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/{ref}")
    public ResponseEntity<Robot> getRobot(@PathVariable int ref) {
        return robotService.getRobotByRef(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{ref}")
    public ResponseEntity<Robot> updateRobot(@PathVariable int ref, @RequestBody final Robot robot) {
        return ResponseEntity.ok(robotService.updateRobot(ref, robot));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ref}")
    public ResponseEntity<Void> deleteRobot(@PathVariable int ref) {
        robotService.deleteRobot(ref);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/my")
    public ResponseEntity<List<Robot>> getMyRobots(Principal connectedUser) {
        User user = userRepository.findByEmail(connectedUser.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Robot> myRobots = robotService.getRobotsByUser(user);
        return ResponseEntity.ok(myRobots);
    }

}
