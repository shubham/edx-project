
package com.example.shubham.edx_project.EdXModel.CourseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Media {

    @SerializedName("course_image")
    @Expose
    private CourseImage courseImage;
    @SerializedName("course_video")
    @Expose
    private CourseVideo courseVideo;
    @SerializedName("image")
    @Expose
    private Image image;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Media() {
    }

    /**
     * 
     * @param image
     * @param courseVideo
     * @param courseImage
     */
    public Media(CourseImage courseImage, CourseVideo courseVideo, Image image) {
        this.courseImage = courseImage;
        this.courseVideo = courseVideo;
        this.image = image;
    }

    /**
     * 
     * @return
     *     The courseImage
     */
    public CourseImage getCourseImage() {
        return courseImage;
    }

    /**
     * 
     * @param courseImage
     *     The course_image
     */
    public void setCourseImage(CourseImage courseImage) {
        this.courseImage = courseImage;
    }

    /**
     * 
     * @return
     *     The courseVideo
     */
    public CourseVideo getCourseVideo() {
        return courseVideo;
    }

    /**
     * 
     * @param courseVideo
     *     The course_video
     */
    public void setCourseVideo(CourseVideo courseVideo) {
        this.courseVideo = courseVideo;
    }

    /**
     * 
     * @return
     *     The image
     */
    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
