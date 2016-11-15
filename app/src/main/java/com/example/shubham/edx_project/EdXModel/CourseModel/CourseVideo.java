
package com.example.shubham.edx_project.EdXModel.CourseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CourseVideo {

    @SerializedName("uri")
    @Expose
    private Object uri;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CourseVideo() {
    }

    /**
     * 
     * @param uri
     */
    public CourseVideo(Object uri) {
        this.uri = uri;
    }

    /**
     * 
     * @return
     *     The uri
     */
    public Object getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(Object uri) {
        this.uri = uri;
    }

}
