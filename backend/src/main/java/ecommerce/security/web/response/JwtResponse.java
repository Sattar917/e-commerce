package ecommerce.security.web.response;

import java.util.List;

public class JwtResponse {
  
  private String token;
  
  private String type = "Bearer";
  
  private Integer id;
  
  private String username;
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  private List<String> roles;

  public JwtResponse(String accessToken, Integer id, String username, String firstName, String lastName, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getFirstName() {
	    return firstName;
	  }

  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }
  
  public String getLastName() {
	    return lastName;
	  }

  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}