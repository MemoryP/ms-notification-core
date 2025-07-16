package com.ocbc.ms.notification.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liu Yong
 * @description UserInfoVo
 * @date 2020/9/27
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoVo {

    @NotBlank
    @JsonProperty(value = "type")
    private String type;
    @NotBlank
    @JsonProperty(value = "id")
    private String id;
    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    private Object remark;

    @NotBlank
    private Object extra;

    public UserInfoVo(UserInfoVo other){
        if(other != null){
            this.type = other.type;
            this.id = other.id;
            this.name = other.name;
            this.remark = other.remark;
            this.extra = other.extra;
        }
    }
}
