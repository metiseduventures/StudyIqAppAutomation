
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
    "package_id",
    "package_title",
    "package_name",
    "availability_id",
    "is_course_free",
    "package_validity",
    "is_emi_available",
    "is_package_best_selling",
    "package_base_price",
    "package_discount_price_1",
    "package_discount_price_2",
    "discount_price_1_start_date",
    "discount_price_1_end_date",
    "discount_price_2_start_date",
    "discount_price_2_end_date",
    "features"
})
@Generated("jsonschema2pojo")
public class Package__1 {

    @JsonProperty("package_id")
    private Integer packageId;
    @JsonProperty("package_title")
    private String packageTitle;
    @JsonProperty("package_name")
    private String packageName;
    @JsonProperty("availability_id")
    private Integer availabilityId;
    @JsonProperty("is_course_free")
    private String isCourseFree;
    @JsonProperty("package_validity")
    private Integer packageValidity;
    @JsonProperty("is_emi_available")
    private Integer isEmiAvailable;
    @JsonProperty("is_package_best_selling")
    private Integer isPackageBestSelling;
    @JsonProperty("package_base_price")
    private Integer packageBasePrice;
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
    @JsonProperty("features")
    private List<Feature__1> features = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("package_id")
    public Integer getPackageId() {
        return packageId;
    }

    @JsonProperty("package_id")
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("package_title")
    public String getPackageTitle() {
        return packageTitle;
    }

    @JsonProperty("package_title")
    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    @JsonProperty("package_name")
    public String getPackageName() {
        return packageName;
    }

    @JsonProperty("package_name")
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @JsonProperty("availability_id")
    public Integer getAvailabilityId() {
        return availabilityId;
    }

    @JsonProperty("availability_id")
    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    @JsonProperty("is_course_free")
    public String getIsCourseFree() {
        return isCourseFree;
    }

    @JsonProperty("is_course_free")
    public void setIsCourseFree(String isCourseFree) {
        this.isCourseFree = isCourseFree;
    }

    @JsonProperty("package_validity")
    public Integer getPackageValidity() {
        return packageValidity;
    }

    @JsonProperty("package_validity")
    public void setPackageValidity(Integer packageValidity) {
        this.packageValidity = packageValidity;
    }

    @JsonProperty("is_emi_available")
    public Integer getIsEmiAvailable() {
        return isEmiAvailable;
    }

    @JsonProperty("is_emi_available")
    public void setIsEmiAvailable(Integer isEmiAvailable) {
        this.isEmiAvailable = isEmiAvailable;
    }

    @JsonProperty("is_package_best_selling")
    public Integer getIsPackageBestSelling() {
        return isPackageBestSelling;
    }

    @JsonProperty("is_package_best_selling")
    public void setIsPackageBestSelling(Integer isPackageBestSelling) {
        this.isPackageBestSelling = isPackageBestSelling;
    }

    @JsonProperty("package_base_price")
    public Integer getPackageBasePrice() {
        return packageBasePrice;
    }

    @JsonProperty("package_base_price")
    public void setPackageBasePrice(Integer packageBasePrice) {
        this.packageBasePrice = packageBasePrice;
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

    @JsonProperty("features")
    public List<Feature__1> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(List<Feature__1> features) {
        this.features = features;
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
