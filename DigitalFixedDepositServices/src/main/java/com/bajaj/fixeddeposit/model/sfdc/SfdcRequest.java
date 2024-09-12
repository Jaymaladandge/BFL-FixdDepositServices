
package com.bajaj.fixeddeposit.model.sfdc;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "RecWrapperFD"
})
public class SfdcRequest {

    @JsonProperty("RecWrapperFD")
    private RecWrapperFD recWrapperFD;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("RecWrapperFD")
    public RecWrapperFD getRecWrapperFD() {
        return recWrapperFD;
    }

    @JsonProperty("RecWrapperFD")
    public void setRecWrapperFD(RecWrapperFD recWrapperFD) {
        this.recWrapperFD = recWrapperFD;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "SfdcRequest [recWrapperFD=" + recWrapperFD + ", additionalProperties=" + additionalProperties + "]";
	}
    
    

}
