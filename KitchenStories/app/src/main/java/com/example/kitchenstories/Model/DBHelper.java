package com.example.kitchenstories.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kitchenstories.Model.Shopping.IngredientShopping;
import com.example.kitchenstories.Model.Shopping.RecipeForShopping;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //db name
    public static final String DB_NAME = "RECIPE_SHOPPING_DB";
    //db version
    public  static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME_SHOPPING = "SHOPPING_TABLE";
    public static final String TABLE_NAME_INGREDIENT = "INGREDIENT_TABLE";
    //table SHOPPING collumns
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_KEYSEARCH = "KEYSEARCH";
    //table INGREDIENT collumns
    public static final String C_ID_SHOPPING = "IDSHOPPING";
    public static final String C_AMOUNT = "AMOUNT";
    public static final String C_NAMEINGRE = "NAMEINGRE";
    public static final String C_STATUS = "STATUS";
    //create query for table SHOPPING
    public static final String CREATE_TABLE_SHOPPING = "CREATE TABLE "+TABLE_NAME_SHOPPING+" ("
            + C_ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +C_NAME +" TEXT, "
            +C_IMAGE+" BLOB, "
            +C_KEYSEARCH+" TEXT);";
    //create query for table INGREDIENT
    public static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE "+TABLE_NAME_INGREDIENT+" ("
            + C_ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +C_ID_SHOPPING +" INTEGER, "
            +C_AMOUNT +" TEXT, "
            +C_NAMEINGRE +" TEXT, "
            +C_STATUS+" INTEGER);";

    private ByteArrayOutputStream arrayOutputStream;
    private byte[] imageInByte;

    Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SHOPPING);
        db.execSQL(CREATE_TABLE_INGREDIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SHOPPING);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_INGREDIENT);
        onCreate(db);
    }

    public long insertInfoShopping(String name, Bitmap image, String keysearch)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * from " + TABLE_NAME_SHOPPING +" where " + C_KEYSEARCH + " = ?",new String [] {keysearch});
        if(cursor1.getCount()>0){
            return -1;
        }
        else {
            SQLiteDatabase db1 = this.getWritableDatabase();
            arrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, arrayOutputStream);
            imageInByte = arrayOutputStream.toByteArray();

            ContentValues values = new ContentValues();

            values.put(C_NAME, name);
            values.put(C_IMAGE, imageInByte);
            values.put(C_KEYSEARCH, keysearch);

            long id = db1.insert(TABLE_NAME_SHOPPING, null, values);
            db1.close();
            return id;
        }
    }

    public long insertInfoIngre(int IdShopping,String amount,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_ID_SHOPPING ,IdShopping);
        values.put(C_AMOUNT,amount);
        values.put(C_NAMEINGRE,name);
        values.put(C_STATUS,0);

        long id = db.insert(TABLE_NAME_INGREDIENT,null,values);
        db.close();
        return id;
    }



    public int getMaxId(){
        int mx=-1;
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT max(ID) from " +TABLE_NAME_SHOPPING,new String [] {});
            if (cursor != null)
                if(cursor.moveToFirst())
                {
                    mx= cursor.getInt(0);
                }
            //  cursor.close();
            return mx;
        }
        catch(Exception e){

            return -1;
        }
    }

    public RecipeForShopping getRecipeForShoppingByID(int idShopping){
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            RecipeForShopping recipeForShopping;
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + TABLE_NAME_SHOPPING +" where " + C_ID + " = "+idShopping,null);
            if(cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] imageBytes = cursor.getBlob(2);
                String key = cursor.getString(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                recipeForShopping = new RecipeForShopping(id, name, bitmap, key);
                return recipeForShopping;
            }
            else {
                Toast.makeText(context,"No values in DB",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch(Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public List<RecipeForShopping> getListRecipeForShopping(){
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            List<RecipeForShopping> shoppingList = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + TABLE_NAME_SHOPPING,null);
            if(cursor.getCount()!=0){
                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    byte[] imageBytes = cursor.getBlob(2);
                    String key = cursor.getString(3);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    shoppingList.add(new RecipeForShopping(id,name,bitmap,key));
                }
                return shoppingList;
            }
            else {
                // Toast.makeText(context,"No values in DB",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch(Exception e){
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public List<IngredientShopping> getListIngredient(int id){
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            List<IngredientShopping> ingredientShoppingListList = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + TABLE_NAME_INGREDIENT +" where " + C_ID_SHOPPING +" = "+id + " ORDER BY " + C_STATUS + " ASC",null);
            if(cursor.getCount()!=0){
                while (cursor.moveToNext()){
                    int idIngre = cursor.getInt(0);
                    int idShopping = cursor.getInt(1);
                    String amount = cursor.getString(2);
                    String nameIngre = cursor.getString(3);
                    int status = cursor.getInt(4);

                    ingredientShoppingListList.add(new IngredientShopping(idIngre,idShopping,nameIngre,amount,status));
                }
                return ingredientShoppingListList;

            }
            else {
                // Toast.makeText(context,"No values in DB",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch(Exception e){
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public int getCountIngreStatus0(int idShopping){
        int mx=-1;
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT COUNT(*) FROM " +TABLE_NAME_INGREDIENT +" WHERE "+C_ID_SHOPPING +" = " +idShopping +" AND " + C_STATUS +" =  0",new String [] {});
            if (cursor != null)
                if(cursor.moveToFirst())
                {
                    mx= cursor.getInt(0);
                }
            //  cursor.close();
            return mx;
        }
        catch(Exception e){
            return -1;
        }
    }

    public boolean updateStatusIngre(int id,int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_STATUS,status);
        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_NAME_INGREDIENT +" where " + C_ID + " = " +id ,new String [] {});
        if(cursor.getCount()>0){
            long result = db.update(TABLE_NAME_INGREDIENT ,contentValues,C_ID+ " = "+id,new String[]{});
            if(result==-1){
                return false;
            }
            else
                return true;
        }
        else
            return false;

    }
    public boolean deleteShopping(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * from " + TABLE_NAME_SHOPPING +" where " + C_ID + " = " +id ,new String [] {});
        if(cursor1.getCount()>0){
            long result = db.delete(TABLE_NAME_SHOPPING ,C_ID+ " = "+id,new String[]{});
            if(result==-1){
                return false;
            }
            else {
                Cursor cursor2 = db.rawQuery("SELECT * from " + TABLE_NAME_INGREDIENT +" where " + C_ID_SHOPPING + " = " +id ,new String [] {});
                if(cursor2.getCount()>0){
                    long result2 = db.delete(TABLE_NAME_INGREDIENT ,C_ID_SHOPPING+ " = "+id,new String[]{});
                    if(result==-1){
                        return false;
                    }
                    else
                        return true;
                }
                else
                    return false;
            }
        }
        else
            return false;

    }
}

