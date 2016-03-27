package com.csh.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VehicleUtil {

  public static List<List<Map<String, Object>>> getVehicleLineByCode(
      List<Map<String, Object>> vehicleLineMap) {
    String[] codes =
        {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};
    List<List<Map<String, Object>>> lineMaps = new ArrayList<List<Map<String, Object>>>();
    for (String code : codes) {
      List<Map<String, Object>> lineMap = new ArrayList<Map<String, Object>>();
      for (Map<String, Object> map : vehicleLineMap) {
        if (code.equals(map.get("code"))) {
          lineMap.add(map);
        }
      }
      lineMaps.add(lineMap);
    }
    return lineMaps;
  }
}
