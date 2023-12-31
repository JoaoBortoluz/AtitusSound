package br.edu.atitus.atitusound.entities;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_music")
public class MusicEntity extends GenericEntity{
	
	private Duration duration;
	private int like_count;
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "artist_uuid") // não permite valor nulo no BD
	private ArtistEntity artist;
	
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public ArtistEntity getArtist() {
		return artist;
	}
	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
