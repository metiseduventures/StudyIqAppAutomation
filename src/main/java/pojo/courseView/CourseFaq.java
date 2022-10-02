
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
    "faq_question",
    "faq_answer"
})
@Generated("jsonschema2pojo")
public class CourseFaq {

    @JsonProperty("faq_question")
    private String faqQuestion;
    @JsonProperty("faq_answer")
    private String faqAnswer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("faq_question")
    public String getFaqQuestion() {
        return faqQuestion;
    }

    @JsonProperty("faq_question")
    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    @JsonProperty("faq_answer")
    public String getFaqAnswer() {
        return faqAnswer;
    }

    @JsonProperty("faq_answer")
    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
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
