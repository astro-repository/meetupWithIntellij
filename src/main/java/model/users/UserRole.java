package model.users;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserRole {

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue
    private long id;

    /**
     * Un rôle peut posseder une serie de permission
     */
    // ManyToOne
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Permission> permissions = new ArrayList<Permission>();

    public UserRole(){}

    public UserRole(Permission permission, Role role){
        this.permissions.add(permission);
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public List<Permission> getPermissions() {
        return new ArrayList<Permission>(permissions);
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
