package movies.resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import movies.repository.MoviesRepository;
import movies.service.MovieDTO;
import movies.service.MoviesService;

@Path("/movies")
@RegisterRestClient
public class MoviesResource {

	@Inject
	MoviesService service;
	
	@Inject 
	MoviesRepository repo;

	@GET
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public MovieDTO listAll() {
		return service.getWinner();
	}
}
