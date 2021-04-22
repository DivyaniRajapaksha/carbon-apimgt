package org.wso2.carbon.apimgt.gatewaybridge.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Class for representing the webhook subscription.
 */
public class WebhookSubscription implements Serializable {

    private String subscriberName;
    private String callback;
    private String topic;
    private Date updatedTime;
    private long expiryTime;

    public WebhookSubscription(String subscriberName, String callback, String topic, long expiryTime) {
        this.subscriberName = subscriberName;
        this.callback = callback;
        this.topic = topic;
        this.expiryTime = expiryTime;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getUpdatedTime() {
        return updatedTime != null ? new Date(updatedTime.getTime()) : null;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime != null ? new Date(updatedTime.getTime()) : null;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}

