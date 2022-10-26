package br.com.maiconcardoso.unittest.dtos;

import br.com.maiconcardoso.unittest.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;

    public UserDto(UserModel userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
    }




}
