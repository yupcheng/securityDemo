package com.yu.spring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体类
 * 与菜单是多对多关系
 * Created by Administrator on 2017/6/10.
 */
@Entity
@Table(name="ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="NAME",unique=true, nullable=false )//不能重复不能为空
    private String name;
    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
    @Column(name="CREATETIME")
    private Date createTime;
    @Column(name="CREATOR")
    private String creator;
    @Column(name="STATE")
    private int state;
    @Column(name="CODE")
    private String code;
    @ManyToMany
    @JoinTable(name = "MENU_ROLE",
            joinColumns = { @JoinColumn(name = "ROLE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
    private Set<Menu> menus = new HashSet<>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                ", menus=" + menus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (getId() != role.getId()) return false;
        return getType() != null ? getType().equals(role.getType()) : role.getType() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
