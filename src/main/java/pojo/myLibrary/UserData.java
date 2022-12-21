
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
    "id",
    "first_name",
    "last_name",
    "email",
    "paid",
    "api_token",
    "user_type",
    "paid1",
    "state",
    "city",
    "address",
    "pincode",
    "user_feed_language",
    "cart_count",
    "orders",
    "user_follow_course"
})
@Generated("jsonschema2pojo")
public class UserData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("paid")
    private Integer paid;
    @JsonProperty("api_token")
    private Object apiToken;
    @JsonProperty("user_type")
    private Integer userType;
    @JsonProperty("paid1")
    private Integer paid1;
    @JsonProperty("state")
    private String state;
    @JsonProperty("city")
    private String city;
    @JsonProperty("address")
    private String address;
    @JsonProperty("pincode")
    private String pincode;
    @JsonProperty("user_feed_language")
    private String userFeedLanguage;
    @JsonProperty("cart_count")
    private Integer cartCount;
    @JsonProperty("orders")
    private List<Object> orders = null;
    @JsonProperty("user_follow_course")
    private List<Object> userFollowCourse = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
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

    @JsonProperty("paid")
    public Integer getPaid() {
        return paid;
    }

    @JsonProperty("paid")
    public void setPaid(Integer paid) {
        this.paid = paid;
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

    @JsonProperty("paid1")
    public Integer getPaid1() {
        return paid1;
    }

    @JsonProperty("paid1")
    public void setPaid1(Integer paid1) {
        this.paid1 = paid1;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("pincode")
    public String getPincode() {
        return pincode;
    }

    @JsonProperty("pincode")
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @JsonProperty("user_feed_language")
    public String getUserFeedLanguage() {
        return userFeedLanguage;
    }

    @JsonProperty("user_feed_language")
    public void setUserFeedLanguage(String userFeedLanguage) {
        this.userFeedLanguage = userFeedLanguage;
    }

    @JsonProperty("cart_count")
    public Integer getCartCount() {
        return cartCount;
    }

    @JsonProperty("cart_count")
    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    @JsonProperty("orders")
    public List<Object> getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(List<Object> orders) {
        this.orders = orders;
    }

    @JsonProperty("user_follow_course")
    public List<Object> getUserFollowCourse() {
        return userFollowCourse;
    }

    @JsonProperty("user_follow_course")
    public void setUserFollowCourse(List<Object> userFollowCourse) {
        this.userFollowCourse = userFollowCourse;
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
