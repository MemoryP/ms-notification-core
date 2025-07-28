package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmploymentProfile {
    /**
     * Employment Status
     * @remark 01-Full Time
     *  02-Part Time
     *  03-Self Employment
     *  04-No Employment Status
     *  05-Others
     * @size 2
     */
    private String employSts;
    /**
     * Employer Name
     */
    @Size(max = 150)
    private String companyName;
    /**
     * Company Type
     */
    private CodeValueInfo companyType;
    /**
     * Employment Start Year
     */
    @Size(max = 8)
    private String employmentStartDate;

    @Size(max = 8)
    private String employmentEndDate;

    private CodeValueInfo employmentCountry;

    /**
     * Employment Position
     */
    @Size(max = 4)
    private String position;
    /**
     * Employment Occupation
     */
    private CodeValueInfo occupation;

    private CodeValueInfo title;

    private Boolean isFarmer;

    private String remark;

    private String businessNature;


    private CodeValueInfo primaryIndustryCode;

    private Boolean isCurrentJob;

    private CodeValueInfo annualIncome;

    private CodeValueInfo annualIncomeCcy;

    private Long sequenceNo;

    private String operationType;

}
