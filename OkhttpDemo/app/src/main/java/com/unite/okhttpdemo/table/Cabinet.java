package com.unite.okhttpdemo.table;

import java.util.List;

/**
 *
 */
public class Cabinet {

    private String mc;
    private int sum;
    private int num;
    private List<Drawer> drawers;
    private int xh;

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Drawer> getDrawers() {
        return drawers;
    }

    public void setDrawers(List<Drawer> drawers) {
        this.drawers = drawers;
    }
}
