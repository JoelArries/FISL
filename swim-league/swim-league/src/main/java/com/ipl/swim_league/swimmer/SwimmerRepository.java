package com.ipl.swim_league.swimmer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, String> {

    void deleteByName(String swimmerName);

    Optional<Swimmer> findByName(String name);
}
