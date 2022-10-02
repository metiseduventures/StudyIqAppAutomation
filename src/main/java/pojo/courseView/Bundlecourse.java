
package pojo.courseView;

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
    "bundle_course_id",
    "bundle_course_name",
    "course_slug",
    "thumbnail",
    "bundle_course_validity",
    "bundle_course_language_id",
    "bundle_course_language_name",
    "bundle_course_price"
})
@Generated("jsonschema2pojo")
public class Bundlecourse {

    @JsonProperty("bundle_course_id")
    private Integer bundleCourseId;
    @JsonProperty("bundle_course_name")
    private String bundleCourseName;
    @JsonProperty("course_slug")
    private String courseSlug;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("bundle_course_validity")
    private Integer bundleCourseValidity;
    @JsonProperty("bundle_course_language_id")
    private Integer bundleCourseLanguageId;
    @JsonProperty("bundle_course_language_name")
    private String bundleCourseLanguageName;
    @JsonProperty("bundle_course_price")
    private String bundleCoursePrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bundle_course_id")
    public Integer getBundleCourseId() {
        return bundleCourseId;
    }

    @JsonProperty("bundle_course_id")
    public void setBundleCourseId(Integer bundleCourseId) {
        this.bundleCourseId = bundleCourseId;
    }

    @JsonProperty("bundle_course_name")
    public String getBundleCourseName() {
        return bundleCourseName;
    }

    @JsonProperty("bundle_course_name")
    public void setBundleCourseName(String bundleCourseName) {
        this.bundleCourseName = bundleCourseName;
    }

    @JsonProperty("course_slug")
    public String getCourseSlug() {
        return courseSlug;
    }

    @JsonProperty("course_slug")
    public void setCourseSlug(String courseSlug) {
        this.courseSlug = courseSlug;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("bundle_course_validity")
    public Integer getBundleCourseValidity() {
        return bundleCourseValidity;
    }

    @JsonProperty("bundle_course_validity")
    public void setBundleCourseValidity(Integer bundleCourseValidity) {
        this.bundleCourseValidity = bundleCourseValidity;
    }

    @JsonProperty("bundle_course_language_id")
    public Integer getBundleCourseLanguageId() {
        return bundleCourseLanguageId;
    }

    @JsonProperty("bundle_course_language_id")
    public void setBundleCourseLanguageId(Integer bundleCourseLanguageId) {
        this.bundleCourseLanguageId = bundleCourseLanguageId;
    }

    @JsonProperty("bundle_course_language_name")
    public String getBundleCourseLanguageName() {
        return bundleCourseLanguageName;
    }

    @JsonProperty("bundle_course_language_name")
    public void setBundleCourseLanguageName(String bundleCourseLanguageName) {
        this.bundleCourseLanguageName = bundleCourseLanguageName;
    }

    @JsonProperty("bundle_course_price")
    public String getBundleCoursePrice() {
        return bundleCoursePrice;
    }

    @JsonProperty("bundle_course_price")
    public void setBundleCoursePrice(String bundleCoursePrice) {
        this.bundleCoursePrice = bundleCoursePrice;
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
