package plantShop.Entity.dto.account;

public class JwtAuthenticationResponse {
  String token;

  String name;

  String email;

  String role;

  public JwtAuthenticationResponse() {
  }

  public JwtAuthenticationResponse(String token, String name, String email, String role) {
    this.token = token;
    this.name = name;
    this.email = email;
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }
}
