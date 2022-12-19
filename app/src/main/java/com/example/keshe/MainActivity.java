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
import android.view.WindowManager;
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
    private TextView time;
    private TextView riqi;
    private TextView jieqi;
    private TextView lunar;
    private TextView yiji;
    private LinearLayout background;
    private SimpleDateFormat sdf;////一个用于以区域设置敏感的方式格式化和解析日期的具体类
    private TextView tvAddress;
    private TextView tvWeather;
    private TextView tvTemLowHigh;
    private ImageView ivWeather;
    private android.app.NotificationManager NotificationManager;
    private final Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;
                Log.d("fan", "--主线程收到了天气数据-weather---" + weather);
                if (TextUtils.isEmpty(weather)) {////如果传回的数据为空
                    Toast.makeText(MainActivity.this, "天气数据为空！", Toast.LENGTH_LONG).show();
                    return;
                }
                Gson gson = new Gson();////gson是一种组件库，可以把java对象数据转换成json数据格式。
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);////json是一种数据格式，便于数据传输、存储、交换。
                if (weatherBean != null) {
                    Log.d("fan", "--解析后的数据-weather---" + weatherBean);
                }
                updateUiOfWeather(weatherBean);
            }
        }
    };

    @SuppressLint("SetTextI18n")
    private void updateUiOfWeather(WeatherBean weather) {
        if (weather == null) {
            return;
        }
        tvAddress.setText(weather.getCity());
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

    @SuppressLint({"HandlerLeak", "SimpleDateFormat"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        Calendar calendar = Calendar.getInstance();
        loaddate();
        initView();
    }

    private void initView() {
        getWeatherOfCity();
        tvAddress=findViewById(R.id.tv_address);
        tvWeather = findViewById(R.id.tv_weather);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        ivWeather = findViewById(R.id.iv_weather);
    }

    private void getWeatherOfCity() {
        // 开启子线程，请求网络
        new Thread(() -> {
            // 请求网络
            String weatherOfCity = NetUtil.getWeatherOfCity();
            // 使用handler将数据传递给主线程
            Message message = Message.obtain();////获取Message实例
            message.what = 0;
            message.obj = weatherOfCity;
            mHandler.sendMessage(message);
        }).start();
    }
    @SuppressLint("HandlerLeak")
    private void loaddate(){
        time = findViewById(R.id.tv_Time);
        riqi = findViewById(R.id.tv_riqi);
        jieqi = findViewById(R.id.tv_jieqi);
        lunar = findViewById(R.id.tv_lunar);
        yiji = findViewById(R.id.tv_yiji);
        background = findViewById(R.id.background);
        sdf = new SimpleDateFormat("HH");////获取当前的时间
        //weatherBean.setText(sdf.format(new Date()));////获取时间并转换为字符串
        riqi.setText(LunarCalender.getriqi());
        jieqi.setText(LunarCalender.getFestival());
        lunar.setText(LunarCalender.getDayLunar());
        yiji.setText(LunarCalender.getyiji());
        ////将工作线程中需更新UI的操作信息传递到UI主线程，创建一个处理器接收子线程发来的消息
        handler = new Handler() {
            @SuppressLint({"UseCompatLoadingForDrawables", "HandlerLeak"})
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    if(jieqibackground()==false)loadtimeandbackground();
                    else loadtime();
                }
            }
        };
        //new Runnable（）是一个实现接口Runnable的类的对象，后面的run方法是该类里实现的方法
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //让线程 发送消息
                    handler.sendEmptyMessage(1);
                    //让线程 睡眠500毫秒
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();////打印出异常
                    }
                }
            }
        });
        //启动线程
        thread.start();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean jieqibackground(){
        boolean result=true;
        switch (LunarCalender.getFestival()){
            case "立春":
                background.setBackground(getDrawable(R.drawable.jqlichun));
                break;
            case "雨水":
                background.setBackground(getDrawable(R.drawable.jqyushui));
                break;
            case "惊蛰":
                background.setBackground(getDrawable(R.drawable.jqjingzhe));
                break;
            case "春分":
                background.setBackground(getDrawable(R.drawable.jqchunfen));
                break;
            case "清明":
                background.setBackground(getDrawable(R.drawable.jqqingming));
                break;
            case "谷雨":
                background.setBackground(getDrawable(R.drawable.jqguyu));
                break;
            case "立夏":
                background.setBackground(getDrawable(R.drawable.jqlixia));
                break;
            case "小满":
                background.setBackground(getDrawable(R.drawable.jqxiaoman));
                break;
            case "芒种":
                background.setBackground(getDrawable(R.drawable.jqmangzhong));
                break;
            case "夏至":
                background.setBackground(getDrawable(R.drawable.jqxiazhi));
                break;
            case "小暑":
                background.setBackground(getDrawable(R.drawable.jqxiaoshu));
                break;
            case "大暑":
                background.setBackground(getDrawable(R.drawable.jqdashu));
                break;
            case "立秋":
                background.setBackground(getDrawable(R.drawable.jqliqiu));
                break;
            case "处暑":
                background.setBackground(getDrawable(R.drawable.jqchushu));
                break;
            case "白露":
                background.setBackground(getDrawable(R.drawable.jqbailu));
                break;
            case "秋分":
                background.setBackground(getDrawable(R.drawable.jqqiufen));
                break;
            case "寒露":
                background.setBackground(getDrawable(R.drawable.jqhanlu));
                break;
            case "霜降":
                background.setBackground(getDrawable(R.drawable.jqshuangjiang));
                break;
            case "立冬":
                background.setBackground(getDrawable(R.drawable.jqlidong));
                break;
            case "小雪":
                background.setBackground(getDrawable(R.drawable.jqxiaoxue));
                break;
            case "大雪":
                background.setBackground(getDrawable(R.drawable.jqdaxue));
                break;
            case "冬至":
                background.setBackground(getDrawable(R.drawable.jqdongzhi));
                break;
            case "小寒":
                background.setBackground(getDrawable(R.drawable.jqxiaohan));
                break;
            case "大寒":
                background.setBackground(getDrawable(R.drawable.jqdahan));
                break;
            default:
                result=false;
                break;
        }
        return result;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void loadtimeandbackground(){
        switch (sdf.format(new Date())) {////获取当前时间并转换为字符串
            case "23":
            case "00":
                time.setText("子时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            case "01":
            case "02":
                time.setText("丑时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            case "03":
            case "04":
                time.setText("寅时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            case "05":
                time.setText("卯时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            case "06":
                time.setText("卯时");
                background.setBackground(getDrawable(R.drawable.morning));
                break;
            case "07":
            case "08":
                time.setText("辰时");
                background.setBackground(getDrawable(R.drawable.morning));
                break;
            case "09":
            case "10":
                time.setText("巳时");
                background.setBackground(getDrawable(R.drawable.morning));
                break;
            case "11":
                time.setText("午时");
                background.setBackground(getDrawable(R.drawable.morning));
                break;
            case "12":
                time.setText("午时");
                background.setBackground(getDrawable(R.drawable.noon));
                break;
            case "13":
            case "14":
                time.setText("未时");
                background.setBackground(getDrawable(R.drawable.noon));
                break;
            case "15":
            case "16":
                time.setText("申时");
                background.setBackground(getDrawable(R.drawable.noon));
                break;
            case "17":
            case "18":
                time.setText("酉时");
                background.setBackground(getDrawable(R.drawable.noon));
                break;
            case "19":
            case "20":
                time.setText("戌时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            case "21":
            case "22":
                time.setText("亥时");
                background.setBackground(getDrawable(R.drawable.night));
                break;
            default:
                break;
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void loadtime(){
        switch (sdf.format(new Date())) {////获取当前时间并转换为字符串
            case "23":
            case "00":
                time.setText("子时");
                break;
            case "01":
            case "02":
                time.setText("丑时");
                break;
            case "03":
            case "04":
                time.setText("寅时");
                break;
            case "05":
            case "06":
                time.setText("卯时");
                break;
            case "07":
            case "08":
                time.setText("辰时");
                break;
            case "09":
            case "10":
                time.setText("巳时");
                break;
            case "11":
            case "12":
                time.setText("午时");
                break;
            case "13":
            case "14":
                time.setText("未时");
                break;
            case "15":
            case "16":
                time.setText("申时");
                break;
            case "17":
            case "18":
                time.setText("酉时");
                break;
            case "19":
            case "20":
                time.setText("戌时");
                break;
            case "21":
            case "22":
                time.setText("亥时");
                break;
            default:
                break;
        }
    }
}
