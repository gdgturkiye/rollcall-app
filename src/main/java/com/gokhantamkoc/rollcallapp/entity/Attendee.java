package com.gokhantamkoc.rollcallapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="attendee")
public class Attendee {

    @Id
    private long id;

    @NotNull
    private String name;

    @NotEmpty
    private String rsvp;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String attended;
}
