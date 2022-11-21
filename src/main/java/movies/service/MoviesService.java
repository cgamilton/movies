package movies.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import movies.entity.Movie;
import movies.repository.MoviesRepository;

@ApplicationScoped
public class MoviesService {
	
	@Inject
	MoviesRepository repository;
	
	public MovieDTO getWinner() {
		
		var movieDTO = new MovieDTO();
		var qry = repository.listWinner();
		
		//collect to map producers that appears more than one time 
		var map = qry.list()
				.stream()
				.collect(Collectors.groupingBy(Movie::getProducers))
				.entrySet()
				.stream()
				.filter(v -> v.getValue().size() > 1)
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		// sort each entry value by year 
		map.forEach((k, v) -> {
			Collections.sort(v, Comparator.comparing(Movie::getYear).reversed());
		});
		
		
		
		var list = new ArrayList<MovieData>();
		map.forEach((k, v) -> {
			var movieData = new MovieData();
			movieData.setProducer(k);
			movieData.setInterval(calcInterval(v));
			movieData.setFollowingWin(v.get(0).getYear().longValue());
			movieData.setPreviousWin(v.get(1).getYear().longValue());
			list.add(movieData);
		});
		
		list.sort(Comparator.comparing(MovieData::getInterval));
		var mapMovieData = list.stream()
							.collect(Collectors.groupingBy(MovieData::getInterval, LinkedHashMap::new, Collectors.toList()));
		for (Map.Entry<Long, List<MovieData>> e : mapMovieData.entrySet()) {
			movieDTO.setMin(e.getValue());
			break;
		}
		if(mapMovieData.size() > 1) {
			LinkedHashMap<Long,List<MovieData>> mapReversed = mapMovieData.entrySet()
										.stream()
										.sorted((Map.Entry.comparingByKey(Comparator.reverseOrder())))
										.collect(Collectors.toMap(e1 -> e1.getKey(), e2 -> e2.getValue(), (k, v) -> k,
												LinkedHashMap::new));
			mapReversed.forEach((k,v) -> System.out.println(k));
			for (Map.Entry<Long, List<MovieData>> e : mapReversed.entrySet()) {
				movieDTO.setMax(e.getValue());
				break;
			}
		}
		
		
		return movieDTO;
	}
	
	private Long calcInterval(List<Movie> movies) {
		try {
			return movies.get(0).getYear().longValue() - movies.get(1).getYear().longValue();
		}catch (Exception e) {
			throw new RuntimeException("The list must have at least two objects.");
		}
	}
}
