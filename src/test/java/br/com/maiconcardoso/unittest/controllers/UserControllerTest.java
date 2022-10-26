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

}
