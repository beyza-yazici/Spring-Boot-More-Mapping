package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<ActorResponse> actorResponsesConvert (List<Actor> actors){
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor : actors){
            actorResponses.add(actorResponsesConvert(actor));
        }
        return actorResponses;
    }

    public static ActorResponse actorResponsesConvert (Actor actor){
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), null);
    }

    public static ActorResponse actorCreateResponsesConvert(Actor savedActor) {
        return new ActorResponse(savedActor.getId(), savedActor.getFirstName(), savedActor.getLastName(), savedActor.getBirthDate(), savedActor.getMovies());
    }
}
