package com.example.keshe;

import android.content.Context;
import android.database.Cursor;

import com.example.keshe.bean.ArticleBean;
import com.example.keshe.database.CollectionDBHelper;

import java.util.ArrayList;
import java.util.Objects;

public class ArticleInfo {
    private static String[] articleIDArray = {"1", "2","3","4","5","6"};
    private static String[] titleArray = {"二十四节气之白露", "二十四节气之处暑","二十四节气之立秋","二十四节气之大暑","二十四节气之小暑","二十四节气之冬至"};
    private static String[] contentArray = {"白露，是“二十四节气”中的第15个节气，秋季第3个节气，干支历申月的结束与酉月的起始。斗指癸；太阳达黄经165度；于公历9月7-9日交节。“白露”是反映自然界寒气增长的重要节气。由于冷空气转守为攻，白昼有阳光尚热，但傍晚后气温便很快下降，昼夜温差逐渐拉大。",
            "处暑，是二十四节气之第十四个节气，也是秋季的第二个节气。斗指戊（西南方）；太阳黄经达150°；于每年公历8月22-24日交节。处暑，即为“出暑”，是炎热离开的意思。时至处暑，太阳直射点继续南移、太阳辐射减弱，副热带高压也向南撤退，暑意渐消。",
            "立秋，是“二十四节气”之第十三个节气，秋季的第一个节气。斗指西南，太阳达黄经135°，于每年公历8月7或8日交节。整个自然界的变化是循序渐进的过程，立秋是阳气渐收、阴气渐长，由阳盛逐渐转变为阴盛的转折。在自然界，万物开始从繁茂成长趋向萧瑟成熟。",
            "大暑，二十四节气之一，是夏季最后一个节气。斗指未，太阳黄经为120°，于公历7月22—24日交节。“暑”是炎热的意思，大暑，指炎热之极。大暑相对小暑，更加炎热，是一年中阳光最猛烈、最炎热的节气，“湿热交蒸”在此时到达顶点。大暑气候特征：高温酷热，雷暴、台风频繁。",
            "小暑，是二十四节气之第十一个节气，干支历午月的结束以及未月的起始。斗指辛，太阳到达黄经105度，于每年公历7月6-8日交节。暑，是炎热的意思，小暑为小热，还不十分热。小暑虽不是一年中最炎热的时节，但紧接着就是一年中最热的节气大暑，民间有“小暑大暑，上蒸下煮”之说。我国多地自小暑起进入雷暴最多的时节。",
            "冬至，又称日南至、冬节、亚岁等，兼具自然与人文两大内涵，既是二十四节气中一个重要的节气，也是中国民间的传统祭祖节日。冬至是四时八节之一，被视为冬季的大节日，在古代民间有“冬至大如年”的讲法。冬至习俗因地域不同而又存在着习俗内容或细节上的差异。在中国南方地区，有冬至祭祖、宴饮的习俗。在中国北方地区，每年冬至日有吃饺子的习俗。"};
    private static int[] articlePicArray = {R.drawable.bailu, R.drawable.chushu,R.drawable.liqiu,R.drawable.dashu,R.drawable.xiaoshu,R.drawable.dongzhi};
    private static String[] authorArray = {"zxx", "ltb","cjl","zxx","ltb","cjl"};

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
