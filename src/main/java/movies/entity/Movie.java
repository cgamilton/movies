package movies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TESTE")
public class Movie {
	@Id
	@Column(name = "TITLE")
	private String title;
	@Column(name = "MOVIEYEAR")
	private Integer year;
	@Column(name = "STUDIOS")
	private String studios;
	@Column(name = "PRODUCERS")
	private String producers;
	@Column(name = "WINNER")
	private String winner;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

}
