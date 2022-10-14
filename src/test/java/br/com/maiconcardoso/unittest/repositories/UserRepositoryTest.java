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
    
}
