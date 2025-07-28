package com.ocbc.ms.notification.core.feign.DTO.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;


/**
 * CustCtrlRegInfo
 *
 * @author Felix
 * @date 2023/12/11
 * @description:
 */
@Data
@Schema(name = "HoldCodeInfo")
public class HoldCodeInfo implements Serializable {

    private Long sequenceNo;

    @NotBlank
    @Size(max = 10)
    private String holdCode;

    @Size(max = 8)
    private String expireDate;

    @Size(max = 200)
    private String remark;

    @NotBlank
    @Size(max = 12)
    private String branchNo;

    private String createdDate;

    private String createdUser;

    private String createdBranchNo;

    private String lastUpdatedDateTime;

    private String tellerNo;

    private String operationType;

    private String description;

    private String level;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HoldCodeInfo codeInfo = (HoldCodeInfo) obj;
        return StringUtils.equals(this.getHoldCode(), codeInfo.getHoldCode())
                && Objects.equals(this.getSequenceNo(), codeInfo.getSequenceNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getHoldCode(), this.getSequenceNo());
    }
}
