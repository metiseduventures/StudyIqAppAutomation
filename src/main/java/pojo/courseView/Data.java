
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
    "course_languages",
    "course_detail",
    "course_info",
    "exam_covered",
    "authors",
    "demo_urls",
    "features",
    "course_type",
    "packages",
    "bundlecourses",
    "course_faqs",
    "order_history",
    "price_info"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("course_languages")
    private List<CourseLanguage> courseLanguages = null;
    @JsonProperty("course_detail")
    private CourseDetail courseDetail;
    @JsonProperty("course_info")
    private CourseInfo courseInfo;
    @JsonProperty("exam_covered")
    private List<ExamCovered> examCovered = null;
    @JsonProperty("authors")
    private List<Author> authors = null;
    @JsonProperty("demo_urls")
    private List<DemoUrl> demoUrls = null;
    @JsonProperty("features")
    private List<Feature> features = null;
    @JsonProperty("course_type")
    private CourseType courseType;
    @JsonProperty("packages")
    private List<Package> packages = null;
    @JsonProperty("bundlecourses")
    private List<Bundlecourse> bundlecourses = null;
    @JsonProperty("course_faqs")
    private List<CourseFaq> courseFaqs = null;
    @JsonProperty("order_history")
    private List<Object> orderHistory = null;
    @JsonProperty("price_info")
    private String priceInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("course_languages")
    public List<CourseLanguage> getCourseLanguages() {
        return courseLanguages;
    }

    @JsonProperty("course_languages")
    public void setCourseLanguages(List<CourseLanguage> courseLanguages) {
        this.courseLanguages = courseLanguages;
    }

    @JsonProperty("course_detail")
    public CourseDetail getCourseDetail() {
        return courseDetail;
    }

    @JsonProperty("course_detail")
    public void setCourseDetail(CourseDetail courseDetail) {
        this.courseDetail = courseDetail;
    }

    @JsonProperty("course_info")
    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    @JsonProperty("course_info")
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    @JsonProperty("exam_covered")
    public List<ExamCovered> getExamCovered() {
        return examCovered;
    }

    @JsonProperty("exam_covered")
    public void setExamCovered(List<ExamCovered> examCovered) {
        this.examCovered = examCovered;
    }

    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonProperty("demo_urls")
    public List<DemoUrl> getDemoUrls() {
        return demoUrls;
    }

    @JsonProperty("demo_urls")
    public void setDemoUrls(List<DemoUrl> demoUrls) {
        this.demoUrls = demoUrls;
    }

    @JsonProperty("features")
    public List<Feature> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @JsonProperty("course_type")
    public CourseType getCourseType() {
        return courseType;
    }

    @JsonProperty("course_type")
    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    @JsonProperty("packages")
    public List<Package> getPackages() {
        return packages;
    }

    @JsonProperty("packages")
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    @JsonProperty("bundlecourses")
    public List<Bundlecourse> getBundlecourses() {
        return bundlecourses;
    }

    @JsonProperty("bundlecourses")
    public void setBundlecourses(List<Bundlecourse> bundlecourses) {
        this.bundlecourses = bundlecourses;
    }

    @JsonProperty("course_faqs")
    public List<CourseFaq> getCourseFaqs() {
        return courseFaqs;
    }

    @JsonProperty("course_faqs")
    public void setCourseFaqs(List<CourseFaq> courseFaqs) {
        this.courseFaqs = courseFaqs;
    }

    @JsonProperty("order_history")
    public List<Object> getOrderHistory() {
        return orderHistory;
    }

    @JsonProperty("order_history")
    public void setOrderHistory(List<Object> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @JsonProperty("price_info")
    public String getPriceInfo() {
        return priceInfo;
    }

    @JsonProperty("price_info")
    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
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
