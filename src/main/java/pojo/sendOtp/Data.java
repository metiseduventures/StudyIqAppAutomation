
package pojo.sendOtp;

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
    "action",
    "user_id",
    "first_name",
    "last_name",
    "email",
    "mobile_number",
    "api_token",
    "user_type",
    "otp",
    "cart_count"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("action")
    private Integer action;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("first_name")
    private Object firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("mobile_number")
    private String mobileNumber;
    @JsonProperty("api_token")
    private Object apiToken;
    @JsonProperty("user_type")
    private Integer userType;
    @JsonProperty("otp")
    private Integer otp;
    @JsonProperty("cart_count")
    private Integer cartCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("action")
    public Integer getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(Integer action) {
        this.action = action;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("first_name")
    public Object getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    @JsonProperty("mobile_number")
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonProperty("api_token")
    public Object getApiToken() {
        return apiToken;
    }

    @JsonProperty("api_token")
    public void setApiToken(Object apiToken) {
        this.apiToken = apiToken;
    }

    @JsonProperty("user_type")
    public Integer getUserType() {
        return userType;
    }

    @JsonProperty("user_type")
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @JsonProperty("otp")
    public Integer getOtp() {
        return otp;
    }

    @JsonProperty("otp")
    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    @JsonProperty("cart_count")
    public Integer getCartCount() {
        return cartCount;
    }

    @JsonProperty("cart_count")
    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
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
