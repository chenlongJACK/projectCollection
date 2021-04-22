package com.cl.bean.util;

import java.util.Map;

/**
 * @Description
 * @auther chenlong
 * @date 2021/4/2215:51
 */
public class FieldCollection {

    private Map<String,String> valuefields;

    private Map<String,String> conditionsFields;

    private String object;

    public FieldCollection() {
    }

    public FieldCollection(Map<String, String> valuefields, Map<String, String> conditionsFields, String object) {
        this.valuefields = valuefields;
        this.conditionsFields = conditionsFields;
        this.object = object;
    }

    public Map<String, String> getValuefields() {
        return valuefields;
    }

    public void setValuefields(Map<String, String> valuefields) {
        this.valuefields = valuefields;
    }

    public Map<String, String> getConditionsFields() {
        return conditionsFields;
    }

    public void setConditionsFields(Map<String, String> conditionsFields) {
        this.conditionsFields = conditionsFields;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
