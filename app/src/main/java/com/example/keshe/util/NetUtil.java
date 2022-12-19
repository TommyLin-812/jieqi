package com.example.keshe.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {

    public static final String URL_WEATHER_WITH_FUTURE = "https://yiketianqi.com/free/day?appid=48453555&appsecret=bPCgbZl2";


    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;//HttpURLConnection是Java的标准类，可用于向指定网站发送GET请求、POST请求。
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 连接网络
        try {
            URL urL = new URL(urlStr);//url是统一资源定位符，对可以从互联网上得到的资源的位置和访问方法的一种简洁的表示，是互联网上标准资源的地址。互联网上的每个文件都有一个唯一的URL，它包含的信息指出文件的位置以及浏览器应该怎么处理它。
            connection = (HttpURLConnection) urL.openConnection();
            connection.setRequestMethod("GET");////可以省略？
            connection.setConnectTimeout(5000);//// 5秒 连接主机的超时时间（单位：毫秒）
            connection.setReadTimeout(5000); //// 5秒 从主机读取数据的超时时间（单位：毫秒）

            // 从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();////得到从服务器端发回的数据
            inputStreamReader = new InputStreamReader(inputStream);////是字节流通向字符流的桥梁：他使用指定的charset读取字节并将其解码为字符。不指定默认使用UTF-8
            //// 字符流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();////可变字符串
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);////追加字符串
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String getWeatherOfCity() {
        // 拼接出获取天气数据的URL
        // https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9
        String weatherUrl = URL_WEATHER_WITH_FUTURE;
        Log.d("fan", "----weatherUrl----" + weatherUrl);
        String weatherResult = doGet(weatherUrl);
        Log.d("fan", "----weatherResult----" + weatherResult);
        return weatherResult;
    }

}
