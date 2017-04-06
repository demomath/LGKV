package com.bigsun.www.mylibrary.lgkv.inst;


import android.text.TextUtils;

import com.bigsun.www.mylibrary.lgkv.interf.Parser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public final class LGGsonParser implements Parser {

  private final Gson gson;

  public LGGsonParser(Gson gson) {
    this.gson = gson;
  }

  @Override public <T> T fromJson(String content, Type type) throws JsonSyntaxException {
    if (TextUtils.isEmpty(content)) {
      return null;
    }
    return gson.fromJson(content, type);
  }

  @Override public String toJson(Object body) {
    return gson.toJson(body);
  }

}
