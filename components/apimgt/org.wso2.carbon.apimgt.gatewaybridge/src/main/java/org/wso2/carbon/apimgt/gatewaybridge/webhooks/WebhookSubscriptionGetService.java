package org.wso2.carbon.apimgt.gatewaybridge.webhooks;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.gatewaybridge.dto.WebhookSubscriptionDTO;

import java.util.List;

/**
 * Get the subscriptions for the topic from database.
 */
public interface WebhookSubscriptionGetService {
    public List<WebhookSubscriptionDTO> getWebhookSubscription(String topic)
            throws APIManagementException;
}
