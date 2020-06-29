package com.example.springboot;

public class IndexService implements IService {
  @Override
  public String serve() {
    return "Hello World!!";
  }
}
