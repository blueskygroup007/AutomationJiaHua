package com.bluesky.sqlitetest;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 */
public class Device extends LitePalSupport {

    @Column(nullable = false)
    public int serial;
    public String tag;
    public String affect;
    public String parameter;
    public String name;
    public String range;
    public String standard;
    public String mode;
    public String pipe;
    public String type;
    public String count;//这个可以为int
    public String install;
    public String factory;
    public String remark;
    public String brand;
    public String date;

    public Device(int serial, String tag, String affect, String parameter, String name, String range, String standard, String mode, String pipe, String type, String count, String install, String factory, String remark, String brand, String date) {
        this.serial = serial;
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
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
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
