package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class CrmInfo {
    /**
     * List of Relationship Managers for the customer
     */
    @Valid
    private List<RelationshipManager> rmList;
    /**
     * recommender
     */
    private String recommender;
}
