package movies.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import movies.entity.Movie;
import movies.repository.MoviesRepository;

@QuarkusTest
@TestHTTPEndpoint(MoviesResource.class)
class MoviesResourceTest {

	@Inject
	MoviesRepository repository;

	@Test
	public void testDataEmptyEndpoint() {
		given().when()
		.get("/")
		.then().statusCode(200)
		.body("min", hasSize(0));
	}

	@Test
	public void testDataEndpoint() {
		persistData();
		given().when()
		.get("/")
		.then().statusCode(200)
		.body("min[0].producer", is("Amilton"))
		.body("min[0].interval", is(10))
		.body("min[0].previousWin", is(1990))
		.body("min[0].followingWin", is(2000));
	}
	
	@Test
	public void testDataEndpointMaxAndMin() {
		persistData();
		persistData2();
		given().when()
		.get("/")
		.then().statusCode(200)
		.body("min[0].producer", is("Alexandre"))
		.body("min[0].interval", is(1))
		.body("min[0].previousWin", is(1995))
		.body("min[0].followingWin", is(1996))
		.body("max[0].producer", is("Amilton"))
		.body("max[0].interval", is(10))
		.body("max[0].previousWin", is(1990))
		.body("max[0].followingWin", is(2000));
	}
	
	@Test
	public void testDataEndpoint2MinWinners() {
		persistData();
		persistData2();
		persistData3();
		given().when()
		.get("/")
		.then().statusCode(200)
		.body("min[0].producer", is("Alexandre"))
		.body("min[0].interval", is(1))
		.body("min[0].previousWin", is(1995))
		.body("min[0].followingWin", is(1996))
		.body("min[1].producer", is("Paul"))
		.body("min[1].interval", is(1))
		.body("min[1].previousWin", is(1983))
		.body("min[1].followingWin", is(1984))
		.body("max[0].producer", is("Amilton"))
		.body("max[0].interval", is(10))
		.body("max[0].previousWin", is(1990))
		.body("max[0].followingWin", is(2000));
	}
	
	@Test
	public void testDataEndpoint2MinWinners2MaxWinners() {
		persistData();
		persistData2();
		persistData3();
		persistData4();
		given().when()
		.get("/")
		.then().statusCode(200)
		.body("min[0].producer", is("Alexandre"))
		.body("min[0].interval", is(1))
		.body("min[0].previousWin", is(1995))
		.body("min[0].followingWin", is(1996))
		.body("min[1].producer", is("Paul"))
		.body("min[1].interval", is(1))
		.body("min[1].previousWin", is(1983))
		.body("min[1].followingWin", is(1984))
		.body("max[0].producer", is("John"))
		.body("max[0].interval", is(10))
		.body("max[0].previousWin", is(1972))
		.body("max[0].followingWin", is(1982))
		.body("max[1].producer", is("Amilton"))
		.body("max[1].interval", is(10))
		.body("max[1].previousWin", is(1990))
		.body("max[1].followingWin", is(2000));
	}
	
	@Transactional
	void persistData() {
		var movie = new Movie();
		movie.setProducers("Amilton");
		movie.setYear(1990);
		movie.setWinner("yes");
		movie.setTitle("The movie 1");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
		movie = new Movie();
		movie.setProducers("Amilton");
		movie.setYear(2000);
		movie.setWinner("yes");
		movie.setTitle("The movie 2");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
	}
	
	@Transactional
	void persistData2() {
		var movie = new Movie();
		movie.setProducers("Alexandre");
		movie.setYear(1995);
		movie.setWinner("yes");
		movie.setTitle("The best movie 1");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
		movie = new Movie();
		movie.setProducers("Alexandre");
		movie.setYear(1996);
		movie.setWinner("yes");
		movie.setTitle("The best movie 2");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
	}
	@Transactional
	void persistData3() {
		var movie = new Movie();
		movie.setProducers("Paul");
		movie.setYear(1983);
		movie.setWinner("yes");
		movie.setTitle("Paul 1");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
		movie = new Movie();
		movie.setProducers("Paul");
		movie.setYear(1984);
		movie.setWinner("yes");
		movie.setTitle("Paul 2");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
	}
	
	
	@Transactional
	void persistData4() {
		var movie = new Movie();
		movie.setProducers("John");
		movie.setYear(1972);
		movie.setWinner("yes");
		movie.setTitle("Bugs 1");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
		movie = new Movie();
		movie.setProducers("John");
		movie.setYear(1982);
		movie.setWinner("yes");
		movie.setTitle("Bugs 2");
		movie.setStudios("BNU Studios");

		repository.persist(movie);
	}
	
	@BeforeEach
	@Transactional
	void dropData() {
		repository.deleteAll();
	}
}
