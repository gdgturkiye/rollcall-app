package com.gokhantamkoc.rollcallapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupRSVP {

    private long created;

    private long updated;

    private String response;

    private long guests;

    private MeetupEvent event;

    private MeetupGroup group;

    private MeetupMember member;

    private MeetupVenue venue;
}
