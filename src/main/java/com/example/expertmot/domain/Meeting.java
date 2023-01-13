package com.example.expertmot.domain;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // @Column(name = "user_id", nullable = false)
    //private Long user_id;

    @Column(name = "date", nullable = true, length = Integer.MAX_VALUE)
    private Date date;

    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    @Column(name = "street", nullable = false, length = Integer.MAX_VALUE)
    private String street;
    @Column(name = "street_number", nullable = false, length = Integer.MAX_VALUE)
    private String streetNumber;

    @Column(name = "house_number")
    private Integer houseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //public Long getUserId() {return user_id;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {return streetNumber;}

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
