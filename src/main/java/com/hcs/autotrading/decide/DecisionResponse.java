package com.hcs.autotrading.decide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DecisionResponse(Decision decision, String reason) {

}
