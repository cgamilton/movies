package movies.service;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
	private List<MovieData> min = new ArrayList<>();
	private List<MovieData> max = new ArrayList<>();

	public List<MovieData> getMax() {
		return max;
	}

	public void setMax(List<MovieData> max) {
		this.max = max;
	}

	public List<MovieData> getMin() {
		return min;
	}

	public void setMin(List<MovieData> min) {
		this.min = min;
	}

}
