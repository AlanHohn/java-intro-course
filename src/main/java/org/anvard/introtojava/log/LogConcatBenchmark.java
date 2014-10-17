package org.anvard.introtojava.log;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

/**
 * Typical practice when performing debug logging is to check if the log level
 * is enabled before assembling the log message, in order to avoid the
 * performance penalty of string concatenation or formatting. This micro
 * benchmark tests the real-world impact of following or not following this
 * practice.
 * 
 * @author alan
 * 
 */
public class LogConcatBenchmark extends Benchmark {

  private static final Logger logger = LoggerFactory
      .getLogger(LogConcatBenchmark.class);
  private String[] strings;
  private double[] doubles;

  @Override
  protected void setUp() throws Exception {
    strings = new String[5];
    doubles = new double[5];
    for (int i = 0; i < 5; i++) {
      strings[i] = RandomStringUtils.random(8);
      doubles[i] = RandomUtils.nextDouble();
    }
  }

  public String timeSimpleStringNoCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      logger.debug("Test");
    }
    return strings[0];
  }

  public String timeSimpleStringCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      if (logger.isDebugEnabled()) {
        logger.debug("Test");
      }
    }
    return strings[0];
  }

  public String timeMultStringNoCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      logger.debug(strings[0] + " " + strings[1] + " " + strings[2] + " "
          + strings[3] + " " + strings[4]);
    }
    return strings[0];
  }

  public String timeMultStringCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      if (logger.isDebugEnabled()) {
        logger.debug(strings[0] + " " + strings[1] + " " + strings[2] + " "
            + strings[3] + " " + strings[4]);
      }
    }
    return strings[0];
  }

  public String timeMultStringParams(long reps) {
    for (int i = 0; i < reps; i++) {
      logger.debug("{} {} {} {} {}", strings[0], strings[1], strings[2],
          strings[3], strings[4]);
    }
    return strings[0];
  }

  public double timeMultDoubleNoCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      logger.debug(String.format("%2.2f %2.2f %2.2f %2.2f %2.2f", doubles[0], doubles[1], doubles[2],
          doubles[3], doubles[4]));
    }
    return doubles[0];
  }

  public double timeMultDoubleCheck(long reps) {
    for (int i = 0; i < reps; i++) {
      if (logger.isDebugEnabled()) {
        logger.debug(String.format("%2.2f %2.2f %2.2f %2.2f %2.2f", doubles[0], doubles[1], doubles[2],
            doubles[3], doubles[4]));
      }
    }
    return doubles[0];
  }

  public static void main(String args[]) {
    CaliperMain.main(LogConcatBenchmark.class, args);
  }

}
