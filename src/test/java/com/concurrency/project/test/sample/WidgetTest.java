package com.concurrency.project.test.sample;

import org.junit.Test;
import com.concurrency.project.sample.LoggingWidget;
import com.concurrency.project.sample.Widget;

public class WidgetTest {

  @Test
  public void testWidget() {
    //"synchronized" uses reentrant lock, otherwise widget object would never enter synchronized block
    //as lock would be acquired on loggingWidget object
    Widget widget = new Widget();
    LoggingWidget loggingWidget = new LoggingWidget();
    
    loggingWidget.doSomething();
  }

}
