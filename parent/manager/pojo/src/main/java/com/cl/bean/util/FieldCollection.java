package com.cl.bean.util;

import java.util.Map;

/**
 * @Description
 * @auther chenlong
 * @date 2021/4/2215:51
 */
public class FieldCollection {
    /**属性名称与中文映射集合*/
    private Map<String,String> valueFields;
    /**条件属性名称与中文映射集合*/
    private Map<String,String> conditionsFields;
    /**模块名称*/
    private String module;

    public FieldCollection() {
    }

    public FieldCollection(Map<String, String> valueFields, Map<String, String> conditionsFields, String module) {
        this.valueFields = valueFields;
        this.conditionsFields = conditionsFields;
        this.module = module;
    }

    public Map<String, String> getValueFields() {
        return valueFields;
    }

    public void setValueFields(Map<String, String> valueFields) {
        this.valueFields = valueFields;
    }

    public Map<String, String> getConditionsFields() {
        return conditionsFields;
    }

    public void setConditionsFields(Map<String, String> conditionsFields) {
        this.conditionsFields = conditionsFields;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
