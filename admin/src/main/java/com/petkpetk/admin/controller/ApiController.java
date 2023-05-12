package com.petkpetk.admin.controller;

import com.petkpetk.admin.dto.api.ResponseDTO;
import com.petkpetk.admin.dto.api.ResultObject;

public abstract class ApiController {

  protected <T> ResponseDTO<T> ok() {
    return ok(null, ResultObject.getSuccess());
  }

  protected <T> ResponseDTO<T> ok(T data) {
    return ok(data, ResultObject.getSuccess());
  }

  protected <T> ResponseDTO<T> ok(T data, ResultObject result) {
    ResponseDTO<T> obj = new ResponseDTO<>();
    obj.setResult(result);
    obj.setData(data);

    return obj;
  }
}
