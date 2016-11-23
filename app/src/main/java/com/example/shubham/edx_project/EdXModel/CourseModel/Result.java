
package com.example.shubham.edx_project.EdXModel.CourseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("blocks_url")
    @Expose
    private String blocksUrl;
    @SerializedName("effort")
    @Expose
    private Object effort;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("enrollment_start")
    @Expose
    private String enrollmentStart;
    @SerializedName("enrollment_end")
    @Expose
    private Object enrollmentEnd;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("org")
    @Expose
    private String org;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("start_display")
    @Expose
    private String startDisplay;
    @SerializedName("start_type")
    @Expose
    private String startType;
    @SerializedName("pacing")
    @Expose
    private String pacing;
    @SerializedName("mobile_available")
    @Expose
    private boolean mobileAvailable;
    @SerializedName("hidden")
    @Expose
    private boolean hidden;
    @SerializedName("course_id")
    @Expose
    private String courseId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param blocksUrl
     * @param effort
     * @param number
     * @param courseId
     * @param org
     * @param enrollmentStart
     * @param id
     * @param startType
     * @param startDisplay
     * @param enrollmentEnd
     * @param shortDescription
     * @param start
     * @param hidden
     * @param name
     * @param mobileAvailable
     * @param media
     * @param end
     * @param pacing
     */
    public Result(String blocksUrl, Object effort, String end, String enrollmentStart, Object enrollmentEnd, String id, Media media, String name, String number, String org, String shortDescription, String start, String startDisplay, String startType, String pacing, boolean mobileAvailable, boolean hidden, String courseId) {
        this.blocksUrl = blocksUrl;
        this.effort = effort;
        this.end = end;
        this.enrollmentStart = enrollmentStart;
        this.enrollmentEnd = enrollmentEnd;
        this.id = id;
        this.media = media;
        this.name = name;
        this.number = number;
        this.org = org;
        this.shortDescription = shortDescription;
        this.start = start;
        this.startDisplay = startDisplay;
        this.startType = startType;
        this.pacing = pacing;
        this.mobileAvailable = mobileAvailable;
        this.hidden = hidden;
        this.courseId = courseId;
    }

    /**
     * 
     * @return
     *     The blocksUrl
     */
    public String getBlocksUrl() {
        return blocksUrl;
    }

    /**
     * 
     * @param blocksUrl
     *     The blocks_url
     */
    public void setBlocksUrl(String blocksUrl) {
        this.blocksUrl = blocksUrl;
    }

    /**
     * 
     * @return
     *     The effort
     */
    public Object getEffort() {
        return effort;
    }

    /**
     * 
     * @param effort
     *     The effort
     */
    public void setEffort(Object effort) {
        this.effort = effort;
    }

    /**
     * 
     * @return
     *     The end
     */
    public String getEnd() {
        return end;
    }

    /**
     * 
     * @param end
     *     The end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * 
     * @return
     *     The enrollmentStart
     */
    public String getEnrollmentStart() {
        return enrollmentStart;
    }

    /**
     * 
     * @param enrollmentStart
     *     The enrollment_start
     */
    public void setEnrollmentStart(String enrollmentStart) {
        this.enrollmentStart = enrollmentStart;
    }

    /**
     * 
     * @return
     *     The enrollmentEnd
     */
    public Object getEnrollmentEnd() {
        return enrollmentEnd;
    }

    /**
     * 
     * @param enrollmentEnd
     *     The enrollment_end
     */
    public void setEnrollmentEnd(Object enrollmentEnd) {
        this.enrollmentEnd = enrollmentEnd;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The number
     */
    public String getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The org
     */
    public String getOrg() {
        return org;
    }

    /**
     * 
     * @param org
     *     The org
     */
    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * 
     * @return
     *     The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * 
     * @param shortDescription
     *     The short_description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * 
     * @return
     *     The start
     */
    public String getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The startDisplay
     */
    public String getStartDisplay() {
        return startDisplay;
    }

    /**
     * 
     * @param startDisplay
     *     The start_display
     */
    public void setStartDisplay(String startDisplay) {
        this.startDisplay = startDisplay;
    }

    /**
     * 
     * @return
     *     The startType
     */
    public String getStartType() {
        return startType;
    }

    /**
     * 
     * @param startType
     *     The start_type
     */
    public void setStartType(String startType) {
        this.startType = startType;
    }

    /**
     * 
     * @return
     *     The pacing
     */
    public String getPacing() {
        return pacing;
    }

    /**
     * 
     * @param pacing
     *     The pacing
     */
    public void setPacing(String pacing) {
        this.pacing = pacing;
    }

    /**
     * 
     * @return
     *     The mobileAvailable
     */
    public boolean isMobileAvailable() {
        return mobileAvailable;
    }

    /**
     * 
     * @param mobileAvailable
     *     The mobile_available
     */
    public void setMobileAvailable(boolean mobileAvailable) {
        this.mobileAvailable = mobileAvailable;
    }

    /**
     * 
     * @return
     *     The hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * 
     * @param hidden
     *     The hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * 
     * @return
     *     The courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 
     * @param courseId
     *     The course_id
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
