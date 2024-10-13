package gustavo.cadastro.Dtos;

import gustavo.cadastro.models.Roles;

public record UserDto(String email, String userName, String password, Roles role) {

}
