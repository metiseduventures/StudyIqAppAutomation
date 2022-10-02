
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
    "video_url",
    "thumbnail",
    "s3_enabled",
    "s3_url"
})
@Generated("jsonschema2pojo")
public class DemoVideo {

    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("s3_enabled")
    private Integer s3Enabled;
    @JsonProperty("s3_url")
    private String s3Url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("video_url")
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
