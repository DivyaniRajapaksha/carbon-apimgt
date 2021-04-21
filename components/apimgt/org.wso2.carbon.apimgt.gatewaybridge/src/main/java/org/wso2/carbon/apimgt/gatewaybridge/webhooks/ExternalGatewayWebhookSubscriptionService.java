package org.wso2.carbon.apimgt.gatewaybridge.webhooks;


import org.json.JSONObject;
import org.wso2.carbon.apimgt.api.APIManagementException;

/**
 * Subscribe the external gateway to receive events.
 */
public interface ExternalGatewayWebhookSubscriptionService {
    public void addExternalGatewaySubscription(JSONObject object) throws APIManagementException;
}
