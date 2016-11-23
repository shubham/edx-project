package com.example.shubham.edx_project.EdXModel.DBModelDataProvider;

/**
 * Created by shubham on 22/11/16.
 */
public class FavoriteListDataProvider {

    private String mCourseId;
    private String mCourseName;
    private String mCourseNumber;
    private String mCourseOrg;
    private String mCourseStartDate;
    private String mCoursePacing;
    private String mCourseImageUrl;

    public FavoriteListDataProvider(String mCourseId, String mCourseName, String mCourseNumber, String mCourseOrg, String mCourseStartDate, String mCoursePacing, String mCourseImageUrl) {
        this.mCourseId = mCourseId;
        this.mCourseName = mCourseName;
        this.mCourseNumber = mCourseNumber;
        this.mCourseOrg = mCourseOrg;
        this.mCourseStartDate = mCourseStartDate;
        this.mCoursePacing = mCoursePacing;
        this.mCourseImageUrl = mCourseImageUrl;
    }

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getmCourseName() {
        return mCourseName;
    }

    public void setmCourseName(String mCourseName) {
        this.mCourseName = mCourseName;
    }

    public String getmCourseNumber() {
        return mCourseNumber;
    }

    public void setmCourseNumber(String mCourseNumber) {
        this.mCourseNumber = mCourseNumber;
    }

    public String getmCourseOrg() {
        return mCourseOrg;
    }

    public void setmCourseOrg(String mCourseOrg) {
        this.mCourseOrg = mCourseOrg;
    }

    public String getmCourseStartDate() {
        return mCourseStartDate;
    }

    public void setmCourseStartDate(String mCourseStartDate) {
        this.mCourseStartDate = mCourseStartDate;
    }

    public String getmCoursePacing() {
        return mCoursePacing;
    }

    public void setmCoursePacing(String mCoursePacing) {
        this.mCoursePacing = mCoursePacing;
    }

    public String getmCourseImageUrl() {
        return mCourseImageUrl;
    }

    public void setmCourseImageUrl(String mCourseImageUrl) {
        this.mCourseImageUrl = mCourseImageUrl;
    }
}
