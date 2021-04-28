package org.wso2.carbon.apimgt.internal.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.json.simple.JSONObject;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.gatewaybridge.dto.WebhookSubscriptionDTO;
import org.wso2.carbon.apimgt.gatewaybridge.webhooks.ExternalGatewayWebhookSubscriptionService;
import org.wso2.carbon.apimgt.gatewaybridge.webhooks.ExternalGatewayWebhookSubscriptionServiceImpl;
import org.wso2.carbon.apimgt.internal.service.GatewaybridgeSubscriptionApiService;
import org.wso2.carbon.apimgt.internal.service.dto.EventSubscriptionDTO;

import javax.ws.rs.core.Response;

/**
 * web service.
 */
public class GatewaybridgeSubscriptionApiServiceImpl implements GatewaybridgeSubscriptionApiService {

    private static final Log log = LogFactory.getLog(GatewaybridgeSubscriptionApiServiceImpl.class);
    private final ExternalGatewayWebhookSubscriptionService externalGatewayWebhookSubscription =
            new ExternalGatewayWebhookSubscriptionServiceImpl();


    @Override
    public Response gatewaybridgeSubscriptionPost(EventSubscriptionDTO subscription, MessageContext messageContext)
            throws APIManagementException {
        try {
            externalGatewayWebhookSubscription.addExternalGatewaySubscription(
                    new WebhookSubscriptionDTO(subscription.getSubscriberName(),
                            subscription.getCallbackUrl(), subscription.getTopic()));
            return Response.ok().build();
        } catch (APIManagementException e) {
            log.error("Error while processing request", e);
            JSONObject responseObj = new JSONObject();
            responseObj.put("Message", e.getMessage());
            String responseStringObj = String.valueOf(responseObj);
            return Response.serverError().entity(responseStringObj).build();
        }
    }
}
