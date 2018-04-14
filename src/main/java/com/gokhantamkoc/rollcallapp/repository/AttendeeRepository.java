package com.gokhantamkoc.rollcallapp.repository;

import com.gokhantamkoc.rollcallapp.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    @Query("select att from Attendee att order by att.name")
    List<Attendee> listAttendees();

    @Query("select att from Attendee att where att.attended='Yes' order by att.name")
    List<Attendee> listAttendedAttendees();
}
