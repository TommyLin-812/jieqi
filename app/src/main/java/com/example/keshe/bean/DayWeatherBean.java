package com.example.keshe.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class DayWeatherBean implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("week")
    private String week;

    @SerializedName("update_time")
    private String update_time;

    @SerializedName("wea")
    private String wea;

    @SerializedName("wea_img")
    private String weaImg;

    @SerializedName("tem")
    private String tem;

    @SerializedName("tem_day")
    private String tem_day;

    @SerializedName("tem_night")
    private String tem_night;

    @SerializedName("win")
    private String[] win;

    @SerializedName("win_speed")
    private String winSpeed;

    @SerializedName("win_meter")
    private String win_meter;

    @SerializedName("air")
    private String air;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("humidity")
    private String humidity;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem_day() {
        return tem_day;
    }

    public void setTem_day(String tem_day) {
        this.tem_day = tem_day;
    }

    public String getTem_night() {
        return tem_night;
    }

    public void setTem_night(String tem_night) {
        this.tem_night = tem_night;
    }

    public String[] getWin() {
        return win;
    }

    public void setWin(String[] win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", wea='" + wea + '\'' +
                ", weaImg='" + weaImg + '\'' +
                ", tem='" + tem + '\'' +
                ", tem1='" + tem_day + '\'' +
                ", tem2='" + tem_night + '\'' +
                ", win=" + Arrays.toString(win) +
                ", winSpeed='" + winSpeed + '\'' +
                ", air='" + air + '\'' +
                ", airLevel='" + pressure + '\'' +
                ", airTips='" + humidity +
                '}';
    }
}
