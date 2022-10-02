
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
    "no_of_videos",
    "no_of_quiz",
    "lecture",
    "updates"
})
@Generated("jsonschema2pojo")
public class CourseInfo {

    @JsonProperty("no_of_videos")
    private Integer noOfVideos;
    @JsonProperty("no_of_quiz")
    private Integer noOfQuiz;
    @JsonProperty("lecture")
    private Integer lecture;
    @JsonProperty("updates")
    private Integer updates;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("no_of_videos")
    public Integer getNoOfVideos() {
        return noOfVideos;
    }

    @JsonProperty("no_of_videos")
    public void setNoOfVideos(Integer noOfVideos) {
        this.noOfVideos = noOfVideos;
    }

    @JsonProperty("no_of_quiz")
    public Integer getNoOfQuiz() {
        return noOfQuiz;
    }

    @JsonProperty("no_of_quiz")
    public void setNoOfQuiz(Integer noOfQuiz) {
        this.noOfQuiz = noOfQuiz;
    }

    @JsonProperty("lecture")
    public Integer getLecture() {
        return lecture;
    }

    @JsonProperty("lecture")
    public void setLecture(Integer lecture) {
        this.lecture = lecture;
    }

    @JsonProperty("updates")
    public Integer getUpdates() {
        return updates;
    }

    @JsonProperty("updates")
    public void setUpdates(Integer updates) {
        this.updates = updates;
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
