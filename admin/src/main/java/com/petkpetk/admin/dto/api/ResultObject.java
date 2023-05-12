package com.petkpetk.admin.dto.api;

import java.io.Serializable;

import com.petkpetk.admin.config.constant.ResultType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject implements Serializable {

  public String code;

  public String desc;

  public ResultObject(ResultType resultType) {
    this.code = resultType.getCode();
    this.desc = resultType.getDesc();
  }

  public ResultObject(ResultType resultCode, String message) {
    this.code = resultCode.getCode();
    this.desc = message;
  }

  public ResultObject(Exception e) {
    this.code = ResultType.SYSTEM_ERROR.getCode();
    this.desc = ResultType.SYSTEM_ERROR.getDesc();
  }

  public static ResultObject getSuccess() {
    return new ResultObject(ResultType.SUCCESS);
  }
}
