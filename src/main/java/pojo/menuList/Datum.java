
package pojo.menuList;

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
    "menu_id",
    "menu_title",
    "menu_slug",
    "menu_icon"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("menu_id")
    private Integer menuId;
    @JsonProperty("menu_title")
    private String menuTitle;
    @JsonProperty("menu_slug")
    private String menuSlug;
    @JsonProperty("menu_icon")
    private String menuIcon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("menu_id")
    public Integer getMenuId() {
        return menuId;
    }

    @JsonProperty("menu_id")
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @JsonProperty("menu_title")
    public String getMenuTitle() {
        return menuTitle;
    }

    @JsonProperty("menu_title")
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    @JsonProperty("menu_slug")
    public String getMenuSlug() {
        return menuSlug;
    }

    @JsonProperty("menu_slug")
    public void setMenuSlug(String menuSlug) {
        this.menuSlug = menuSlug;
    }

    @JsonProperty("menu_icon")
    public String getMenuIcon() {
        return menuIcon;
    }

    @JsonProperty("menu_icon")
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
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
