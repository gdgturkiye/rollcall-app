package com.gokhantamkoc.rollcallapp.service;

import com.gokhantamkoc.rollcallapp.entity.Attendee;

import java.util.List;

public interface AttendeeService {

    Attendee create(Attendee attendee);
    Attendee update(Attendee attendee);
    List<Attendee> list();
    Attendee get(long id);
    void addIfNewAttendee(Attendee attendee);
    int getAttendance();
}
