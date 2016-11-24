package com.example.shubham.edx_project.FavoriteListDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shubham.edx_project.EdXModel.DBModelDataProvider.FavoriteListDataSchema;

import static android.content.ContentValues.TAG;

/**
 *  For Database Operations: DatabaseHelper Class
 *
 * Created by shubham on 22/11/16.
 */
public class FavoriteListDB extends SQLiteOpenHelper {
    //database info and version
    private static final String DATABASE_NAME="FAVORITE_COURSE.DB";
    private static final int DB_VERSION=1;
    //table query
    private static final String TABLE_QUERY="CREATE TABLE "+ FavoriteListDataSchema.TABLE_NAME+"("
            + FavoriteListDataSchema.FavouriteList.COURSE_ID+" TEXT UNIQUE,"
            + FavoriteListDataSchema.FavouriteList.COURSE_NAME+" TEXT,"
            + FavoriteListDataSchema.FavouriteList.COURSE_NUMBER+" TEXT,"
            + FavoriteListDataSchema.FavouriteList.COURSE_ORG+" TEXT,"
            + FavoriteListDataSchema.FavouriteList.COURSE_START_DATE+" TEXT,"
            + FavoriteListDataSchema.FavouriteList.COURSE_PACING+" TEXT,"
            + FavoriteListDataSchema.FavouriteList.COURSE_IMAGE_URL+" TEXT);";
// constructor for databaseHelper class
    public FavoriteListDB(Context context) {
        super(context, DATABASE_NAME, null,DB_VERSION);
        Log.d("DB operation :","table creation or open from constructor ");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create  table
        try {
            sqLiteDatabase.execSQL(TABLE_QUERY);
        }
        catch (SQLiteException e)
        {
            Log.getStackTraceString(e);
        }
    }
    /**
     * method for adding information into db
     *
     * @param iCourseId : courseId of course
     * @param iCourseName : Course Name
     * @param iCourseNumber : Course Number by Organisation
     * @param iCourseOrg : Organisation Number
     * @param iCourseStartDate : Start Date of Course
     * @param iCoursePacing : Pacing of Course i.e by self or instructor
     * @param iCourseImageUrl : Course Image url
     * @param iSqLiteDatabase : instance of sqlLite Database
     * @return  : the long type value for checking of insertion on calling side of method
     */
    public long addData(String iCourseId,String iCourseName,String iCourseNumber,String iCourseOrg,String iCourseStartDate,String iCoursePacing,String iCourseImageUrl,SQLiteDatabase iSqLiteDatabase)
    {
        long checking=0;
        ContentValues iValues=new ContentValues();
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_ID,iCourseId);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_NAME,iCourseName);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_NUMBER,iCourseNumber);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_ORG,iCourseOrg);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_START_DATE,iCourseStartDate);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_PACING,iCoursePacing);
        iValues.put(FavoriteListDataSchema.FavouriteList.COURSE_IMAGE_URL,iCourseImageUrl);
        //inserting in table
        try {
            checking =  iSqLiteDatabase.insert(FavoriteListDataSchema.TABLE_NAME, null, iValues);
        }
        catch (SQLiteException | NullPointerException e)
        {
            Log.d(TAG, "addData: insertion unsuccessful", e);
            e.printStackTrace();
        }
        return checking;
    }
    /**
     * getting data from database table
     *
     * @param iSqLiteDatabase : for getting the sqlLite database instance
     * @return cursor  : in which all the values are present after getting from database
     */
    public Cursor getDataFromCourseTable(SQLiteDatabase iSqLiteDatabase)
    {
        Cursor cursor=null;
        String []iProjection={
                FavoriteListDataSchema.FavouriteList.COURSE_ID,
                FavoriteListDataSchema.FavouriteList.COURSE_NAME,
                FavoriteListDataSchema.FavouriteList.COURSE_NUMBER,
                FavoriteListDataSchema.FavouriteList.COURSE_ORG,
                FavoriteListDataSchema.FavouriteList.COURSE_START_DATE,
                FavoriteListDataSchema.FavouriteList.COURSE_PACING,
                FavoriteListDataSchema.FavouriteList.COURSE_IMAGE_URL
        };
        try {
            cursor=iSqLiteDatabase.query(FavoriteListDataSchema.TABLE_NAME,iProjection,null,null,null,null,null);
            Log.d("DB operation","implementing getting result from database");
        }
        catch (SQLiteException | NullPointerException e)
        {
            e.printStackTrace();
        }
        return  cursor;
    }

    /**
     * delete a particular course from favourite list
     *
     * @param iCourseId : using course id for deleting the course
     * @param iSqLiteDatabase :for getting the course instance
     * @return
     */
    public int deleteCourseFromTable(String iCourseId,SQLiteDatabase iSqLiteDatabase) {
        String iSelection = FavoriteListDataSchema.FavouriteList.COURSE_ID + " LIKE ?";
        String[] iSelectionArgs = {iCourseId};
        //deleting the course using courseId
        try
        {
            int deletedRow=iSqLiteDatabase.delete(FavoriteListDataSchema.TABLE_NAME,iSelection,iSelectionArgs);
            return deletedRow;
        }catch (SQLiteException | NullPointerException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
