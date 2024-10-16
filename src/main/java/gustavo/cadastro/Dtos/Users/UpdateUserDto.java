package gustavo.cadastro.Dtos.Users;

import gustavo.cadastro.models.Department;

public record UpdateUserDto(String userName, String email, String newPassword, Department department) {

}
