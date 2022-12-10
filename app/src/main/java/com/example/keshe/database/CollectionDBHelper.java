package com.example.keshe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.keshe.bean.ArticleBean;

import java.util.ArrayList;

public class CollectionDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "collection.db";
    private static final String TABLE_NAME = "ArticleCollection";
    private static final int DB_VERSION = 1;
    private static CollectionDBHelper helper = null;
    private SQLiteDatabase reader = null;
    private SQLiteDatabase writer = null;

    private CollectionDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static CollectionDBHelper getInstance(Context context) {
        if (helper == null) helper = new CollectionDBHelper(context);
        return helper;
    }

    public SQLiteDatabase OpenReadLink() {
        if (reader == null || !reader.isOpen()) reader = helper.getReadableDatabase();
        return reader;
    }

    public SQLiteDatabase OpenWriteLink() {
        if (writer == null || !writer.isOpen()) writer = helper.getWritableDatabase();
        return writer;
    }

    public void CloseLink() {
        if (reader != null && reader.isOpen()) {
            reader.close();
            reader = null;
        }
        if (writer != null && writer.isOpen()) {
            writer.close();
            writer = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "articleID INTEGER PRIMARY KEY NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long Insert(ArticleBean bean) {
        ContentValues values = new ContentValues();
        values.put("articleID", bean.getArticleID());
        return writer.insert(TABLE_NAME, null, values);
    }

    public long Delete(ArticleBean bean) {
        return writer.delete(TABLE_NAME, "articleID=?", new String[]{bean.getArticleID()});
    }

    public Boolean Query(ArticleBean bean) {
        Cursor cursor = reader.query(TABLE_NAME, null, "articleID=?", new String[]{bean.getArticleID()}, null, null, null);
        Boolean flag = cursor.moveToNext();
        cursor.close();
        return flag;
    }

    public ArrayList<String> QueryAll() {
        ArrayList<String> articleIDList = new ArrayList<>();
        Cursor cursor = reader.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) articleIDList.add(cursor.getString(0));
        cursor.close();
        return articleIDList;
    }
}
