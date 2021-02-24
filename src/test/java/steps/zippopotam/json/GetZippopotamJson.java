package steps.zippopotam.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import steps.common.json.AbstractBaseJson;
import steps.common.json.ICollectionJson;

import java.util.List;

@Getter
@Setter
public class GetZippopotamJson extends AbstractBaseJson implements ICollectionJson<GetZippopotamPlacesJson> {
	@JsonProperty("post code")
	private String postCode;
	private String country;
	@JsonProperty("country abbreviation")
	private String countryAbbreviation;
	private List<GetZippopotamPlacesJson> places;

	/*@JsonCreator
	public GetZippopotamJson (@JsonProperty("post code") String postCode,
							  @JsonProperty("country") String country,
							  @JsonProperty("country abbreviation") String countryAbbreviation,
							  @JsonProperty("places") List<GetZippopotamPlacesJson> places){
		this.postCode = postCode;
		this.country = country;
		this.countryAbbreviation = countryAbbreviation;
		this.places = places;
	}*/

	/*@JsonSetter("post code")
	public void getPostCode(String postCode) {
		this.postCode = postCode;
	}

	@JsonSetter("country abbreviation")
	public void getCountryAbbreviation(String countryAbbreviation) {
		this.countryAbbreviation = countryAbbreviation;
	}*/
	/*public GetZippopotamJson (@JsonProperty("post code") String postCode,
							  @JsonProperty("country abbreviation") String countryAbbreviation,
							  @JsonProperty("country") String country,
							  @JsonProperty("places") List<GetZippopotamPlacesJson> places){
		this.postCode = postCode;
		this.countryAbbreviation = countryAbbreviation;
		this.country = country;
		this.places = places;
	}*/

	@Override
	public List getList() {
		return getPlaces();
	}

}
