package br.com.maiconcardoso.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.maiconcardoso.unittest.model.UserModel;
import br.com.maiconcardoso.unittest.repositories.UserRepository;
import br.com.maiconcardoso.unittest.services.UserService;
import br.com.maiconcardoso.unittest.utils.UserCreator;

@ExtendWith(SpringExtension.class) // Usado para testar a aplicação sem executa-la
public class UserServiceTest {
    
    @InjectMocks // Usado para injetar a dependência da classe que será testada.
    private UserService userService;

    @Mock // Usada para injetar a dependência que está sendo usada na classe testada.
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void setUp() {

        PageImpl<UserModel> userPage = new PageImpl<>(List.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(userPage);

        BDDMockito.when(this.userRepositoryMock.findAll())
            .thenReturn(List.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userRepositoryMock.findById(ArgumentMatchers.anyInt()))
            .thenReturn(Optional.of(UserCreator.createUserToBeSaved()));

        BDDMockito.when(this.userRepositoryMock.save(ArgumentMatchers.any()))
            .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.doNothing().when(this.userRepositoryMock).delete(ArgumentMatchers.any());

    }
}
