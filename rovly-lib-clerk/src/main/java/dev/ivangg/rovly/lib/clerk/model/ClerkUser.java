package dev.ivangg.rovly.lib.clerk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClerkUser {
    private String id;
    private String name;

    public ClerkUser() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("first_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
