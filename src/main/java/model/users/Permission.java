package model.users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
public class Permission {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Une permission peut être attribué à plusieurs rôle
     */
    // OneToMany
    @OneToMany
    private List<UserRole> userRoles = new ArrayList<UserRole>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public Permission(){}

    public Permission(UserRole userRole, Role role) {
        this.userRoles.add(userRole);
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public List<UserRole> getUserRoles() {
        return new ArrayList<UserRole>(userRoles);
    }

    public void setUserRoles(UserRole userRole) {

    }

    public void addUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
