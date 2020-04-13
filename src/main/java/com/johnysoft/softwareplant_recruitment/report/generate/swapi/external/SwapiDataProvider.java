package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import com.johnysoft.softwareplant_recruitment.report.generate.swapi.SwapiSearchParams;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.MoviesProvider.MovieModel;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.external.PlanetCharacterCombiner.PlanetCharacterCombination;
import com.johnysoft.softwareplant_recruitment.report.generate.swapi.model.SwapiDataModel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;
import static reactor.core.publisher.Mono.just;
import static reactor.core.publisher.Mono.zip;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SwapiDataProvider {

    PlanetsProvider planetsProvider;
    CharactersProvider characterProvider;
    MoviesProvider moviesProvider;

    public Mono<SwapiDataModel> getSwapiData(SwapiSearchParams swapiSearchParams) {
        return zip(
                planetsProvider.planetsMatched(swapiSearchParams.getPlanetName()),
                characterProvider.charactersMatched(swapiSearchParams.getCharacterPhrase())
        )
                .map(it -> new PlanetCharacterCombiner(it.getT1(), it.getT2()).combine())
                .flatMap(this::zipWithMovies)
                .map(it -> new PlanetCharacterMovieMerger(it.getT1(), it.getT2()).merge());
    }

    private Mono<Tuple2<List<PlanetCharacterCombination>, List<MovieModel>>> zipWithMovies(
            List<PlanetCharacterCombination> planetCharacterCombinations) {

        final List<String> moviesUrls = planetCharacterCombinations.stream()
                .map(PlanetCharacterCombination::getMovieUrl)
                .collect(toUnmodifiableList());

        return zip(just(planetCharacterCombinations), moviesProvider.moviesForIds(moviesUrls));
    }
}
