package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupMemberPhoto {

    @JsonProperty(value = "base_url")
    private String baseUrl;

    @JsonProperty(value = "highres_link")
    private String highresLink;

    private long id;

    @JsonProperty(value = "photo_link")
    private String photoLink;

    @JsonProperty(value = "thumb_link")
    private String thumbLink;

    private String type;
}
