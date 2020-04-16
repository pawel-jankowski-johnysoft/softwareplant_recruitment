package com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2.FilmsProvider.Film;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2.MovieCharacterProvider.MovieCharacter;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.v2.PlanetProvider.Planet;
import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

class SwapiDataCollector {

    public SwapiDataModel collect(List<Film> films, List<Planet> planets, List<MovieCharacter> movieCharacters) {
        SwapiDataModel model = new SwapiDataModel();
        singleSwapiRecords(films, planets, movieCharacters).forEach(model::addRecord);

        return model;
    }

    private Stream<SingleSwapiRecord> singleSwapiRecords(List<Film> films, List<Planet> planets, List<MovieCharacter> movieCharacters) {
        return prepareMovieEvents(films, planets, movieCharacters)
                .filter(MovieEvent::occurred)
                .map(movieEvent -> new SingleSwapiRecord(movieEvent.film.getFilmId(), movieEvent.film.getFilmTitle(),
                        movieEvent.movieCharacter.getCharacterId(), movieEvent.movieCharacter.getName(),
                        movieEvent.planet.getPlanetId(), movieEvent.planet.getPlanetName()));
    }

    private Stream<MovieEvent> prepareMovieEvents(List<Film> films, List<Planet> planets, List<MovieCharacter> movieCharacters) {
        return films.stream().flatMap(film ->
                planets.stream().flatMap(planet ->
                        movieCharacters.stream().map(movieCharacter -> new MovieEvent(movieCharacter, planet, film))
                ));
    }

    @Value
    private class MovieEvent {
        MovieCharacter movieCharacter;
        Planet planet;
        Film film;

        private boolean occurred() {
            return film.actionIsOnPlanet(planet) && film.characterPlays(movieCharacter);
        }
    }
}
