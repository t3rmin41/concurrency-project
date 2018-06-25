package com.concurrency.project.test.memory;

public class Outer {

  int month = 4;

  public int getMonth() {
    return this.month;
  }
  public void setMonth(int m) {
    this.month = m;
  }
  
  public class Inner {
    private int year = 2018;

    public int getYear() {
      return year;
    }
    public void setYear(int year) {
      this.year = year;
    }
    public int getMonth() {
      return month;
    }
    
  }

}
