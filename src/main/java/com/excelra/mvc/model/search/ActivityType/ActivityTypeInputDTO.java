package com.excelra.mvc.model.search.ActivityType;

import java.io.Serializable;

public class ActivityTypeInputDTO implements Serializable {

    private String activityType;

    private String activityUom;

    private String prefix;

    private Long minValue;

    private Long maxValue;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityUom() {
        return activityUom;
    }

    public void setActivityUom(String activityUom) {
        this.activityUom = activityUom;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }
}
