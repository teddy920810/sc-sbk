package com.heeexy.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class WxUser extends DomainObject{

    private String avatarUrl;

    private String city;

    private String country;

    private String gender;

    private String language;

    private String nickName;

    private String province;

    private String openid;

    @JsonIgnoreProperties("wxUser")
    @OneToMany(mappedBy = "wxUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningData> runningDatas;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<RunGroup> groups;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<RunningData> getRunningDatas() {
        return runningDatas;
    }

    public void setRunningDatas(List<RunningData> runningDatas) {
        this.runningDatas = runningDatas;
    }

    public List<RunGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<RunGroup> groups) {
        this.groups = groups;
    }

    public WxUser() {
    }

    public WxUser(String avatarUrl, String city, String country, String gender, String language, String nickName, String province, String openid) {
        this.avatarUrl = avatarUrl;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.language = language;
        this.nickName = nickName;
        this.province = province;
        this.openid = openid;
    }

    public WxUser(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", language='" + language + '\'' +
                ", nickName='" + nickName + '\'' +
                ", province='" + province + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
