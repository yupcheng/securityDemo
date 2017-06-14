package com.yu.spring.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 资源
 * Created by Administrator on 2017/6/12.
 */
@Entity
@Table(name="PRIVILEGE")
public class Privilege {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //一个资源对应一个菜单，一个菜单对应多个资源（功能）
    //一个角色对应多个资源，一个资源对应多个角色
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId",updatable = false,insertable = false)
    private Menu menu;
  /*  @Column(name="MENUID", nullable=false)*/
    private Integer menuId;
    @Column(name="PRIVILEGENAME", nullable=false)
    private String privilegeName;

    @Column(name="URL", nullable=false)
    private String url;
    @Column(name="STATUS", nullable=false)
    private Integer status;
    @Column(name="ISDELETED", nullable=false)
    private Integer isDeleted;
    @Column(name="CREATETIME", nullable=false)
    private Date createTime;
    @Column(name="CREATOR", nullable=false)
    private String creator;
    @Column(name="MARK", nullable=false)
    private String mark;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", privilegeName='" + privilegeName + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                ", cteateTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilege)) return false;

        Privilege privilege = (Privilege) o;

        if (getId() != null ? !getId().equals(privilege.getId()) : privilege.getId() != null) return false;
        return getMark() != null ? getMark().equals(privilege.getMark()) : privilege.getMark() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getMark() != null ? getMark().hashCode() : 0);
        return result;
    }
}
