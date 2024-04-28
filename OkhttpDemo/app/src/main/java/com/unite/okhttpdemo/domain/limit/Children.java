package com.unite.okhttpdemo.domain.limit;

import java.util.List;

/**
 *
 */
public class Children {

    private Integer id;
    private String  name;
    private Boolean IsHide;
    private Boolean IsButton;
    private String path;
    private String iconCls;
    private Meta meta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
