
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
    "feature_id",
    "feature_name",
    "feature_descp"
})
@Generated("jsonschema2pojo")
public class Feature {

    @JsonProperty("feature_id")
    private Integer featureId;
    @JsonProperty("feature_name")
    private String featureName;
    @JsonProperty("feature_descp")
    private String featureDescp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feature_id")
    public Integer getFeatureId() {
        return featureId;
    }

    @JsonProperty("feature_id")
    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @JsonProperty("feature_name")
    public String getFeatureName() {
        return featureName;
    }

    @JsonProperty("feature_name")
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @JsonProperty("feature_descp")
    public String getFeatureDescp() {
        return featureDescp;
    }

    @JsonProperty("feature_descp")
    public void setFeatureDescp(String featureDescp) {
        this.featureDescp = featureDescp;
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
