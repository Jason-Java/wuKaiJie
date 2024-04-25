package com.unite.okhttpdemo.domain.limit;

import com.unite.okhttpdemo.domain.response.DetailResponse;

import java.util.List;

/**
 *
 */
public class OneChildren{

    private List<TwoChildren> children;

    public List<TwoChildren> getChildren() {
        return children;
    }

    public void setChildren(List<TwoChildren> children) {
        this.children = children;
    }
}
