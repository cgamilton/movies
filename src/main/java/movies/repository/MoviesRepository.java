package movies.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import movies.entity.Movie;

@ApplicationScoped
public class MoviesRepository implements PanacheRepository<Movie>{
	
	public PanacheQuery<Movie> listWinner() {
		return this.find(" WINNER = 'yes'");
	}
}
