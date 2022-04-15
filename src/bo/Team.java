package bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "team")

public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer teamId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.team")
	@Fetch(FetchMode.JOIN)
	Set<TeamSeason> seasons = new HashSet<TeamSeason>();
	
	
	@Column
	String name;
	@Column
	String league;
	@Column
	Integer yearFounded; 
	@Column
	Integer yearLast;
	

	// utility function
	public TeamSeason getTeamSeason(Integer year) {
		for (TeamSeason ts : seasons) {
			if (ts.getYear().equals(year)) return ts;
		}
		return null;
	}
	//fix this^^


	public void addSeason(TeamSeason s) {
		seasons.add(s);
	}

	public Set<TeamSeason> getSeasons() {
		return seasons;
	}
	
	
	public void setSeasons(Set<TeamSeason> seasons) {
		this.seasons = seasons;
	}
	
	public Integer getId() {
		return teamId;
	}
	public void setId(Integer id) {
		this.teamId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String leagueName) {
		this.league = leagueName;
	}

	public Integer getyearFounded() {
		return yearFounded;
	}

	public void setyearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}
	public Integer getyearLast() {
		return yearLast;
	}

	public void setyearLast(Integer yearLast) {
		this.yearLast = yearLast;
	}

	@Override
	public boolean equals(Object obj) { 
		if(!(obj instanceof Team)){
			return false;
		}
		Team other = (Team) obj;
		return (this.getName().equalsIgnoreCase(other.getName()) &&
				this.getyearFounded()==other.getyearFounded() &&
				this.getyearLast()==other.getyearLast() &&
				this.getLeague()==other.getLeague());
	}
	 
	@Override
	public int hashCode() {
		Integer hash = 0;
		if (this.getName()!=null) hash += this.getName().hashCode(); 
		if (this.getyearFounded()!=null) hash += this.getyearFounded().hashCode();
		if (this.getyearLast()!=null) hash += this.getyearLast().hashCode(); 
		if (this.getLeague()!=null) hash += this.getLeague().hashCode();
		return hash;
	}
	
	
}

