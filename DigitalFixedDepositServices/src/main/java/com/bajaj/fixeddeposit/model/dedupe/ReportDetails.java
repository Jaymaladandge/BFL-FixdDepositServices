package com.bajaj.fixeddeposit.model.dedupe;
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
"FILLER_STRING_1",
"FILLER_STRING_2",
"FILLER_STRING_3",
"FILLER_STRING_4",
"FILLER_STRING_5",
"FILLER_STRING_6",
"FILLER_STRING_7",
"RejectDate"
})
public class ReportDetails {

@JsonProperty("FILLER_STRING_1")
private Object fILLERSTRING1;
@JsonProperty("FILLER_STRING_2")
private Object fILLERSTRING2;
@JsonProperty("FILLER_STRING_3")
private Object fILLERSTRING3;
@JsonProperty("FILLER_STRING_4")
private Object fILLERSTRING4;
@JsonProperty("FILLER_STRING_5")
private Object fILLERSTRING5;
@JsonProperty("FILLER_STRING_6")
private Object fILLERSTRING6;
@JsonProperty("FILLER_STRING_7")
private Object fILLERSTRING7;
@JsonProperty("RejectDate")
private String rejectDate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

@JsonProperty("FILLER_STRING_1")
public Object getFILLERSTRING1() {
return fILLERSTRING1;
}

@JsonProperty("FILLER_STRING_1")
public void setFILLERSTRING1(Object fILLERSTRING1) {
this.fILLERSTRING1 = fILLERSTRING1;
}

@JsonProperty("FILLER_STRING_2")
public Object getFILLERSTRING2() {
return fILLERSTRING2;
}

@JsonProperty("FILLER_STRING_2")
public void setFILLERSTRING2(Object fILLERSTRING2) {
this.fILLERSTRING2 = fILLERSTRING2;
}

@JsonProperty("FILLER_STRING_3")
public Object getFILLERSTRING3() {
return fILLERSTRING3;
}

@JsonProperty("FILLER_STRING_3")
public void setFILLERSTRING3(Object fILLERSTRING3) {
this.fILLERSTRING3 = fILLERSTRING3;
}

@JsonProperty("FILLER_STRING_4")
public Object getFILLERSTRING4() {
return fILLERSTRING4;
}

@JsonProperty("FILLER_STRING_4")
public void setFILLERSTRING4(Object fILLERSTRING4) {
this.fILLERSTRING4 = fILLERSTRING4;
}

@JsonProperty("FILLER_STRING_5")
public Object getFILLERSTRING5() {
return fILLERSTRING5;
}

@JsonProperty("FILLER_STRING_5")
public void setFILLERSTRING5(Object fILLERSTRING5) {
this.fILLERSTRING5 = fILLERSTRING5;
}

@JsonProperty("FILLER_STRING_6")
public Object getFILLERSTRING6() {
return fILLERSTRING6;
}

@JsonProperty("FILLER_STRING_6")
public void setFILLERSTRING6(Object fILLERSTRING6) {
this.fILLERSTRING6 = fILLERSTRING6;
}

@JsonProperty("FILLER_STRING_7")
public Object getFILLERSTRING7() {
return fILLERSTRING7;
}

@JsonProperty("FILLER_STRING_7")
public void setFILLERSTRING7(Object fILLERSTRING7) {
this.fILLERSTRING7 = fILLERSTRING7;
}

@JsonProperty("RejectDate")
public String getRejectDate() {
return rejectDate;
}

@JsonProperty("RejectDate")
public void setRejectDate(String rejectDate) {
this.rejectDate = rejectDate;
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
	return "ReportDetails [fILLERSTRING1=" + fILLERSTRING1 + ", fILLERSTRING2=" + fILLERSTRING2 + ", fILLERSTRING3="
			+ fILLERSTRING3 + ", fILLERSTRING4=" + fILLERSTRING4 + ", fILLERSTRING5=" + fILLERSTRING5
			+ ", fILLERSTRING6=" + fILLERSTRING6 + ", fILLERSTRING7=" + fILLERSTRING7 + ", rejectDate=" + rejectDate
			+ ", additionalProperties=" + additionalProperties + "]";
}



}