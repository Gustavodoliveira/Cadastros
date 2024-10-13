package gustavo.cadastro.Dtos.Users;

import gustavo.cadastro.models.Roles;

public record RegisterUserDto(String email, String userName, String password, Roles role) {

}
