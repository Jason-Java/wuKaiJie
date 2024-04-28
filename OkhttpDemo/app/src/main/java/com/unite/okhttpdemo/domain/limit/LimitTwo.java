package com.unite.okhttpdemo.domain.limit;

import java.util.List;

/**
 *
 */
public class LimitTwo extends Children{

    
    private List<LimitThree> children;



    public List<LimitThree> getChildren() {
        return children;
    }


    public void setChildren(List<LimitThree> children) {
        this.children = children;
    }
}
