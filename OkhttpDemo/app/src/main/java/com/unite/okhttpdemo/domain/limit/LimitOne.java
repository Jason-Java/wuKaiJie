package com.unite.okhttpdemo.domain.limit;

import com.unite.okhttpdemo.domain.response.BaseResponse;

import java.util.List;

/**
 *
 */
public class LimitOne{

    private List<LimitTwo> children;

    public List<LimitTwo> getChildren() {
        return children;
    }

    public void setChildren(List<LimitTwo> children) {
        this.children = children;
    }
}
