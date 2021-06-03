package pro.restommender.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.dto.responseDTO.UserResponseDTO;
import pro.restommender.mapper.UserMapper;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.User;
import pro.restommender.service.AuthenticatedUserService;
import pro.restommender.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //TODO: na kraju otkomentarisati zakomentarisano
    @GetMapping(path = "blocked")
    // @PreAuthorize("hasRole('ROLE_AUTH_USER')")
    public ResponseEntity<?> getAllBlocked() {

        try {

            // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // if (user.getType().name().equals("USER"))
            //     return new ResponseEntity<>("User type must be admin.", HttpStatus.UNAUTHORIZED);

            List<AuthenticatedUser> users = userService.getAll(true);

            List<UserResponseDTO> usersDto = userMapper.toDtoList(users);

            return new ResponseEntity<>(usersDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: na kraju otkomentarisati zakomentarisano
    @GetMapping(path = "unblocked")
    // @PreAuthorize("hasRole('ROLE_AUTH_USER')")
    public ResponseEntity<?> getAllUnblocked() {

        try {
            // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // if (user.getType().name().equals("USER"))
            //     return new ResponseEntity<>("User type must be admin.", HttpStatus.UNAUTHORIZED);

            List<AuthenticatedUser> users = userService.getAll(false);

            List<UserResponseDTO> usersDto = userMapper.toDtoList(users);

            return new ResponseEntity<>(usersDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
