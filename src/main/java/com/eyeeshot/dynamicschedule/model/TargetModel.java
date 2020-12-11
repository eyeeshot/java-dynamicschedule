package com.eyeeshot.dynamicschedule.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetModel {
  private String otaId;
  private List<String> targetThings;
}
