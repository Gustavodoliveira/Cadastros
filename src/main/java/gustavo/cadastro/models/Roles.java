package gustavo.cadastro.models;

public enum Roles {
  ADMIN("admin"),
  USER("user");

  private String role;

  Roles(String role) {
    this.role = role;
  }
}
