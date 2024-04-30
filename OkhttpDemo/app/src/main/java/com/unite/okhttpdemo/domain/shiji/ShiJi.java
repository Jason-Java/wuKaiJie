package com.unite.okhttpdemo.domain.shiji;

/**
 *
 */
public class ShiJi {

    private String CommonName;
    private String Label;
    private String CAS;
    private String Alias;//别名
    private String Model;//规格
    private String Consistency;//纯度
    private String Classification;//固体

    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getCAS() {
        return CAS;
    }

    public void setCAS(String CAS) {
        this.CAS = CAS;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getConsistency() {
        return Consistency;
    }

    public void setConsistency(String consistency) {
        Consistency = consistency;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }
}
