package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupPhotoGradient {

    private long id;

    @JsonProperty(value = "composite_color")
    private String compositeColor;

    @JsonProperty(value = "dark_color")
    private String darkColor;

    @JsonProperty(value = "light_color")
    private String lightColor;
}
