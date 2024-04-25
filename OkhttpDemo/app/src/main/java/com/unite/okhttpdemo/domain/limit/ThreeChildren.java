package com.unite.okhttpdemo.domain.limit;

/**
 *
 */
public class ThreeChildren {

    private Integer id;
    private Integer pid;
    private Integer order;
    private String name;
    private Boolean IsHide;
    private Boolean IsButton;
    private String path;
    private Meta meta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHide() {
        return IsHide;
    }

    public void setHide(Boolean hide) {
        IsHide = hide;
    }

    public Boolean getButton() {
        return IsButton;
    }

    public void setButton(Boolean button) {
        IsButton = button;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
