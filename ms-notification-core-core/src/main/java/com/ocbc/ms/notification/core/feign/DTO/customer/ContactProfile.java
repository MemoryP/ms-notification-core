package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class ContactProfile {
    /**
     * is the only address on record a "care-of" or "keep-in" address
     */
    private Boolean isAddressProofVerified;
    /**
     * List of Addresses
     */
    @Valid
    private List<AddressInfo> addressList;
    /**
     * Electronic contact Info List
     */
    @Valid
    private List<EContactInfo> contactInfoList;

    @Valid
    private List<PDPO> pdpoList;
}
