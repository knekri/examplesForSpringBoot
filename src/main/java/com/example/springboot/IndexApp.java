package com.example.springboot;

import lombok.Data;
import lombok.NonNull;

@Data
public class IndexApp {
  @NonNull
  private IService service;

}
