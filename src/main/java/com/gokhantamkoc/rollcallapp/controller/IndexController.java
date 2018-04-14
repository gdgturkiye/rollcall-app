package com.gokhantamkoc.rollcallapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gokhantamkoc.rollcallapp.entity.Attendee;
import com.gokhantamkoc.rollcallapp.model.MeetupRSVP;
import com.gokhantamkoc.rollcallapp.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class IndexController {

    private final long MAX_LONG_VALUE = 9_223_372_036_854_775_807L;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AttendeeService attendeeService;

    @Value("${meetup.baseurl}")
    String meetupBaseUrl;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        getRSVPsfromMeetup();

        modelAndView.addObject("numberOfAttendees", attendeeService.list().size());
        modelAndView.addObject("attendance", attendeeService.getAttendance());
        modelAndView.addObject("attendees", attendeeService.list());

        return modelAndView;
    }

    @RequestMapping(path = "/attendee/attend/{id}", method = RequestMethod.GET)
    public String markAttendeeAsAttended(@PathVariable("id") long memberId) {

        getRSVPsfromMeetup();

        Attendee attendee = attendeeService.get(memberId);
        attendee.setAttended("Yes");
        attendeeService.update(attendee);

        return "redirect:/";
    }

    @RequestMapping(path = "/attendee/unattend/{id}", method = RequestMethod.GET)
    public String markAttendeeAsUnattended(@PathVariable("id") long memberId) {
        getRSVPsfromMeetup();

        Attendee attendee = attendeeService.get(memberId);
        attendee.setAttended("No");
        attendeeService.update(attendee);

        return "redirect:/";
    }

    @RequestMapping(path = "/attendee/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editAttendee(@PathVariable("id") long memberId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("attendee-edit");

        Attendee attendee = attendeeService.get(memberId);
        modelAndView.addObject("attendee", attendee);

        return modelAndView;
    }

    @RequestMapping(path = "/attendee/new", method = RequestMethod.GET)
    public ModelAndView createNewAttendee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("attendee-new");

        Attendee attendee = new Attendee();

        Random random = new Random();
        long id = 1L +(long)(random.nextDouble() * (MAX_LONG_VALUE - 1L));
        attendee.setId(id);

        modelAndView.addObject("attendee", attendee);

        return modelAndView;
    }

    @RequestMapping(path = "/attendee/new", method = RequestMethod.POST)
    public String createAttendee(Attendee attendee) {
        attendeeService.create(attendee);
        return "redirect:/";
    }

    @RequestMapping(path = "/attendee/save", method = RequestMethod.POST)
    public String saveAttendee(Attendee attendee) {
        attendeeService.update(attendee);
        return "redirect:/";
    }

    private void getRSVPsfromMeetup() {
        String url = meetupBaseUrl + "/GDGAnkara/events/247383362/rsvps?photo-host=public&sig_id=223368809&sig=3d34a6f4eb4693518aefeb41642bdcaeef4c554b";
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        try {
            ArrayList<MeetupRSVP> rsvps = objectMapper.readValue(responseEntity.getBody().toString(), new TypeReference<ArrayList<MeetupRSVP>>() {});
            for(MeetupRSVP meetupRSVP : rsvps) {
                Attendee attendee = new Attendee();
                attendee.setId(meetupRSVP.getMember().getId());
                attendee.setName(meetupRSVP.getMember().getName());
                attendee.setRsvp(meetupRSVP.getResponse());
                attendee.setAttended("No");
                attendee.setGender("Female");
                attendeeService.addIfNewAttendee(attendee);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
