package org.wso2.carbon.apimgt.gatewaybridge.apiretriever;

/**
 * Class for retrieving artifacts from the storage.
 */
public interface ArtifactRetriever {
    /**
     * Retrieves data from the storage.
     *
     * @param apiId              the UUID of the API
     * @param gatewayLabel       the label subscribed by the gateway
     * @param gatewayInstruction an instruction to check whether this is to publish or remove the API from gateway
     * @return a String contains all the information about the API and gateway artifacts
     * @throws Exception If there are any errors when retrieving the Artifacts
     */
    String retrieveArtifact(String apiId, String gatewayLabel, String gatewayInstruction)
            throws Exception;
}
