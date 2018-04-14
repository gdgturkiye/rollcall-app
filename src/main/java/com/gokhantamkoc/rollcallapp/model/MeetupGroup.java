package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupGroup {

    private long id;

    private String urlname;

    private String name;

    private String status;

    private String who;

    private long members;

    @JsonProperty(value = "join_mode")
    private String joinMode;

    @JsonProperty(value = "localized_location")
    private String localizedLocation;

    @JsonProperty(value = "group_photo")
    private MeetupGroupPhoto meetupGroupPhoto;

    @JsonProperty(value = "key_photo")
    private MeetupKeyPhoto meetupKeyPhoto;

    @JsonProperty(value = "photo_gradient")
    private MeetupPhotoGradient meetupPhotoGradient;
}
