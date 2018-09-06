package com.medical.domain;

import java.util.Date;

public class Patient extends BaseObject {

    private Integer id;

    private String uuid;

    private String username;

    private String sex;

    private Integer age;

    private Date birthday;

    private String phonenum;

    private String cid;

    private String wechatnum;

    private String origin; // 民族

    private String profession; // 职业

    private String address;

    private Integer height;

    private Integer weight;

    private String flood; // 血型

    private String menses; // 月经史

    private String person; // 个人史

    private String birthis; // 生育史

    private String family; // 家族史

    private String allergy; // 过敏史

    private Date createtime; // 建立时间

    private String state; // 病人状态

    private String lastcase; // 最新一次病症

    public String getLastcase() {
        return lastcase;
    }

    public void setLastcase(String lastcase) {
        this.lastcase = lastcase;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getWechatnum() {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum) {
        this.wechatnum = wechatnum;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getFlood() {
        return flood;
    }

    public void setFlood(String flood) {
        this.flood = flood;
    }

    public String getMenses() {
        return menses;
    }

    public void setMenses(String menses) {
        this.menses = menses;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getBirthis() {
        return birthis;
    }

    public void setBirthis(String birthis) {
        this.birthis = birthis;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
