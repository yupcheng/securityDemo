package com.yu.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户菜单实体类
 * 与权限是一个对多关系
 * Created by Administrator on 2017/6/10.
 */
@Entity
@Table(name="MENU")
public class Menu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="TITLE", nullable=false)
    private String title;
    @Column(name="MAKER", nullable=false)
    private String maker;//代码
    @Column(name="LEVEL", nullable=false)
    private int level;//菜单级别
    @Column(name="PARENTMENUID", nullable=false)
    private int parentMenuId;
    @Column(name="URL", nullable=false)
    private String url;
    @Column(name="STATUS", nullable=false)
    private Integer status;
    @Column(name="SORT", nullable=false)
    private int sort;//排序
    @Column(name="CREATETIME", nullable=false)
    private Date createTime;
    @JsonIgnore
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<Privilege> privileges;
    @Transient//忽略字段映射
    private Set<Menu> children ;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(int parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return status;
    }

    public void setState(Integer state) {
        this.status = state;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", maker='" + maker + '\'' +
                ", level=" + level +
                ", parentMenuId=" + parentMenuId +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", privileges=" + privileges +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (getId() != menu.getId()) return false;
        return getMaker().equals(menu.getMaker());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getMaker().hashCode();
        return result;
    }
}
