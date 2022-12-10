package com.example.keshe;

import android.content.Context;
import android.database.Cursor;

import com.example.keshe.bean.ArticleBean;
import com.example.keshe.database.CollectionDBHelper;

import java.util.ArrayList;
import java.util.Objects;

public class ArticleInfo {
    private static String[] articleIDArray = {"1", "2"};
    private static String[] titleArray = {"测试标题1", "测试标题2"};
    private static String[] contentArray = {"红红火火恍恍惚惚或或哈哈哈哈", "6666666666666666666666"};
    private static int[] articlePicArray = {R.drawable.rain_cow, R.drawable.rabit};
    private static String[] authorArray = {"zxx", "ltb"};

    public static ArrayList<ArticleBean> getArticleList() {
        ArrayList<ArticleBean> articleList = new ArrayList<>();
        for (int i = 0; i < articleIDArray.length; i++) {
            ArticleBean bean = new ArticleBean(articleIDArray[i], titleArray[i], contentArray[i], articlePicArray[i], authorArray[i]);
            articleList.add(bean);
        }
        return articleList;
    }

    public static ArrayList<ArticleBean> getFavouriteList(Context context) {
        CollectionDBHelper helper = CollectionDBHelper.getInstance(context);
        helper.OpenReadLink();
        ArrayList<ArticleBean> favouriteList = new ArrayList<>();
        ArrayList<String> articleIDList = helper.QueryAll();
        for (String articleID : articleIDList) {
            for (int i = 0; i < articleIDArray.length; i++) {
                if (Objects.equals(articleID, articleIDArray[i])) {
                    favouriteList.add(new ArticleBean(articleID, titleArray[i], contentArray[i], articlePicArray[i], authorArray[i]));
                }
            }
        }
        helper.CloseLink();
        return favouriteList;
    }
}
