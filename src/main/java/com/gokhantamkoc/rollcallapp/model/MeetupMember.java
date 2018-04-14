package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupMember {

    private long id;

    private String bio;

    @JsonProperty(value = "event_context")
    private MeetupEventContext eventContext;

    private String name;

    private MeetupMemberPhoto photo;

    private String role;

    private String title;

    @JsonIgnore
    private String rsvp;

    @JsonIgnore
    private String gender;

    @JsonIgnore
    private String attended;
}
