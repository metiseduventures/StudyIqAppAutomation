
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
    "course_id",
    "course_title",
    "thumbnail",
    "supportno",
    "course_stream",
    "course_description",
    "course_description_html",
    "reference_id",
    "reference_count",
    "course_type_id",
    "course_type",
    "meta_title",
    "meta_keyword",
    "meta_description",
    "default_language_id",
    "is_emi_available",
    "is_course_free",
    "course_base_price",
    "package_discount_price_1",
    "package_discount_price_2",
    "discount_price_1_start_date",
    "discount_price_1_end_date",
    "discount_price_2_start_date",
    "discount_price_2_end_date",
    "discount_text"
})
@Generated("jsonschema2pojo")
public class CourseDetail {

    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_title")
    private String courseTitle;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("supportno")
    private String supportno;
    @JsonProperty("course_stream")
    private Integer courseStream;
    @JsonProperty("course_description")
    private Object courseDescription;
    @JsonProperty("course_description_html")
    private String courseDescriptionHtml;
    @JsonProperty("reference_id")
    private Object referenceId;
    @JsonProperty("reference_count")
    private Integer referenceCount;
    @JsonProperty("course_type_id")
    private Integer courseTypeId;
    @JsonProperty("course_type")
    private String courseType;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("meta_keyword")
    private String metaKeyword;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("default_language_id")
    private Integer defaultLanguageId;
    @JsonProperty("is_emi_available")
    private Integer isEmiAvailable;
    @JsonProperty("is_course_free")
    private Integer isCourseFree;
    @JsonProperty("course_base_price")
    private Integer courseBasePrice;
    @JsonProperty("package_discount_price_1")
    private Integer packageDiscountPrice1;
    @JsonProperty("package_discount_price_2")
    private Integer packageDiscountPrice2;
    @JsonProperty("discount_price_1_start_date")
    private Object discountPrice1StartDate;
    @JsonProperty("discount_price_1_end_date")
    private Object discountPrice1EndDate;
    @JsonProperty("discount_price_2_start_date")
    private Object discountPrice2StartDate;
    @JsonProperty("discount_price_2_end_date")
    private Object discountPrice2EndDate;
    @JsonProperty("discount_text")
    private String discountText;
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

    @JsonProperty("supportno")
    public String getSupportno() {
        return supportno;
    }

    @JsonProperty("supportno")
    public void setSupportno(String supportno) {
        this.supportno = supportno;
    }

    @JsonProperty("course_stream")
    public Integer getCourseStream() {
        return courseStream;
    }

    @JsonProperty("course_stream")
    public void setCourseStream(Integer courseStream) {
        this.courseStream = courseStream;
    }

    @JsonProperty("course_description")
    public Object getCourseDescription() {
        return courseDescription;
    }

    @JsonProperty("course_description")
    public void setCourseDescription(Object courseDescription) {
        this.courseDescription = courseDescription;
    }

    @JsonProperty("course_description_html")
    public String getCourseDescriptionHtml() {
        return courseDescriptionHtml;
    }

    @JsonProperty("course_description_html")
    public void setCourseDescriptionHtml(String courseDescriptionHtml) {
        this.courseDescriptionHtml = courseDescriptionHtml;
    }

    @JsonProperty("reference_id")
    public Object getReferenceId() {
        return referenceId;
    }

    @JsonProperty("reference_id")
    public void setReferenceId(Object referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty("reference_count")
    public Integer getReferenceCount() {
        return referenceCount;
    }

    @JsonProperty("reference_count")
    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }

    @JsonProperty("course_type_id")
    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    @JsonProperty("course_type_id")
    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    @JsonProperty("course_type")
    public String getCourseType() {
        return courseType;
    }

    @JsonProperty("course_type")
    public void setCourseType(String courseType) {
        this.courseType = courseType;
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
    public String getMetaDescription() {
        return metaDescription;
    }

    @JsonProperty("meta_description")
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    @JsonProperty("default_language_id")
    public Integer getDefaultLanguageId() {
        return defaultLanguageId;
    }

    @JsonProperty("default_language_id")
    public void setDefaultLanguageId(Integer defaultLanguageId) {
        this.defaultLanguageId = defaultLanguageId;
    }

    @JsonProperty("is_emi_available")
    public Integer getIsEmiAvailable() {
        return isEmiAvailable;
    }

    @JsonProperty("is_emi_available")
    public void setIsEmiAvailable(Integer isEmiAvailable) {
        this.isEmiAvailable = isEmiAvailable;
    }

    @JsonProperty("is_course_free")
    public Integer getIsCourseFree() {
        return isCourseFree;
    }

    @JsonProperty("is_course_free")
    public void setIsCourseFree(Integer isCourseFree) {
        this.isCourseFree = isCourseFree;
    }

    @JsonProperty("course_base_price")
    public Integer getCourseBasePrice() {
        return courseBasePrice;
    }

    @JsonProperty("course_base_price")
    public void setCourseBasePrice(Integer courseBasePrice) {
        this.courseBasePrice = courseBasePrice;
    }

    @JsonProperty("package_discount_price_1")
    public Integer getPackageDiscountPrice1() {
        return packageDiscountPrice1;
    }

    @JsonProperty("package_discount_price_1")
    public void setPackageDiscountPrice1(Integer packageDiscountPrice1) {
        this.packageDiscountPrice1 = packageDiscountPrice1;
    }

    @JsonProperty("package_discount_price_2")
    public Integer getPackageDiscountPrice2() {
        return packageDiscountPrice2;
    }

    @JsonProperty("package_discount_price_2")
    public void setPackageDiscountPrice2(Integer packageDiscountPrice2) {
        this.packageDiscountPrice2 = packageDiscountPrice2;
    }

    @JsonProperty("discount_price_1_start_date")
    public Object getDiscountPrice1StartDate() {
        return discountPrice1StartDate;
    }

    @JsonProperty("discount_price_1_start_date")
    public void setDiscountPrice1StartDate(Object discountPrice1StartDate) {
        this.discountPrice1StartDate = discountPrice1StartDate;
    }

    @JsonProperty("discount_price_1_end_date")
    public Object getDiscountPrice1EndDate() {
        return discountPrice1EndDate;
    }

    @JsonProperty("discount_price_1_end_date")
    public void setDiscountPrice1EndDate(Object discountPrice1EndDate) {
        this.discountPrice1EndDate = discountPrice1EndDate;
    }

    @JsonProperty("discount_price_2_start_date")
    public Object getDiscountPrice2StartDate() {
        return discountPrice2StartDate;
    }

    @JsonProperty("discount_price_2_start_date")
    public void setDiscountPrice2StartDate(Object discountPrice2StartDate) {
        this.discountPrice2StartDate = discountPrice2StartDate;
    }

    @JsonProperty("discount_price_2_end_date")
    public Object getDiscountPrice2EndDate() {
        return discountPrice2EndDate;
    }

    @JsonProperty("discount_price_2_end_date")
    public void setDiscountPrice2EndDate(Object discountPrice2EndDate) {
        this.discountPrice2EndDate = discountPrice2EndDate;
    }

    @JsonProperty("discount_text")
    public String getDiscountText() {
        return discountText;
    }

    @JsonProperty("discount_text")
    public void setDiscountText(String discountText) {
        this.discountText = discountText;
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
