package com.ipl.swim_league.swimmer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SwimmerService {
    private final SwimmerRepository swimmerRepository;

    @Autowired
    public SwimmerService(SwimmerRepository swimmerRepository){
        this.swimmerRepository = swimmerRepository;
    }

    public List<Swimmer> getSwimmers(){
        return swimmerRepository.findAll();
    }

    public List<Swimmer> getSwimmersFromTeam(String teamName){
        return swimmerRepository.findAll().stream()
                .filter(swimmer -> teamName.equals(swimmer.getIsl_team()))
                .collect(Collectors.toList());
    }

    public List<Swimmer> getSwimmersByName(String searchText){
        return swimmerRepository.findAll().stream()
                .filter(swimmer -> swimmer.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Swimmer> getSwimmerByEvent(String searchText){
        return swimmerRepository.findAll().stream()
                .filter(swimmer ->
                        swimmer.getBest_event().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Swimmer> getSwimmersByNation(String searchText){
        return swimmerRepository.findAll().stream()
                .filter(swimmer -> swimmer.getNationality().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Swimmer> getSwimmerByTeamAndEvent(String team, String event){
        return swimmerRepository.findAll().stream()
                .filter(swimmer -> team.equals(swimmer.getIsl_team()) && event.equals(swimmer.getBest_event()))
                .collect(Collectors.toList());
    }

    public Swimmer addSwimer(Swimmer swimmer){
        swimmerRepository.save(swimmer);
        return swimmer;
    }

    public Swimmer updateSwimmer(Swimmer updatedSwimmer){
        Optional<Swimmer> existingSwimmer = swimmerRepository.findByName(updatedSwimmer.getName());

        if (existingSwimmer.isPresent()){
            Swimmer swimmerToUpdate = existingSwimmer.get();
            swimmerToUpdate.setName(updatedSwimmer.getName());
            swimmerToUpdate.setAge(updatedSwimmer.getAge());
            swimmerToUpdate.setBest_event(updatedSwimmer.getBest_event());
            swimmerToUpdate.setGender(updatedSwimmer.getGender());
            swimmerToUpdate.setBest_time(updatedSwimmer.getBest_time());
            swimmerToUpdate.setIsl_team(updatedSwimmer.getIsl_team());

            swimmerRepository.save(swimmerToUpdate);
            return swimmerToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteSwimmer(String swimmerName){
        swimmerRepository.deleteByName(swimmerName);
    }
}
