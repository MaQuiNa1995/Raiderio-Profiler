package maquina1995.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {
	
	private String name;
	private String race;
	@JsonProperty("class")
	private String clazz;
	@JsonProperty("active_spec_name")
	private String activeSpecName;
	@JsonProperty("active_spec_role")
	private String activeSpecRole;
	private String gender;
	private String faction;
	@JsonProperty("achievement_points")
	private int achievementPoints;
	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;
	private String region;
	private String realm;
	@JsonProperty("last_crawled_at")
	private String lastCrawledAt;
	@JsonProperty("profile_url")
	private String profileUrl;
	@JsonProperty("profile_banner")
	private String profileBanner;
	@JsonProperty("raid_progression")
	private RaidProgression raidProgression;
	
	public String getGender() {
		return StringUtils.capitalize(this.gender);
	}

}
