
package pojo.courseList;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "course_id",
    "sort_order",
    "display_course_name",
    "actual_course_name",
    "thumbnail_url",
    "course_type",
    "course_slug",
    "course_content_type",
    "course_video",
    "course_link",
    "s3_enabled",
    "s3_url"
})
@Generated("jsonschema2pojo")
public class Course {

    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("sort_order")
    private Integer sortOrder;
    @JsonProperty("display_course_name")
    private String displayCourseName;
    @JsonProperty("actual_course_name")
    private String actualCourseName;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("course_type")
    private Integer courseType;
    @JsonProperty("course_slug")
    private String courseSlug;
    @JsonProperty("course_content_type")
    private Integer courseContentType;
    @JsonProperty("course_video")
    private Object courseVideo;
    @JsonProperty("course_link")
    private Integer courseLink;
    @JsonProperty("s3_enabled")
    private Integer s3Enabled;
    @JsonProperty("s3_url")
    private String s3Url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("course_id")
    public Integer getCourseId() {
        return courseId;
    }

    @JsonProperty("course_id")
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @JsonProperty("sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @JsonProperty("display_course_name")
    public String getDisplayCourseName() {
        return displayCourseName;
    }

    @JsonProperty("display_course_name")
    public void setDisplayCourseName(String displayCourseName) {
        this.displayCourseName = displayCourseName;
    }

    @JsonProperty("actual_course_name")
    public String getActualCourseName() {
        return actualCourseName;
    }

    @JsonProperty("actual_course_name")
    public void setActualCourseName(String actualCourseName) {
        this.actualCourseName = actualCourseName;
    }

    @JsonProperty("thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @JsonProperty("course_type")
    public Integer getCourseType() {
        return courseType;
    }

    @JsonProperty("course_type")
    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    @JsonProperty("course_slug")
    public String getCourseSlug() {
        return courseSlug;
    }

    @JsonProperty("course_slug")
    public void setCourseSlug(String courseSlug) {
        this.courseSlug = courseSlug;
    }

    @JsonProperty("course_content_type")
    public Integer getCourseContentType() {
        return courseContentType;
    }

    @JsonProperty("course_content_type")
    public void setCourseContentType(Integer courseContentType) {
        this.courseContentType = courseContentType;
    }

    @JsonProperty("course_video")
    public Object getCourseVideo() {
        return courseVideo;
    }

    @JsonProperty("course_video")
    public void setCourseVideo(Object courseVideo) {
        this.courseVideo = courseVideo;
    }

    @JsonProperty("course_link")
    public Integer getCourseLink() {
        return courseLink;
    }

    @JsonProperty("course_link")
    public void setCourseLink(Integer courseLink) {
        this.courseLink = courseLink;
    }

    @JsonProperty("s3_enabled")
    public Integer getS3Enabled() {
        return s3Enabled;
    }

    @JsonProperty("s3_enabled")
    public void setS3Enabled(Integer s3Enabled) {
        this.s3Enabled = s3Enabled;
    }

    @JsonProperty("s3_url")
    public String getS3Url() {
        return s3Url;
    }

    @JsonProperty("s3_url")
    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
