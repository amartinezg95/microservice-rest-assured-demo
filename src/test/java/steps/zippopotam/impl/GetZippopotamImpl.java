package steps.zippopotam.impl;

import steps.common.impl.BaseStepsImpl;
import steps.common.utils.HTTPUtils;
import steps.common.utils.RequestSpecificationDTO;
import steps.common.utils.RestInteraction;

public class GetZippopotamImpl extends BaseStepsImpl {

	public GetZippopotamImpl() {
		super("get.zippopotam", "http://api.zippopotam.us/");
	}

	public RequestSpecificationDTO fillDTO() {
		RequestSpecificationDTO requestSpecificationDTO = new RequestSpecificationDTO();
		requestSpecificationDTO.fillHeaderContentType(HTTPUtils.JSON_CONTENT_TYPE);
		return requestSpecificationDTO;
	}

	public RestInteraction launchGetRequest(String country, String zip) {
		this.url = "http://api.zippopotam.us/" + country + "/" + zip;
		return this.launchGetRequestUsingDTO(this.fillDTO());
	}
}
