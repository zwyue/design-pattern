package com.zwyue.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 权限表
 * @author yangli
 * @date 2018/12/25
 */
public class Power implements Serializable {
    //主键id
    private Integer id;
    //权限规则
    private String rules;
    //权限名称
    private String powername;
    //所属分类
    private String category;
    //创建时间
    private String crttime;

    /**
     * 叶子节点（是否有子节点）
     *
     * @date 2018/12/27 16:15
     */
    private Boolean isLeaf ;

    /**
     * 权限属性（0：菜单、1：按钮）
     *
     * @date 2018/12/27 14:46
     */
    private String types ;

    //图标
    private String iconcls ;

    /**
     * 子节点
     */
    private List<Power> childPowers ;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    public String getPowername() {
        return powername;
    }

    public void setPowername(String powername) {
        this.powername = powername == null ? null : powername.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public List<Power> getChildPowers() {
        return childPowers;
    }

    public void setChildPowers(List<Power> childPowers) {
        this.childPowers = childPowers;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }
}