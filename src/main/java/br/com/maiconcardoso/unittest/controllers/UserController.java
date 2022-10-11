package br.com.maiconcardoso.unittest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maiconcardoso.unittest.dtos.UserDto;
import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserModel>> findAllUserModel() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUserModel());
    }  

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findByIdUserModel(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdUserModel(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveUserModel(@RequestBody @Valid UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    
}
