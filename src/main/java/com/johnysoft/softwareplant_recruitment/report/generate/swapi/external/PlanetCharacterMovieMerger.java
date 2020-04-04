package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.MoviesProvider.MovieModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.PlanetCharacterCombiner.PlanetCharacterCombination;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SingleSwapiRecord;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

/**
 * merge all movies with people & planet
 */
@FieldDefaults(makeFinal = true, level = PRIVATE)
class PlanetCharacterMovieMerger {
    Map<String, String> moviesByUrl;
    List<PlanetCharacterCombination> planetCharacterCombine;

    PlanetCharacterMovieMerger(List<PlanetCharacterCombination> planetCharacterCombine, List<MovieModel> movies) {
        this.planetCharacterCombine = planetCharacterCombine;
        this.moviesByUrl = groupByUrl(movies);
    }

    private Map<String, String> groupByUrl(List<MovieModel> movies) {
        return movies.stream().collect(toMap(MovieModel::getUrl, MovieModel::getTitle));
    }

    SwapiDataModel merge() {
        SwapiDataModel model = new SwapiDataModel();
        planetCharacterCombine.stream()
                .map(this::singleSwapiRecord)
                .forEach(model::addRecord);
        return model;
    }

    private SingleSwapiRecord singleSwapiRecord(PlanetCharacterCombination personMovieRecord) {
        return new SingleSwapiRecord(personMovieRecord.getMovieId(), moviesByUrl.get(personMovieRecord.getMovieUrl()),
                personMovieRecord.getCharacterId(), personMovieRecord.getCharacterName(),
                personMovieRecord.getPlanetId(), personMovieRecord.getPlanetName());
    }
}
