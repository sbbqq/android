package com.example.tvtest.adpter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqq on 18-8-14.
 */

public class AttrValue {
    private List<String> standNames;
    private List<String> standValues;
    private List<String> clors;

    public List<String> getStandNames() {
        return standNames;
    }

    public void setStandNames(List<String> standNames) {
        this.standNames = standNames;
    }

    public List<String> getStandValues() {
        return standValues;
    }

    public void setStandValues(List<String> standValues) {
        this.standValues = standValues;
    }

    public List<String> getClors() {
        return clors;
    }

    public void setClors(List<String> clors) {
        this.clors = clors;
    }

    public AttrValue(List<String> standNames, List<String> standValues, List<String> clors) {
        this.standNames = standNames;
        this.standValues = standValues;
        this.clors = clors;
    }
}
