package org.wso2.carbon.apimgt.gatewaybridge.webhooks;


import org.json.simple.JSONObject;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.gatewaybridge.dao.WebhooksDAO;
import org.wso2.carbon.apimgt.impl.APIConstants;


/**
 * Subscribe the external gateway to receive events.
 */
public class ExternalGatewayWebhookSubscriptionServiceImpl implements ExternalGatewayWebhookSubscriptionService {

    @Override
    public void addExternalGatewaySubscription(org.json.JSONObject object) throws APIManagementException {
        WebhookSubscription webhookSubscription = new WebhookSubscription(
                (String) object.get(APIConstants.Webhooks.SUBSCRIBER_NAME),
                (String) object.get(APIConstants.Webhooks.CALLBACK), (String) object.get(APIConstants.Webhooks.TOPIC),
                (long) object.get(APIConstants.Webhooks.EXPIRY_AT));
        WebhooksDAO.getInstance().addSubscription(webhookSubscription);
    }
}

