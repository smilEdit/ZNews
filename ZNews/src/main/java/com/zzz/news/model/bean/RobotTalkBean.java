package com.zzz.news.model.bean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 14:24
 */

public class RobotTalkBean {
    private int icon;
    private String content;
    private String createDate;
    private boolean isComMsg;

    public RobotTalkBean(String createDate, boolean isComMsg, String content, int icon) {
        this.createDate = createDate;
        this.isComMsg = isComMsg;
        this.content = content;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isComMsg() {
        return isComMsg;
    }

    public void setComMsg(boolean comMsg) {
        isComMsg = comMsg;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
