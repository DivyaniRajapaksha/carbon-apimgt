package org.wso2.carbon.apimgt.gatewaybridge.models;

import java.util.Set;

/**
 * Model Class for deploying artifacts in the gateway.
 */
public class DeployAPIInGatewayEvent extends Event {

    private String apiId;
    private Set<String> gatewayLabels;
    private static final long serialVersionUID = 6106269076155338045L;

    public DeployAPIInGatewayEvent(String eventId, long timestamp, String type, String tenantDomain, String apiId,
                                   Set<String> gatewayLabels) {

        this.eventId = eventId;
        this.timeStamp = timestamp;
        this.type = type;
        this.tenantDomain = tenantDomain;

        this.apiId = apiId;
        this.gatewayLabels = gatewayLabels;

    }

    public Set<String> getGatewayLabels() {
        return gatewayLabels;
    }

    public void setGatewayLabels(Set<String> gatewayLabels) {

        this.gatewayLabels = gatewayLabels;
    }

    public String getApiId() {

        return apiId;
    }

    public void setApiId(String apiId) {

        this.apiId = apiId;
    }

}
