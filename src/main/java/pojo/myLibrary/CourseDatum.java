
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
    "course_id",
    "course_title",
    "thumbnail",
    "slug",
    "categories",
    "lanugage_id",
    "reference_count",
    "payment_type",
    "order_course_type",
    "total_emi",
    "total_paid_emi",
    "validity_date",
    "validity_days_left",
    "course_removal_days",
    "validity_msg",
    "free_course"
})
@Generated("jsonschema2pojo")
public class CourseDatum {

    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_title")
    private String courseTitle;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("categories")
    private List<Category> categories = null;
    @JsonProperty("lanugage_id")
    private Integer lanugageId;
    @JsonProperty("reference_count")
    private Integer referenceCount;
    @JsonProperty("payment_type")
    private Integer paymentType;
    @JsonProperty("order_course_type")
    private Integer orderCourseType;
    @JsonProperty("total_emi")
    private Integer totalEmi;
    @JsonProperty("total_paid_emi")
    private Integer totalPaidEmi;
    @JsonProperty("validity_date")
    private String validityDate;
    @JsonProperty("validity_days_left")
    private Integer validityDaysLeft;
    @JsonProperty("course_removal_days")
    private Integer courseRemovalDays;
    @JsonProperty("validity_msg")
    private Object validityMsg;
    @JsonProperty("free_course")
    private Integer freeCourse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("course_id")
    public Integer getCourseId() {
        return courseId;
    }

    @JsonProperty("course_id")
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @JsonProperty("course_title")
    public String getCourseTitle() {
        return courseTitle;
    }

    @JsonProperty("course_title")
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @JsonProperty("lanugage_id")
    public Integer getLanugageId() {
        return lanugageId;
    }

    @JsonProperty("lanugage_id")
    public void setLanugageId(Integer lanugageId) {
        this.lanugageId = lanugageId;
    }

    @JsonProperty("reference_count")
    public Integer getReferenceCount() {
        return referenceCount;
    }

    @JsonProperty("reference_count")
    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }

    @JsonProperty("payment_type")
    public Integer getPaymentType() {
        return paymentType;
    }

    @JsonProperty("payment_type")
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("order_course_type")
    public Integer getOrderCourseType() {
        return orderCourseType;
    }

    @JsonProperty("order_course_type")
    public void setOrderCourseType(Integer orderCourseType) {
        this.orderCourseType = orderCourseType;
    }

    @JsonProperty("total_emi")
    public Integer getTotalEmi() {
        return totalEmi;
    }

    @JsonProperty("total_emi")
    public void setTotalEmi(Integer totalEmi) {
        this.totalEmi = totalEmi;
    }

    @JsonProperty("total_paid_emi")
    public Integer getTotalPaidEmi() {
        return totalPaidEmi;
    }

    @JsonProperty("total_paid_emi")
    public void setTotalPaidEmi(Integer totalPaidEmi) {
        this.totalPaidEmi = totalPaidEmi;
    }

    @JsonProperty("validity_date")
    public String getValidityDate() {
        return validityDate;
    }

    @JsonProperty("validity_date")
    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    @JsonProperty("validity_days_left")
    public Integer getValidityDaysLeft() {
        return validityDaysLeft;
    }

    @JsonProperty("validity_days_left")
    public void setValidityDaysLeft(Integer validityDaysLeft) {
        this.validityDaysLeft = validityDaysLeft;
    }

    @JsonProperty("course_removal_days")
    public Integer getCourseRemovalDays() {
        return courseRemovalDays;
    }

    @JsonProperty("course_removal_days")
    public void setCourseRemovalDays(Integer courseRemovalDays) {
        this.courseRemovalDays = courseRemovalDays;
    }

    @JsonProperty("validity_msg")
    public Object getValidityMsg() {
        return validityMsg;
    }

    @JsonProperty("validity_msg")
    public void setValidityMsg(Object validityMsg) {
        this.validityMsg = validityMsg;
    }

    @JsonProperty("free_course")
    public Integer getFreeCourse() {
        return freeCourse;
    }

    @JsonProperty("free_course")
    public void setFreeCourse(Integer freeCourse) {
        this.freeCourse = freeCourse;
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
