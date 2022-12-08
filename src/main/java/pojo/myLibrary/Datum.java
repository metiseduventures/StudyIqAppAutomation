
package pojo.myLibrary;

import java.util.HashMap;
import java.util.List;
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
    "course_type_id",
    "course_type_name",
    "course_display_name",
    "course_data"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("course_type_id")
    private Integer courseTypeId;
    @JsonProperty("course_type_name")
    private String courseTypeName;
    @JsonProperty("course_display_name")
    private String courseDisplayName;
    @JsonProperty("course_data")
    private List<CourseDatum> courseData = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("course_type_id")
    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    @JsonProperty("course_type_id")
    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    @JsonProperty("course_type_name")
    public String getCourseTypeName() {
        return courseTypeName;
    }

    @JsonProperty("course_type_name")
    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    @JsonProperty("course_display_name")
    public String getCourseDisplayName() {
        return courseDisplayName;
    }

    @JsonProperty("course_display_name")
    public void setCourseDisplayName(String courseDisplayName) {
        this.courseDisplayName = courseDisplayName;
    }

    @JsonProperty("course_data")
    public List<CourseDatum> getCourseData() {
        return courseData;
    }

    @JsonProperty("course_data")
    public void setCourseData(List<CourseDatum> courseData) {
        this.courseData = courseData;
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
