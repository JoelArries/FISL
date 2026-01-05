package com.ipl.swim_league.swimmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "swimmer")
public class SwimmerController {
    private final SwimmerService swimmerService;

    @Autowired
    public SwimmerController(SwimmerService swimmerService){
        this.swimmerService = swimmerService;
    }

    @GetMapping
    public List<Swimmer> getSwimmers(
        @RequestParam(required = false) String team,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String bestEvent,
        @RequestParam(required = false) String nation){
        if (team != null && bestEvent != null){
            return swimmerService.getSwimmerByTeamAndEvent(team, bestEvent);
        }
        else if (team != null){
            return swimmerService.getSwimmersFromTeam(team);
        }
        else if (name != null){
            return swimmerService.getSwimmersByName(name);
        }
        else if (bestEvent != null){
            return swimmerService.getSwimmerByEvent(bestEvent);
        }
        else if (nation != null){
            return swimmerService.getSwimmersByNation(nation);
        }
        else{
            return swimmerService.getSwimmers();
        }
    }

    @PostMapping
    public ResponseEntity<Swimmer> addSwimmer(@RequestBody Swimmer swimmer){
        Swimmer createdSwimmer= swimmerService.addSwimer(swimmer);
        return new ResponseEntity<>(createdSwimmer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Swimmer> updateSwimmer(@RequestBody Swimmer swimmer){
        Swimmer resultSwimmer = swimmerService.updateSwimmer(swimmer);
        if (resultSwimmer != null){
            return new ResponseEntity<>(resultSwimmer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{swimmerName}")
    public ResponseEntity<String> deleteSwimmer(@PathVariable String swimmerName){
        swimmerService.deleteSwimmer(swimmerName);
        return new ResponseEntity<>("Swimmer deleted successfully!", HttpStatus.OK);
    }
}
