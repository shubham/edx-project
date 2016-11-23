
package com.example.shubham.edx_project.EdXModel.CourseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Pagination {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("num_pages")
    @Expose
    private int numPages;
    @SerializedName("next")
    @Expose
    private String next;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pagination() {
    }

    /**
     * 
     * @param previous
     * @param count
     * @param next
     * @param numPages
     */
    public Pagination(int count, String previous, int numPages, String next) {
        this.count = count;
        this.previous = previous;
        this.numPages = numPages;
        this.next = next;
    }

    /**
     * 
     * @return
     *     The count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * 
     * @param previous
     *     The previous
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * 
     * @return
     *     The numPages
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * 
     * @param numPages
     *     The num_pages
     */
    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    /**
     * 
     * @return
     *     The next
     */
    public String getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(String next) {
        this.next = next;
    }

}
