package br.com.maiconcardoso.unittest.repositories;

import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("Save the user when successful")
    public void save_PersistUser_WhenSuccessful() {
        UserModel userToBeSaved = UserCreator.createUserToBeSaved();
        UserModel userSaved = userRepository.save(userToBeSaved);
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Update the user when successful")
    public void update_PersistsUser_WhenSuccessful() {
        UserModel userToBeSaved = UserCreator.createUserToBeSaved();
        UserModel userSaved = userRepository.save(userToBeSaved);
        userSaved.setName("Jose");
        userSaved.setEmail("jose@gmail.com");
        userSaved.setPassword("1234");
        UserModel userToBeUpdated = userRepository.save(userSaved);
        Assertions.assertThat(userToBeUpdated.getName()).isNotEqualTo(userToBeSaved.getName());
        Assertions.assertThat(userToBeUpdated).isNotNull();
        Assertions.assertThat(userToBeUpdated.getId()).isEqualTo(userToBeSaved.getId());
    }
    
}
