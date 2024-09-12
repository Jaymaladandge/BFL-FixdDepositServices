
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
    "DocName",
    "DocContent",
    "DocType"
})
public class ObjKycDoc {

    @JsonProperty("DocName")
    private String docName;
    @JsonProperty("DocContent")
    private String docContent;
    
    @JsonProperty("DocType")
    private String docType;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("DocName")
    public String getDocName() {
        return docName;
    }

    @JsonProperty("DocName")
    public void setDocName(String docName) {
        this.docName = docName;
    }

    @JsonProperty("DocContent")
    public String getDocContent() {
        return docContent;
    }

    @JsonProperty("DocContent")
    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @JsonProperty("DocType")
	public String getDocType() {
		return docType;
	}
	 @JsonProperty("DocType")
	public void setDocType(String docType) {
		this.docType = docType;
	}

	@Override
	public String toString() {
		return "ObjKycDoc [docName=" + docName + ", docContent=" + docContent + ", docType=" + docType
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	
    
    

}
