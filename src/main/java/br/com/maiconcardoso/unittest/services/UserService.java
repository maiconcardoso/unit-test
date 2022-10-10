package br.com.maiconcardoso.unittest.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.maiconcardoso.unittest.dtos.UserDto;
import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;

    public UserModel saveUser(UserDto userDto) {
        var userToBeSaved = new UserModel();
        BeanUtils.copyProperties(userDto, userToBeSaved);
        return repository.save(userToBeSaved);
    }

    public List<UserModel> findAllUserModel() {
        return repository.findAll();
    }

}
