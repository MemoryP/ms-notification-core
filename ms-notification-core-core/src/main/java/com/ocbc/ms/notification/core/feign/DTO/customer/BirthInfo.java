package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.ocbc.ms.cbs.core.validation.constraint.LongDate;
import lombok.Data;


@Data
public class BirthInfo {
    /**
     * date of birth
     * format: YYYYMMDD
     * @size 8
     */
    @LongDate
    private String birthDate;
    /**
     * place of birth(city)
     */
    private CodeValueInfo cityCodeIntl;
    /**
     * place of birth(country)
     */
    private CodeValueInfo countryCode;
    /**
     * Province of Birth Place in local language
     * @size 30
     */
    private String birthProvinceLocal;
    /**
     * Province of Birth Place in English
     * @size 30
     */
    private String birthProvinceIntl;

}
