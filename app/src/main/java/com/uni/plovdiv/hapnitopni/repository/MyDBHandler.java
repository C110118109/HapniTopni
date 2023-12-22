package com.uni.plovdiv.hapnitopni.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uni.plovdiv.hapnitopni.entities.Favourites;
import com.uni.plovdiv.hapnitopni.entities.Locations;
import com.uni.plovdiv.hapnitopni.entities.Orders;
import com.uni.plovdiv.hapnitopni.entities.Products;
import com.uni.plovdiv.hapnitopni.entities.Users;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hapniTopni.db";

    //table USERS info
    public static final String TABLE_USERS_NAME = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
//    public static final String COLUMN_IS_CURRENT_USER = "isCurrentUser";

    //table PRODUCTS info
    public static final String TABLE_PRODUCTS_NAME = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_IMAGE_ID = "image_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";

    //table FAVOURITES info
    public static final String TABLE_FAVOURITES_NAME = "favourites";
    public static final String COLUMN_FAVOURITE_ID = "fa_id";
    public static final String COLUMN_FA_IMAGE_ID = "fa_image_id";
    public static final String COLUMN_FA_PRODUCT_NAME = "fa_product_name";
    public static final String COLUMN_FA_DESCRIPTION = "fa_description";
    public static final String COLUMN_FA_PRICE = "fa_price";
    public static final String COLUMN_STATUS = "status";

    //table ORDERS info
    public static final String TABLE_ORDERS_NAME = "orders";
    public static final String COLUMN_ORDER_ID = "id";
    public static final String COLUMN_ORDER_IMAGE_ID = "image_id";
    public static final String COLUMN_ORDER_NAME = "name";
    public static final String COLUMN_ORDER_DESCRIPTION = "description";
    public static final String COLUMN_ORDER_PRICE = "price";
    public static final String COLUMN_ORDER_QUANTITY = "quantity";


    //table LOCATIONS info
    public static final String TABLE_LOCATIONS_NAME = "locations";
    public static final String COLUMN_LOCATION_ID = "id";
    public static final String COLUMN_PHOTO_ID = "photo_id";
    public static final String COLUMN_LOCATION_NAME = "location_name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TAB = "tab";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_USERS_NAME + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_PASSWORD + " TEXT ," +
                COLUMN_NAME + " TEXT " +
//                COLUMN_IS_CURRENT_USER + " TEXT " +
                ");";


        //if price is string could be easiest for retrieving data and put in the fragment using TextView
        String query2 = "CREATE TABLE " + TABLE_PRODUCTS_NAME + "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_ID + " INTEGER ," +
                COLUMN_PRODUCT_NAME + " TEXT ," +
                COLUMN_DESCRIPTION + " TEXT ," +
                COLUMN_PRICE + " TEXT " +
                ");";

//        String query3 = "CREATE TABLE " + TABLE_ORDERS_NAME + "(" +
//                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_USER_TO_ORDERS_ID + " INTEGER ," +
//                COLUMN_PRODUCT_TO_ORDERS_ID + " INTEGER ," +
//                COLUMN_QUANTITY + " INTEGER " +
//                ");";
        String query3 = "CREATE TABLE " + TABLE_LOCATIONS_NAME + "(" +
                COLUMN_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PHOTO_ID + " INTEGER ," +
                COLUMN_LOCATION_NAME + " TEXT ," +
                COLUMN_ADDRESS + " TEXT ," +
                COLUMN_TAB + " TEXT " +
                ");";

        String query4 = "CREATE TABLE " + TABLE_ORDERS_NAME + "(" +
                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORDER_IMAGE_ID + " INTEGER ," +
                COLUMN_ORDER_NAME + " TEXT ," +
                COLUMN_ORDER_DESCRIPTION + " TEXT ," +
                COLUMN_ORDER_PRICE + " INTEGER ," +
                COLUMN_ORDER_QUANTITY + " INTEGER" +
                ");";

        //if price is string could be easiest for retrieving data and put in the fragment using TextView
        String query5 = "CREATE TABLE " + TABLE_FAVOURITES_NAME + "(" +
                COLUMN_FAVOURITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FA_IMAGE_ID + " INTEGER ," +
                COLUMN_FA_PRODUCT_NAME + " TEXT ," +
                COLUMN_FA_DESCRIPTION + " TEXT ," +
                COLUMN_FA_PRICE + " TEXT ," +
                COLUMN_STATUS + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(query4);
        sqLiteDatabase.execSQL(query5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES_NAME);
        onCreate(sqLiteDatabase);
    }

    //add a new product in table Products
    public void addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, product.getImage());
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS_NAME, null, values);
        db.close();
    }
    public void addFavourite(Favourites favourite) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FA_IMAGE_ID, favourite.getImage());
        values.put(COLUMN_FA_PRODUCT_NAME, favourite.getName());
        values.put(COLUMN_FA_DESCRIPTION, favourite.getDescription());
        values.put(COLUMN_FA_PRICE, favourite.getPrice());
        values.put(COLUMN_STATUS, favourite.getStatus());


        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_FAVOURITES_NAME, null, values);
        db.close();
    }
    public void addOrder(Orders order) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_IMAGE_ID, order.getImage());
        values.put(COLUMN_ORDER_NAME, order.getName());
        values.put(COLUMN_ORDER_DESCRIPTION, order.getDescription());
        values.put(COLUMN_ORDER_PRICE, order.getPrice());
        values.put(COLUMN_ORDER_QUANTITY, order.getQuantity());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_ORDERS_NAME, null, values);
        db.close();
    }

    public void addLocation(Locations location) {
        ContentValues valuess = new ContentValues();
        valuess.put(COLUMN_PHOTO_ID, location.getPhoto());
        valuess.put(COLUMN_LOCATION_NAME, location.getName());
        valuess.put(COLUMN_ADDRESS, location.getAddress());
        valuess.put(COLUMN_TAB, location.getTab());

        SQLiteDatabase dbb = getWritableDatabase();

        dbb.insert(TABLE_LOCATIONS_NAME, null, valuess);
        dbb.close();
    }

    //check if already have a product with the same name
    public Boolean checkProductExist(Products product) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from products where product_name = ?",
                new String[]{product.getName()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }
    public Boolean checkFavouriteExist(Favourites favourite) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from favourites where fa_product_name = ?",
                new String[]{favourite.getName()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkOrderExist(Orders order) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from orders where name = ?",
                new String[]{order.getName()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkLocationExist(Locations location) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from locations where location_name = ?",
                new String[]{location.getName()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    // we have created a new method for reading all the courses.
    public ArrayList<Products> allProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_PRODUCTS_NAME;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Products> productsArrayList= new ArrayList<>();

        // from first to last row in table products
        if (cursor.moveToFirst()) {
            do {
                //adding the data
                productsArrayList.add(new Products(cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        //return ArrayList for easiest use
        return productsArrayList;
    }

    public ArrayList<Favourites> allFavourites() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_FAVOURITES_NAME;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Favourites> favouritesArrayList= new ArrayList<>();

        // from first to last row in table products
        if (cursor.moveToFirst()) {
            do {
                //adding the data
                favouritesArrayList.add(new Favourites(cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        //return ArrayList for easiest use
        return favouritesArrayList;
    }

    public ArrayList<Locations> allLocations() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_LOCATIONS_NAME;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Locations> locationsArrayList= new ArrayList<>();

        // from first to last row in table products
        if (cursor.moveToFirst()) {
            do {
                //adding the data
                locationsArrayList.add(new Locations(cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        //return ArrayList for easiest use
        return locationsArrayList;
    }

    public ArrayList<Orders> allOrders() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_ORDERS_NAME;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Orders> ordersArrayList= new ArrayList<>();

        // from first to last row in table products
        if (cursor.moveToFirst()) {
            do {
                //adding the data
                ordersArrayList.add(new Orders(cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        //return ArrayList for easiest use
        return ordersArrayList;
    }

    //add a new row to the table Orders
    public Boolean Orderation(Orders order) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_IMAGE_ID, order.getImage());
        values.put(COLUMN_ORDER_NAME, order.getName());
        values.put(COLUMN_ORDER_DESCRIPTION, order.getDescription());
        values.put(COLUMN_ORDER_PRICE, order.getPrice());
        values.put(COLUMN_ORDER_QUANTITY, order.getQuantity());
//        values.put(COLUMN_IS_CURRENT_USER, bool);
        SQLiteDatabase db = getWritableDatabase();
        //db.insert returns type long
        long result = db.insert(TABLE_ORDERS_NAME, null, values);
        db.close();

        return result != -1;
    }


    //add a new row to the table Users
    public Boolean Registration(Users user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_IS_CURRENT_USER, bool);
        SQLiteDatabase db = getWritableDatabase();
        //db.insert returns type long
        long result = db.insert(TABLE_USERS_NAME, null, values);
        db.close();

        return result != -1;
    }

    //I use this for registration logic
    //this method check if the email already exists in the table users
    public Boolean checkRegistrationExist(Users user) {
        SQLiteDatabase db = getWritableDatabase();

        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from users where email = ?",
                new String[]{user.getEmail()});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    //Here I use this method for login purpose
    ////this method should check if the email and password was in the database
    public Boolean checkLoginParametersExist(Users user) {
        SQLiteDatabase db = getWritableDatabase();


        //rawQuery method ask for query and array where to put the selected info
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?",
                new String[]{user.getEmail(), user.getPassword()});

        return cursor.getCount() > 0;

    }
    public String[] getOrderProduct(String number) {
        SQLiteDatabase db = getWritableDatabase();
        String ProductName= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select product_name from orders where id =" + number ,null);

        if(cursor.moveToFirst()){
            ProductName += cursor.getString(0);

        }else{
            ProductName = "not found";
        }

        result[0] = ProductName;

        return result;
    }

    public String[] getOrderQuantity(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String quantity="";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select name from orders where name =" + name ,null);

        if(cursor.moveToFirst()){
            name += cursor.getString(0);

        }else{
            name = "not found";
        }

        result[0] = name;

        return result;
    }
    public String[] getUserEmail(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        String email= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select email from users where id =" + user_id ,null);

        if(cursor.moveToFirst()){
            email += cursor.getString(0);

        }else{
            email = "not found";
        }

        result[0] = email;

        return result;
    }

    public String[] getUserName(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        String name= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select name from users where id =" + user_id ,null);

        if(cursor.moveToFirst()){
            name += cursor.getString(0);

        }else{
            name = "not found";
        }

        result[0] = name;

        return result;
    }

    public String[] getUserPassword(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        String password= "";
        String[] result = new String[1];
        Cursor cursor = db.rawQuery("Select password from users where id =" + user_id ,null);

        if(cursor.moveToFirst()){
            password += cursor.getString(0);

        }else{
            password = "not found";
        }

        result[0] = password;

        return result;
    }

    public String getUserId(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("Select id from users where email = ? and password = ?",
                new String[]{email, password});

        if (cursor.moveToFirst()) {
                result = result + cursor.getString(0);
            } else {
                result = result + "result not found";
            }

        return result;
    }

    public void deleteUser(int userId){
        SQLiteDatabase db = getWritableDatabase();
        String query  = String.format("Delete from users where id = %d", userId);
        db.execSQL(query);
    }
    public void clearOrderData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("orders", null, null);
        db.close();
    }
    public void deleteFavourites (String faName){
        SQLiteDatabase db = getWritableDatabase();
        String query  = String.format("Delete from favourites where fa_product_name = '%s' ", faName);
        db.execSQL(query);
    }


    public void editUser(int id,String name, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        String query =
                String.format("Update users " +
                                "set name = '%s', email = '%s', password = '%s' " +
                                " where id = %d",
                                      name,email,password, id);

        db.execSQL(query);

    }



}