package com.excelra.mvc.model.search.Structure;

import java.io.Serializable;
import java.math.BigDecimal;

public class PropertyValues implements Serializable {

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }
}
