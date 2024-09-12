
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
    "RecUniqueKey",
    "objFD_Details",
    "objInvestment_Acc_Details",
    "objMaturity_Acc_Details",
    "objKyc_Doc",
    "objAppl_Details",
    "SplitFD_Details"
})
public class FDRecordsObjList {

    @JsonProperty("RecUniqueKey")
    private String recUniqueKey;
    @JsonProperty("objFD_Details")
    private ObjFDDetails objFDDetails;
    @JsonProperty("objInvestment_Acc_Details")
    private ObjInvestmentAccDetails objInvestmentAccDetails;
    @JsonProperty("objMaturity_Acc_Details")
    private ObjMaturityAccDetails objMaturityAccDetails;
    @JsonProperty("objKyc_Doc")
    private List<ObjKycDoc> objKycDoc;
    @JsonProperty("objAppl_Details")
    private List<ObjApplDetail> objApplDetails = null;
    @JsonProperty("SplitFD_Details")
    private List<SplitFDDetail> splitFDDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("RecUniqueKey")
    public String getRecUniqueKey() {
        return recUniqueKey;
    }

    @JsonProperty("RecUniqueKey")
    public void setRecUniqueKey(String recUniqueKey) {
        this.recUniqueKey = recUniqueKey;
    }

    @JsonProperty("objFD_Details")
    public ObjFDDetails getObjFDDetails() {
        return objFDDetails;
    }

    @JsonProperty("objFD_Details")
    public void setObjFDDetails(ObjFDDetails objFDDetails) {
        this.objFDDetails = objFDDetails;
    }

    @JsonProperty("objInvestment_Acc_Details")
    public ObjInvestmentAccDetails getObjInvestmentAccDetails() {
        return objInvestmentAccDetails;
    }

    @JsonProperty("objInvestment_Acc_Details")
    public void setObjInvestmentAccDetails(ObjInvestmentAccDetails objInvestmentAccDetails) {
        this.objInvestmentAccDetails = objInvestmentAccDetails;
    }

    @JsonProperty("objMaturity_Acc_Details")
    public ObjMaturityAccDetails getObjMaturityAccDetails() {
        return objMaturityAccDetails;
    }

    @JsonProperty("objMaturity_Acc_Details")
    public void setObjMaturityAccDetails(ObjMaturityAccDetails objMaturityAccDetails) {
        this.objMaturityAccDetails = objMaturityAccDetails;
    }

    @JsonProperty("objKyc_Doc")
    public List<ObjKycDoc> getObjKycDoc() {
		return objKycDoc;
	}

    @JsonProperty("objKyc_Doc")
	public void setObjKycDoc(List<ObjKycDoc> objKycDoc) {
		this.objKycDoc = objKycDoc;
	}

	@JsonProperty("objAppl_Details")
    public List<ObjApplDetail> getObjApplDetails() {
        return objApplDetails;
    }

    @JsonProperty("objAppl_Details")
    public void setObjApplDetails(List<ObjApplDetail> objApplDetails) {
        this.objApplDetails = objApplDetails;
    }

    @JsonProperty("SplitFD_Details")
    public List<SplitFDDetail> getSplitFDDetails() {
        return splitFDDetails;
    }

    @JsonProperty("SplitFD_Details")
    public void setSplitFDDetails(List<SplitFDDetail> splitFDDetails) {
        this.splitFDDetails = splitFDDetails;
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
		return "FDRecordsObjList [recUniqueKey=" + recUniqueKey + ", objFDDetails=" + objFDDetails
				+ ", objInvestmentAccDetails=" + objInvestmentAccDetails + ", objMaturityAccDetails="
				+ objMaturityAccDetails + ", objKycDoc=" + objKycDoc + ", objApplDetails=" + objApplDetails
				+ ", splitFDDetails=" + splitFDDetails + ", additionalProperties=" + additionalProperties + "]";
	}

    
    
}
