package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlternativeName {
    /**
     * sequence number
     */
    private Long sequenceNo;

    /**
     * Alternative Name Type
     */
    private CodeValueInfo alternativeNameType;

    /**
     * Customer Alternative Name
     */
    @Size(max = 150)
    private String alternativeName;

    @Size(max = 8)
    private String effectiveDate;

    private Long nameChangeDate;

    /**
     * update date time
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private String lastUpdatedDateTime;

    private String operationType;
}
