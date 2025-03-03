package maquina1995.raider.io.profiler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractRaid {
	
	private String summary;
	@JsonProperty("expansion_id")
	private float expansionId;
	@JsonProperty("total_bosses")
	private float totalBosses;
	@JsonProperty("normal_bosses_killed")
	private float normalBossesKilled;
	@JsonProperty("heroic_bosses_killed")
	private float heroicBossesKilled;
	@JsonProperty("mythic_bosses_killed")
	private float mythicBossesKilled;
}
