package com.example.keshe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.bean.WeatherBean;
import com.example.keshe.util.NetUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private Handler handler;  //异步消息处理器
    private TextView weatherBean;
    private TextView riqi;
    private TextView jieqi;
    private TextView lunar;
    private TextView yiji;
    private LinearLayout background;
    private SimpleDateFormat sdf;
    private SimpleDateFormat osdf;
    private boolean runing;
    private TextView Tv1;
    private Button Btn1;
    private TextView tvWeather, tvTemLowHigh, tvaddress;
    private ImageView ivWeather;
    private final Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;
                Log.d("fan", "--主线程收到了天气数据-weather---" + weather);
                if (TextUtils.isEmpty(weather)) {
                    Toast.makeText(MainActivity.this, "天气数据为空！", Toast.LENGTH_LONG).show();
                    return;
                }
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                if (weatherBean != null) {
                    Log.d("fan", "--解析后的数据-weather---" + weatherBean);
                }
                updateUiOfWeather(weatherBean);
            }
        }
    };

    @SuppressLint("SetTextI18n")
    private void updateUiOfWeather(WeatherBean weather) {
        if (weatherBean == null) {
            return;
        }

        tvWeather.setText(weather.getWea());
        tvTemLowHigh.setText(weather.getTem_night() + "~" + weather.getTem_day());
        ivWeather.setImageResource(getImgResOfWeather(weather.getWeaImg()));
    }

    private int getImgResOfWeather(String weaStr) {
        int result;
        switch (weaStr) {
            case "qing":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "yin":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "yu":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "yun":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "bingbao":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "wu":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "shachen":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "lei":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "xue":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }
        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.home);
        ImageView iv_to_article = this.findViewById(R.id.iv_to_article);
        ImageView iv_to_home = findViewById(R.id.iv_to_home);
        iv_to_home.setImageResource(R.drawable.home_blue);
        iv_to_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArticleList.class);
                startActivity(intent);
            }
        });

        weatherBean = findViewById(R.id.tv_Time);
        riqi = findViewById(R.id.tv_riqi);
        jieqi = findViewById(R.id.tv_jieqi);
        lunar = findViewById(R.id.tv_lunar);
        yiji = findViewById(R.id.tv_yiji);
        background = findViewById(R.id.background);
        sdf = new SimpleDateFormat("HH");////h和H不一样
        osdf = new SimpleDateFormat("HH");
        weatherBean.setText(sdf.format(new Date()));
        riqi.setText(LunarCalender.getriqi());
        jieqi.setText(LunarCalender.getFestival());
        lunar.setText(LunarCalender.getDayLunar());
        yiji.setText(LunarCalender.getyiji());
        Calendar calendar = Calendar.getInstance();
        handler = new Handler() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    switch (sdf.format(new Date())) {
                        case "23":
                        case "00":
                            weatherBean.setText("子时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        case "01":
                        case "02":
                            weatherBean.setText("丑时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        case "03":
                        case "04":
                            weatherBean.setText("寅时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        case "05":
                            weatherBean.setText("卯时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        case "06":
                            weatherBean.setText("卯时");
                            background.setBackground(getDrawable(R.drawable.morning));
                            break;
                        case "07":
                        case "08":
                            weatherBean.setText("辰时");
                            background.setBackground(getDrawable(R.drawable.morning));
                            break;
                        case "09":
                        case "10":
                            weatherBean.setText("巳时");
                            background.setBackground(getDrawable(R.drawable.morning));
                            break;
                        case "11":
                            weatherBean.setText("午时");
                            background.setBackground(getDrawable(R.drawable.morning));
                            break;
                        case "12":
                            weatherBean.setText("午时");
                            background.setBackground(getDrawable(R.drawable.noon));
                            break;
                        case "13":
                        case "14":
                            weatherBean.setText("未时");
                            background.setBackground(getDrawable(R.drawable.noon));
                            break;
                        case "15":
                        case "16":
                            weatherBean.setText("申时");
                            background.setBackground(getDrawable(R.drawable.noon));
                            break;
                        case "17":
                        case "18":
                            weatherBean.setText("酉时");
                            background.setBackground(getDrawable(R.drawable.noon));
                            break;
                        case "19":
                        case "20":
                            weatherBean.setText("戌时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        case "21":
                        case "22":
                            weatherBean.setText("亥时");
                            background.setBackground(getDrawable(R.drawable.night));
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //让线程  发送消息
                    handler.sendEmptyMessage(1);
                    //让线程  睡眠500毫秒
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //启动线程
        thread.start();

        initView();
    }

    private void initView() {
        getWeatherOfCity();
        tvWeather = findViewById(R.id.tv_weather);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        ivWeather = findViewById(R.id.iv_weather);
    }

    private void getWeatherOfCity() {
        // 开启子线程，请求网络
        new Thread(() -> {
            // 请求网络
            String weatherOfCity = NetUtil.getWeatherOfCity("浦东新区");
            // 使用handler将数据传递给主线程
            Message message = Message.obtain();
            message.what = 0;
            message.obj = weatherOfCity;
            mHandler.sendMessage(message);
        }).start();
    }
}
