package de.hybris.electronics.dto.pdp;

import java.util.List;

public class Data {

    private String id;

    private String avatar;

    private String nickname;

    private String addTime;

    private String content;

    private String adminContent;

    private List<String> picList;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddTime() {
        return this.addTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }

    public String getAdminContent() {
        return this.adminContent;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public List<String> getPicList() {
        return this.picList;
    }
}
