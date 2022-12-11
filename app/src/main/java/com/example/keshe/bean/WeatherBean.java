package com.example.keshe.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*{
        "nums":226, //今日实时请求次数
        "cityid":"101120101", //城市ID
        "city":"济南",
        "date":"2022-05-05",
        "week":"星期四",
        "update_time":"22:38", //更新时间
        "wea":"多云", //天气情况
        "wea_img":"yun", //天气标识
        "tem":"25", //实况温度
        "tem_day":"30", //白天温度(高温)
        "tem_night":"23", //夜间温度(低温)
        "win":"南风", //风向
        "win_speed":"3级", //风力
        "win_meter":"19km\/h", //风速
        "air":"53", //空气质量
        "pressure":"987", //气压
        "humidity":"27%" //湿度
}*/

public class WeatherBean implements Serializable {

    @SerializedName("nums")
    private String nums;

    @SerializedName("cityid")
    private String cityId;

    @SerializedName("city")
    private String city;

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
    private String win;

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

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
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

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
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
        return "WeatherBean{" +
                "nums='" + nums + '\'' +
                ", cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", update_time='" + update_time + '\'' +
                ", wea='" + wea + '\'' +
                ", weaImg='" + weaImg + '\'' +
                ", tem='" + tem + '\'' +
                ", tem_day='" + tem_day + '\'' +
                ", tem_night='" + tem_night + '\'' +
                ", win=" + win + '\'' +
                ", win_speed='" + winSpeed + '\'' +
                ", win_meter'" + win_meter + '\'' +
                ", air='" + air + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }


}
