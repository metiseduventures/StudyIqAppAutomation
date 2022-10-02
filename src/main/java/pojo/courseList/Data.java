
package pojo.courseList;

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
    "id",
    "section_name",
    "meta_title",
    "meta_keyword",
    "meta_description",
    "courses"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("section_name")
    private String sectionName;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("meta_keyword")
    private String metaKeyword;
    @JsonProperty("meta_description")
    private Object metaDescription;
    @JsonProperty("courses")
    private List<Course> courses = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    @JsonProperty("section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @JsonProperty("meta_title")
    public String getMetaTitle() {
        return metaTitle;
    }

    @JsonProperty("meta_title")
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    @JsonProperty("meta_keyword")
    public String getMetaKeyword() {
        return metaKeyword;
    }

    @JsonProperty("meta_keyword")
    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    @JsonProperty("meta_description")
    public Object getMetaDescription() {
        return metaDescription;
    }

    @JsonProperty("meta_description")
    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    @JsonProperty("courses")
    public List<Course> getCourses() {
        return courses;
    }

    @JsonProperty("courses")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
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
