package com.ipl.swim_league.swimmer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Duration;

@Entity
@Table(name="swimmer_statistic")
public class Swimmer {
    @Id
    @Column(name = "name", unique = true)
    private String name;
    private String best_event;
    private String best_time;
    private String isl_team;
    private Integer age;
    private String nationality;
    private String gender;

    public Swimmer() {}

    public Swimmer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBest_event() {
        return best_event;
    }

    public void setBest_event(String best_event) {
        this.best_event = best_event;
    }

    public String getBest_time() {
        return best_time;
    }

    public void setBest_time(String best_time) {
        this.best_time = best_time;
    }

    public String getIsl_team() {
        return isl_team;
    }

    public void setIsl_team(String isl_team) {
        this.isl_team = isl_team;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
