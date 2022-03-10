package com.github.rozumek29.plantseekerpanel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.rozumek29.plantseekerpanel.data.AbstractEntity;
import com.github.rozumek29.plantseekerpanel.enums.Role;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class User extends AbstractEntity {

    private String username;
    private String name;
    @JsonIgnore
    private String hashedPassword;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
