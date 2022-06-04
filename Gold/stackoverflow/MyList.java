package com.stackoverflow;

import java.util.ArrayList;

public class MyList extends ArrayList {

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < super.size(); i++) {
      str.append(super.get(i)).append("\n");
    }
    return str.toString();
  }
}
