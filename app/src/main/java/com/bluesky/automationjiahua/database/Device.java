package com.bluesky.automationjiahua.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 */
@Entity
public class Device implements Serializable {


    /*    @ColumnInfo(name = "serial")
        public int serial;*/
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tag")
    public String tag;
    @ColumnInfo(name = "affect")
    public String affect;
    @ColumnInfo(name = "parameter")
    public String parameter;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "range")
    public String range;
    @ColumnInfo(name = "standard")
    public String standard;
    @ColumnInfo(name = "mode")
    public String mode;
    @ColumnInfo(name = "pipe")
    public String pipe;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "count")
    public String count;//这个可以为int
    @ColumnInfo(name = "install")
    public String install;
    @ColumnInfo(name = "factory")
    public String factory;
    @ColumnInfo(name = "remark")
    public String remark;
    @ColumnInfo(name = "brand")
    public String brand;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "domain")
    public String domain;

    public Device(String tag, String affect, String parameter, String name, String range, String standard, String mode, String pipe, String type, String count, String install, String factory, String remark, String brand, String date, String domain) {
        this.tag = tag;
        this.affect = affect;
        this.parameter = parameter;
        this.name = name;
        this.range = range;
        this.standard = standard;
        this.mode = mode;
        this.pipe = pipe;
        this.type = type;
        this.count = count;
        this.install = install;
        this.factory = factory;
        this.remark = remark;
        this.brand = brand;
        this.date = date;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPipe() {
        return pipe;
    }

    public void setPipe(String pipe) {
        this.pipe = pipe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
