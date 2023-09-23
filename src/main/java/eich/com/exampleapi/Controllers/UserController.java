package eich.com.exampleapi.Controllers;

import eich.com.exampleapi.Models.Domain.UserAddDTO;
import eich.com.exampleapi.Models.Domain.UserReadDTO;
import eich.com.exampleapi.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserReadDTO>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserReadDTO> findUserById(@PathVariable Integer userId) {
        UserReadDTO user = userService.findUserById(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserReadDTO> add(@RequestBody UserAddDTO userAddDTO){
        return ResponseEntity.ok(userService.add(userAddDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserReadDTO> updateUser(@PathVariable Integer userId, @RequestBody UserAddDTO updatedUserData) {
        UserReadDTO updatedUser = userService.updateUser(userId, updatedUserData);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserReadDTO> updateUser(
            @PathVariable Integer userId,
            @RequestBody Map<String, Object> updatedUserData
    ) {
        UserReadDTO updatedUser = userService.partialUpdateUser(userId, updatedUserData);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUser(userId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
