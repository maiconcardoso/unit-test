package br.com.maiconcardoso.unittest.services;

import java.util.List;
import java.util.Optional;

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

import br.com.maiconcardoso.unittest.exceptions.EntityNotFoundException;
import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.repositories.UserRepository;
import br.com.maiconcardoso.unittest.utils.UserCreator;

@ExtendWith(SpringExtension.class) // Usado para testar a aplicação sem executa-la
public class UserServiceTest {
    
    @InjectMocks // Usado para injetar a dependência da classe que será testada.
    private UserService userService;

    @Mock // Usada para injetar a dependência que está sendo usada na classe testada.
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void setUp() {

        // PageImpl<UserModel> userPage = new PageImpl<>(List.of(UserCreator.createUserToBeSaved()));

        // BDDMockito.when(this.userRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
        //     .thenReturn(userPage); // Resultado Paginado

        BDDMockito.when(this.userRepositoryMock.findAll())
            .thenReturn(List.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userRepositoryMock.findById(ArgumentMatchers.anyInt()))
            .thenReturn(Optional.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userRepositoryMock.save(ArgumentMatchers.any()))
            .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.doNothing().when(this.userRepositoryMock).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("FindAll returns of user list when succesful")
    public void findAll_ReturnsUserList_WhenSuccessful() {
        String expectedName = UserCreator.createUserToBeSaved().getName();

        List<UserModel> userList = userService.findAllUserModel();

        Assertions.assertThat(userList).isNotEmpty().isNotNull().hasSize(1);

        Assertions.assertThat(userList.get(0).getName()).isEqualTo(expectedName);
    }
    
    @Test
    @DisplayName("findByIdUserModel return user when succesful")
    public void findByIdUserModel_ReturnUser_WhenSuccessful() {
        UserModel expectedId = UserCreator.createUserToBeSaved();

        UserModel userById = userService.findByIdUserModel(expectedId.getId());

        Assertions.assertThat(userById).isNotNull();

        Assertions.assertThat(expectedId.getId()).isEqualTo(userById.getId());
    }

    @Test
    @DisplayName("findByIdUserModel throw Exception when user is not found")
    public void findByIdUserModel_ThrowException_WhenUserIsNotFound() {
        BDDMockito.when(this.userRepositoryMock.findById(ArgumentMatchers.anyInt()))
            .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
            .isThrownBy(() -> userService.findByIdUserModel(1));
    }

    @Test
    @DisplayName("Save return UserModel WhenSuccessful")
    public void save_ReturnUserModel_WhenSuccessful() {
        UserModel userModel = userService.saveUser(UserCreator.createUserDtoToBeSaved());

        Assertions.assertThat(userModel).isNotNull().isEqualTo(UserCreator.createUserToBeSaved());
    }

    @Test
    @DisplayName("Delete UserModel WhenSuccessful")
    public void delete_UserModeol_WhenSuccessful() {
        UserModel userModel = userService.saveUser(UserCreator.createUserDtoToBeSaved());
        userService.deleteUserModel(userModel.getId());
        
        Assertions.assertThatCode(() -> this.userService.deleteUserModel(1))
            .doesNotThrowAnyException();
    }
    
}
