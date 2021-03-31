package org.wso2.carbon.apimgt.gatewaybridge;

import org.wso2.carbon.apimgt.gatewaybridge.listeners.JMSEventListener;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * Class for starting the server.
 */
public class GatewayBridge {
    public static void main(String[] args) throws InterruptedException, JMSException, NamingException {

        JMSEventListener jmsEventListener = new JMSEventListener();
        jmsEventListener.setSubscriber();
    }
}
