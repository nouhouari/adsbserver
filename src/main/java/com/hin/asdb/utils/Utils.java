/* 
 * Copyright (c) 2017 Nourreddine HOUARI (houarinourredine@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */ 
package com.hin.asdb.utils;

/**
 * @author nhouari
 *
 */
public class Utils {

  public static final int NZ = 15;
  public static final double dLatEven = 360.0 / 60.0;
  public static final double dLatOdd = 360.0 / 59.0;

  private static final double tmp = 1 - Math.cos(Math.PI / (2 * NZ));
  private static final double PI_DIV_180 = Math.PI / 180.0;
  private static final double TWO_PI = 2.0 * Math.PI;

  public static int latIndex(double latEven, double latOdd) {
    return (int) Math.floor(59 * latEven - 60 * latOdd + 0.5);
  }

  /**
   * NL(lat)=floor⎛⎝⎜⎜⎜2πarccos(1−1−cos(π2⋅NZ)cos2(π180⋅lat))⎞⎠⎟⎟⎟
   * 
   * @param lat
   * @return
   */
  public static int NLat(double lat) {
    if (lat > 87 || lat < -87) {
      return 1;
    }
    double cos = Math.cos(PI_DIV_180 * Math.abs(lat));
    double cos2 = cos * cos;
    double acos = 1 - tmp / cos2;
    if (acos < -1) {
      acos = -1;
    } else if (acos > 1) {
      acos = 1;
    }
    return (int) Math.floor(TWO_PI / Math.acos(acos));
  }

  public static double latEven(int j, double latEven) {
    double value = dLatEven * (j % 60 + latEven);
    if (value > 270) {
      value -= 360;
    }
    return value;
  }

  public static double latOdd(int j, double latOdd) {
    double value = dLatOdd * (j % 59 + latOdd);
    if (value > 270) {
      value -= 360;
    }
    return value;
  }
}
