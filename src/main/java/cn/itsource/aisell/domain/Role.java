package cn.itsource.aisell.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2018-11-28 11:25:19
 */
@Entity
@Table(name="role")
public class        Role extends BaseDomain {
   

    private String name;
    
    private String sn;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id"))
    private List<Permission> permissions = new ArrayList<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}