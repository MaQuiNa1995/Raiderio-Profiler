package maquina1995.raider.io.profiler.jackson.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import maquina1995.raider.io.profiler.dto.BlackRockDepths;
import maquina1995.raider.io.profiler.dto.LiberationOfUndermine;
import maquina1995.raider.io.profiler.dto.NerubarPalace;
import maquina1995.raider.io.profiler.dto.RaidProgression;

public class RaidProgressionDeserializer extends JsonDeserializer<RaidProgression> {

    @Override
    public RaidProgression deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        RaidProgression raidProgression = new RaidProgression();

        // Deserializacion de NerubarPalace
        JsonNode nerubarNode = node.get("nerubar-palace");
        if (nerubarNode != null) {
            NerubarPalace nerubarPalace = objectMapper.convertValue(nerubarNode, NerubarPalace.class);
            raidProgression.setNerubarPalace(nerubarPalace);
        }

        // Deserializacion de BlackRockDepths
        JsonNode blackrockNode = node.get("blackrock-depths");
        if (blackrockNode != null) {
            BlackRockDepths blackRockDepths = objectMapper.convertValue(blackrockNode, BlackRockDepths.class);
            raidProgression.setBlackRockDepths(blackRockDepths);
        }

        // Deserializacion de LiberationOfUndermine
        JsonNode undermineNode = node.get("liberation-of-undermine");
        if (undermineNode != null) {
            LiberationOfUndermine liberationOfUndermine = objectMapper.convertValue(undermineNode, LiberationOfUndermine.class);
            raidProgression.setLiberationOfUndermine(liberationOfUndermine);
        }

        return raidProgression;
    }
}
