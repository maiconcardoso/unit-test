package br.com.maiconcardoso.unittest.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.maiconcardoso.unittest.dtos.UserDto;
import br.com.maiconcardoso.unittest.exceptions.EntityNotFoundException;
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

    public UserModel findByIdUserModel(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    }

    public List<UserModel> findByNameUser(String name) {
        return repository.findByNameUser(name)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    }

    public UserModel updateUserModel(Integer id, UserDto userDto) {
        UserModel userById = findByIdUserModel(id);
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(userById.getId());
        return repository.save(userModel);
    }

    public void deleteUserModel(Integer id) {
        UserModel userToBeDeleted = findByIdUserModel(id);
        repository.delete(userToBeDeleted);
    }

}
