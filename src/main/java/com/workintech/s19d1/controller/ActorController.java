package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")

public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll(){
        List<Actor> actors = actorService.findAll();
        return Converter.actorResponsesConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id){
        Actor actor = actorService.findById(id);
        return Converter.actorResponsesConvert(actor);
    }

    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        for(Movie movie : movies){
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return Converter.actorCreateResponsesConvert(savedActor);

    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable Long id) {
        Actor foundActor = actorService.findById(id);
        actor.setMovies(foundActor.getMovies());
        actor.setId(id);
        actorService.save(actor);
        return Converter.actorResponsesConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable Long id){
        Actor removed = actorService.findById(id);
        actorService.delete(removed);
        return Converter.actorResponsesConvert(removed);
    }
}
