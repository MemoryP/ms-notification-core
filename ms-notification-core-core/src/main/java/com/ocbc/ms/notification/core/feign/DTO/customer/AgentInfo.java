package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.Data;

@Data
public class AgentInfo {
    /**
     * agent name
     */
    private String agentName;
    /** AGENT_TEL
     * agent phone
     */
    private String agentPhone;
    /**
     * agent document info
     */
    private AgentDocumentInfo agentDocumentInfo;
}
