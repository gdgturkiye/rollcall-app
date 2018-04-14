package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MeetupEvent {

    private long id;

    private String name;

    @JsonProperty(value = "no_rsvp_count")
    private int numberRsvpCount;

    private long time;

    @JsonProperty(value = "utc_offset")
    private long utcOffset;

    @JsonProperty(value = "waitlist_count")
    private int waitlistCount;

    @JsonProperty(value = "yes_rsvp_count")
    private int yesRsvpCount;
}
