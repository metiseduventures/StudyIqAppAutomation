
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
    "language_id",
    "language_name"
})
@Generated("jsonschema2pojo")
public class CourseLanguage {

    @JsonProperty("language_id")
    private Integer languageId;
    @JsonProperty("language_name")
    private String languageName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("language_id")
    public Integer getLanguageId() {
        return languageId;
    }

    @JsonProperty("language_id")
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @JsonProperty("language_name")
    public String getLanguageName() {
        return languageName;
    }

    @JsonProperty("language_name")
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
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
