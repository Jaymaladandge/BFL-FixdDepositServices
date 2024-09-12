
package com.bajaj.fixeddeposit.model.sfdc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "FDRecordsObjList"
})
public class RecWrapperFD {

    @JsonProperty("FDRecordsObjList")
    private List<FDRecordsObjList> fDRecordsObjList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("FDRecordsObjList")
    public List<FDRecordsObjList> getFDRecordsObjList() {
        return fDRecordsObjList;
    }

    @JsonProperty("FDRecordsObjList")
    public void setFDRecordsObjList(List<FDRecordsObjList> fDRecordsObjList) {
        this.fDRecordsObjList = fDRecordsObjList;
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
		return "RecWrapperFD [fDRecordsObjList=" + fDRecordsObjList + ", additionalProperties=" + additionalProperties
				+ "]";
	}
    
    

}
