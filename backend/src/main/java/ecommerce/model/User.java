package ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ecommerce.security.model.Role;

@Entity
@Table(name = "users",
	   uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"), 
		@UniqueConstraint(columnNames =  "email")
	})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	@NotBlank
	@Size(max = 30)
	private String username;
	
	@Column(name = "first_name")
	@NotBlank
	@Size(max = 30)
	private String firstName;

	@Column(name = "last_name")
	@NotBlank
	@Size(max = 30)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
		this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

	public User() {
	}
	 
	
	public Integer getId() {
	        return id;
	    }

	public void setId(Integer id) {
	        this.id = id;
	    }
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
	        return email;
	    }

	public void setEmail(String email) {
	        this.email = email;
	    }

	public String getPassword() {
	        return password;
	    }

	public void setPassword(String passwoprd) {
	        this.password = passwoprd;
	    }
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
