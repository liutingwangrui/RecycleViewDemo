package com.recycleviewdemo.bean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class ShowItemBean {
    public static final int PARENT_ITEM = 0;
    public static final int CHILD_ITEM = 1;

    private int type;//显示类型
    private boolean isExpand;//是否展开

    private String ID;
    private ShowItemBean showItemBean;
    private String parentContent;
    private String childContent;




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public ShowItemBean getShowItemBean() {
        return showItemBean;
    }

    public void setShowItemBean(ShowItemBean showItemBean) {
        this.showItemBean = showItemBean;
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    public String getChildContent() {
        return childContent;
    }

    public void setChildContent(String childContent) {
        this.childContent = childContent;
    }
}
