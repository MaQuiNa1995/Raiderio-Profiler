package maquina1995.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import maquina1995.jackson.deserializer.RaidProgressionDeserializer;

@Getter
@Setter
@JsonTypeName("raid_progression")
@JsonDeserialize(using = RaidProgressionDeserializer.class)
public class RaidProgression{
	
	@JsonProperty("nerubar-palace")
	private NerubarPalace nerubarPalace;
	
	@JsonProperty("blackrock-depths")
	private BlackRockDepths blackRockDepths;
	
	@JsonProperty("liberation-of-undermine")
	private LiberationOfUndermine liberationOfUndermine;
}
