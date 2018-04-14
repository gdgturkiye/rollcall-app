package com.gokhantamkoc.rollcallapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetupVenue {

    @JsonProperty(value = "address_1")
    private String address1;

    @JsonProperty(value = "address_2")
    private String address2;

    @JsonProperty(value = "address_3")
    private String address3;

    private String city;

    private String country;

    private long id;

    private double lat;

    @JsonProperty(value = "localized_country_name")
    private String localizedCountryName;

    private double lon;

    private String name;

    private String phone;

    private boolean repinned;

    private String state;

    private String zip;
}
