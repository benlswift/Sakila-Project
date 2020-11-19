package com.sparta.glowupgirls.sakilaproject.entities;

public class CustomerOrder {
    private Film film;
    private Rental rental;

    public CustomerOrder(Film film, Rental rental) {
        this.film = film;
        this.rental = rental;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
