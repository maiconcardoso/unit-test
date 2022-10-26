package br.com.maiconcardoso.unittest.utils;

import br.com.maiconcardoso.unittest.dtos.UserDto;
import br.com.maiconcardoso.unittest.model.UserModel;

public class UserCreator {
    
    public static UserModel createUserToBeSaved() {
        UserModel user = new UserModel(1 ,"Maicon", "maicon@hotmail.com", "123");
        return user;
    }

    public static UserDto createUserDtoToBeSaved() {
        UserModel user = new UserModel(1 ,"Maicon", "maicon@hotmail.com", "123");
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    public static UserModel userModelUpdate() {
        UserModel userToBeUpdated = createUserToBeSaved();
        userToBeUpdated.setEmail("maiconcardoso@hotmail.com");
        userToBeUpdated.setName("Maicon Cardoso");
        userToBeUpdated.setPassword("321");
        return userToBeUpdated;
    }
}
