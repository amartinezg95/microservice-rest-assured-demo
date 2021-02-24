package steps.zippopotam.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetZippopotamPlacesJson {

	@JsonProperty("place name")
	protected String placeName;
	protected String longitude;
	protected String state;
	@JsonProperty("state abbreviation")
	protected String stateAbbreviation;
	protected String latitude;

}
