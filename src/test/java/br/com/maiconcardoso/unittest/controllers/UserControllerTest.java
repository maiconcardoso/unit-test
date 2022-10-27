package br.com.maiconcardoso.unittest.controllers;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.maiconcardoso.unittest.dtos.UserDto;
import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.services.UserService;
import br.com.maiconcardoso.unittest.utils.UserCreator;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    public void setUp() {
        
        BDDMockito.when(this.userServiceMock.findAllUserModel())
            .thenReturn(List.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userServiceMock.findByIdUserModel(ArgumentMatchers.anyInt()))
            .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.when(this.userServiceMock.saveUser(ArgumentMatchers.any(UserDto.class)))
            .thenReturn(UserCreator.createUserToBeSaved());
        
        BDDMockito.doNothing().when(this.userServiceMock).deleteUserModel(ArgumentMatchers.anyInt());

        BDDMockito.when(this.userServiceMock.updateUserModel(ArgumentMatchers.anyInt(), ArgumentMatchers.any(UserDto.class)))
            .thenReturn(UserCreator.userModelUpdate());
    }

    @Test
    @DisplayName("ListAll return User when successful")
    public void listAll_ReturnUser_WhenSuccessful() {

        List<UserModel> userList = this.userController.findAllUserModel().getBody();

        Assertions.assertThat(userList)
            .isNotEmpty()
            .isNotNull()
            .hasSize(1);
    }

    @Test
    @DisplayName("FindById return user when successful")
    public void findById_ReturnUser_WhenSuccessful() {
        Integer expectedId = UserCreator.createUserToBeSaved().getId();

        UserModel user = this.userController.findByIdUserModel(1).getBody();

        Assertions.assertThat(user).isNotNull();

        Assertions.assertThat(user.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save UserModel when successful")
    public void save_UserModel_WhenSuccessful() {
        UserModel userCreated = this.userController.saveUserModel(UserCreator.createUserDtoToBeSaved()).getBody();

        Assertions.assertThat(userCreated).isNotNull().isEqualTo(UserCreator.createUserToBeSaved());
    }

    @Test
    @DisplayName("Update UserModel when successful")
    public void update_UserModel_WhenSuccessful() {
        ResponseEntity<UserModel> userUpdated = this.userController.updateUserModel(1, UserCreator.createUserDtoToBeSaved());

        Assertions.assertThat(userUpdated)
            .isNotNull();

        Assertions.assertThat(userUpdated.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Delete UserModel when successful")
    public void delete_UserModel_WhenSuccessful() {
        ResponseEntity<Void> userDeleted = this.userController.deleteUserModel(1);

        Assertions.assertThat(userDeleted).isNotNull();
        Assertions.assertThat(userDeleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
