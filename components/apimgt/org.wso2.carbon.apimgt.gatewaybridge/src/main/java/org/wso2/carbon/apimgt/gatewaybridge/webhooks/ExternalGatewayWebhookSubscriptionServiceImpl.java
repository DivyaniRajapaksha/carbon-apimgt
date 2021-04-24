package org.wso2.carbon.apimgt.gatewaybridge.webhooks;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.gatewaybridge.dao.WebhooksDAO;
import org.wso2.carbon.apimgt.gatewaybridge.dto.WebhookSubscriptionDTO;



/**
 * Subscribe the external gateway to receive events.
 */
public class ExternalGatewayWebhookSubscriptionServiceImpl implements ExternalGatewayWebhookSubscriptionService {
    private static final Log log = LogFactory.getLog(ExternalGatewayWebhookSubscriptionServiceImpl.class);

    @Override
    public void addExternalGatewaySubscription(WebhookSubscriptionDTO webhookSubscriptionDTO)
            throws APIManagementException {
       // WebhooksDAO webhooksDAO = WebhooksDAO.getInstance();
        WebhooksDAO webhooksDAO = new WebhooksDAO();
        Boolean result = webhooksDAO.addSubscription(webhookSubscriptionDTO);
        if (result) {
            log.debug("Successfully inserted the subscription ");
        } else {
            log.debug("Unexpected error while inserting the subscription ");
        }

    }
}

