
package pojo.courseView;

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
    "language_id",
    "langauge_name",
    "packages"
})
@Generated("jsonschema2pojo")
public class Package {

    @JsonProperty("language_id")
    private Integer languageId;
    @JsonProperty("langauge_name")
    private String langaugeName;
    @JsonProperty("packages")
    private List<Package__1> packages = null;
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

    @JsonProperty("langauge_name")
    public String getLangaugeName() {
        return langaugeName;
    }

    @JsonProperty("langauge_name")
    public void setLangaugeName(String langaugeName) {
        this.langaugeName = langaugeName;
    }

    @JsonProperty("packages")
    public List<Package__1> getPackages() {
        return packages;
    }

    @JsonProperty("packages")
    public void setPackages(List<Package__1> packages) {
        this.packages = packages;
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
