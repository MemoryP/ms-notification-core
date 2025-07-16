//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ocbc.ms.notification.cn.dto;

public interface DasResponseBaseDTO {
    void setRetCode(String errorCode);

    String getRetCode();

    void setRetMsg(String errorMessage);

    String getRetMsg();

    boolean isSuccess();

    boolean isUnknown();

    boolean isMissingRetCode();

    void convertFromResponse(DasServiceResponse response) throws Exception;
}
