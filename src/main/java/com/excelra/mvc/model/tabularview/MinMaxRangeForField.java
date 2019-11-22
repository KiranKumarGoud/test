package com.excelra.mvc.model.tabularview;

import java.io.Serializable;

public class MinMaxRangeForField implements Serializable {

    private String operator;

    private double minValue;

    private double maxValue;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

}
