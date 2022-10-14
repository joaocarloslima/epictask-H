package br.com.fiap.epictaskapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.fiap.epictaskapi.dto.UserDto;

@Entity
@Table(name = "TB_USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String githubuser;

    @Column(unique = true)
    @Email
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Size(min = 8, max = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User name(String name) {
        Assert.notNull(name, "name is required");
        this.name = name;
        return this;
    }

    public User email(String email) {
        Assert.notNull(email, "email is required");
        this.email = email;
        return this;
    }

    public User password(String password) {
        Assert.notNull(password, "password is required");
        this.password = password;
        return this;
    }

    public User githubuser(String githubuser) {
        Assert.notNull(githubuser, "githubuser is required");
        this.githubuser = githubuser;
        return this;
    }

    public User withRole(Role role){
        Assert.notNull(role, "role is required");
        this.roles.add(role);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto toDto() {
        return new UserDto(id, name, email);
    }

    public String getGithubuser() {
        return githubuser;
    }

    public void setGithubuser(String githubuser) {
        this.githubuser = githubuser;
    }

    public String getAvatar(){
        return "https://github.com/" + githubuser + ".png";
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", githubuser=" + githubuser + ", email=" + email + ", password="
                + password + ", roles=" + roles + "]";
    }

    

}
