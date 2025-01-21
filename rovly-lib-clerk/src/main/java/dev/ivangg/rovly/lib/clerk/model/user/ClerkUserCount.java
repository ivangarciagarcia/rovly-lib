package dev.ivangg.rovly.lib.clerk.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClerkUserCount {

    private Integer count;

    public ClerkUserCount() {
    }

    @JsonProperty("total_count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
