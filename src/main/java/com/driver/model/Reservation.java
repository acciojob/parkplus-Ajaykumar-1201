package com.driver.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberOfHours;

    @ManyToOne
    @JoinColumn
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Spot spot;

    @JsonIgnore
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment payment;

    public Reservation() {
    }

    public Reservation(int id, int numberOfHours) {
        this.id = id;
        this.numberOfHours = numberOfHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }
}
