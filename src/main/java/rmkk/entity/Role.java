package rmkk.entity;

public class Role {
    private Integer id;

    private Integer rolegrade;

    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRolegrade() {
        return rolegrade;
    }

    public void setRolegrade(Integer rolegrade) {
        this.rolegrade = rolegrade;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
}