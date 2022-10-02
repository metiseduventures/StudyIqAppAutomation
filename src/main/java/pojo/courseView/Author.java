
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
    "id",
    "author_name",
    "author_description",
    "author_designation",
    "author_status",
    "profile_pic_url"
})
@Generated("jsonschema2pojo")
public class Author {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_description")
    private String authorDescription;
    @JsonProperty("author_designation")
    private String authorDesignation;
    @JsonProperty("author_status")
    private Integer authorStatus;
    @JsonProperty("profile_pic_url")
    private String profilePicUrl;
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

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_description")
    public String getAuthorDescription() {
        return authorDescription;
    }

    @JsonProperty("author_description")
    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    @JsonProperty("author_designation")
    public String getAuthorDesignation() {
        return authorDesignation;
    }

    @JsonProperty("author_designation")
    public void setAuthorDesignation(String authorDesignation) {
        this.authorDesignation = authorDesignation;
    }

    @JsonProperty("author_status")
    public Integer getAuthorStatus() {
        return authorStatus;
    }

    @JsonProperty("author_status")
    public void setAuthorStatus(Integer authorStatus) {
        this.authorStatus = authorStatus;
    }

    @JsonProperty("profile_pic_url")
    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    @JsonProperty("profile_pic_url")
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
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
