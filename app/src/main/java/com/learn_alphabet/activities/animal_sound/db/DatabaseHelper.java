package com.learn_alphabet.activities.animal_sound.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.learn_alphabet.activities.animal_sound.models.MainCategoryModel;
import com.learn_alphabet.activities.animal_sound.models.SoundModel;
import com.learn_alphabet.activities.animal_sound.models.ItemsModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newdb.db";
    private static final int DATABASE_VERSION = 3;
    private static final String DB_PATH_SUFFIX = "/databases/";
    private static Context myContext;

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 3);
        myContext = context;
    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream open = myContext.getAssets().open(DATABASE_NAME);
        String databasePath = getDatabasePath();
        File file = new File(myContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(databasePath);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
                return;
            }
        }
    }

    private static String getDatabasePath() {
        return myContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void openDataBase() throws SQLException {
        if (myContext.getDatabasePath(DATABASE_NAME).exists()) {
            return;
        }
        try {
            CopyDataBaseFromAsset();
            System.out.println("Copying success from Assets folder");
        } catch (IOException e) {
            throw new RuntimeException("Error creating source database", e);
        }
    }

    @Override 
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String name = DatabaseHelper.class.getName();
        Log.w(name, "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        try {
            CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.w(DatabaseHelper.class.getName(), "Data base is upgraded  ");
    }

    public List<MainCategoryModel> getCategorieList() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM category", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(new MainCategoryModel(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getString(4)));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public List<SoundModel> getAnimalSound(int i) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM animalsound WHERE category_id=" + i, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(new SoundModel(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getString(4), rawQuery.getString(5), rawQuery.getString(6)));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public List<ItemsModel> getItems(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return Cursor(writableDatabase.rawQuery("SELECT * FROM items WHERE category_id=" + i, null));
    }

    private List<ItemsModel> Cursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                ItemsModel itemsmodel = new ItemsModel();
                itemsmodel.setId(cursor.getString(0));
                itemsmodel.setCategory_id(cursor.getString(1));
                itemsmodel.setName(cursor.getString(2));
                itemsmodel.setDesc(cursor.getString(3));
                itemsmodel.setSound_raw(cursor.getString(4));
                itemsmodel.setImg(cursor.getString(5));
                itemsmodel.setDesc2(cursor.getString(6));
                arrayList.add(itemsmodel);
            } while (cursor.moveToNext());
            return arrayList;
        }
        return arrayList;
    }

    public Cursor getItemNotify() {
        return getWritableDatabase().rawQuery("SELECT * FROM items ORDER BY RANDOM()", null);
    }
}
