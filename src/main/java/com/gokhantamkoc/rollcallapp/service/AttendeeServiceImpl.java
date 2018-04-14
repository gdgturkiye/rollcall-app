package com.gokhantamkoc.rollcallapp.service;

import com.gokhantamkoc.rollcallapp.entity.Attendee;
import com.gokhantamkoc.rollcallapp.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    private final long MAX_LONG_VALUE = 9_223_372_036_854_775_807L;

    @Resource
    AttendeeRepository attendeeRepository;

    @Override
    public Attendee create(Attendee attendee) {
        try {
            return attendeeRepository.save(attendee);
        } catch (Exception e) {

            Random random = new Random();
            long id = 1L +(long)(random.nextDouble() * (MAX_LONG_VALUE - 1L));

            attendee.setId(id);
            return this.create(attendee);
        }
    }

    @Override
    public Attendee update(Attendee attendee) {
        Attendee foundAttendee = this.get(attendee.getId());
        if(foundAttendee != null) {
            foundAttendee.setName(attendee.getName());
            foundAttendee.setRsvp(attendee.getRsvp());
            foundAttendee.setAttended(attendee.getAttended());
            foundAttendee.setGender(attendee.getGender());
        }
        return attendeeRepository.save(foundAttendee);
    }

    @Override
    public List<Attendee> list() {
        return attendeeRepository.listAttendees();
    }

    @Override
    public Attendee get(long id) {
        try {
            Attendee foundAttendee = findAttendeeById(id);
            return foundAttendee;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addIfNewAttendee(Attendee attendee) {
        Attendee foundAttendee = this.get(attendee.getId());
        if(foundAttendee == null) {
            attendeeRepository.save(attendee);
        }
    }

    @Override
    public int getAttendance() {
        return attendeeRepository.listAttendedAttendees().size();
    }

    private Attendee findAttendeeById(Long id) throws Exception {
        Optional<Attendee> attendeeResult = attendeeRepository.findById(id);
        return attendeeResult.orElseThrow(() -> new Exception("Attendee not found!"));
    }
}
