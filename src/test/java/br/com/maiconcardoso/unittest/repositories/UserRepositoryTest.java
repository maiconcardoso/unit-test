package br.com.maiconcardoso.unittest.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.repositories.utils.UserCreator;

@DataJpaTest
@DisplayName("Tests for user repository")
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserModel userToBeSaved;

    @BeforeEach
    public void setUp() {
        userToBeSaved = UserCreator.createUserToBeSaved();
    }

    @Test
    @DisplayName("Save the user when successful")
    public void save_PersistUser_WhenSuccessful() {
        UserModel userSaved = userRepository.save(userToBeSaved);
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Update the user when successful")
    public void update_PersistsUser_WhenSuccessful() {
        UserModel userSaved = userRepository.save(userToBeSaved);
        userSaved.setName("Jose");
        userSaved.setEmail("jose@gmail.com");
        userSaved.setPassword("1234");
        UserModel userToBeUpdated = userRepository.save(userSaved);
        Assertions.assertThat(userToBeUpdated.getName()).isNotEqualTo(userToBeSaved.getName());
        Assertions.assertThat(userToBeUpdated).isNotNull();
        Assertions.assertThat(userToBeUpdated.getId()).isEqualTo(userToBeSaved.getId());
    }

    @Test
    @DisplayName("Delete removing user when successful")
    public void delete_RemovingUser_WhenSuccessful() {
        UserModel userSaved = userRepository.save(userToBeSaved);
        userRepository.delete(userSaved);
        Optional<UserModel> userOptional = userRepository.findById(userSaved.getId());
        Assertions.assertThat(userOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Id user when successful") 
    public void findById_ReturnIdUser_whenSuccessful() {
        UserModel userSaved = userRepository.save(userToBeSaved);
        UserModel userById = userRepository.findById(userSaved.getId()).get();
        Assertions.assertThat(userById).isNotNull();
        Assertions.assertThat(userById.getId()).isEqualTo(userSaved.getId());
    }

    @Test
    @DisplayName("Return User list When successful")
    public void findUser_ReturnList_WhenSuccesful() {
        UserModel userSaved = userRepository.save(userToBeSaved);
        List<UserModel> userList = userRepository.findAll();
        Assertions.assertThat(userList).isNotEmpty();
        Assertions.assertThat(userList).contains(userSaved);
    }

    @Test
    @DisplayName("Return user empty list when successful")
    public void findUser_ReturnEmptyList_WhenSuccessful() {
        List<UserModel> userList = userRepository.findAll();
        Assertions.assertThat(userList).isEmpty();
    }

    @Test
    @DisplayName("Throws ConstraintValidationException when name is empty")
    public void throws_ConstraintValidatioException_WhenNameIsEmpty() {
        UserModel userModel = new UserModel();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> this.userRepository.save(userModel))
            .withMessageContaining("Name cannot be empty");

    }
    
}
