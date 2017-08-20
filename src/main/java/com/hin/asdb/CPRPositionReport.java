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
package com.hin.asdb;

public class CPRPositionReport {
  
  private int df;
  private int icao;
  private int tc;
  private int surveillanceStatus;
  private int nicsb;
  private double altitude;
  private int time;
  private boolean even;
  private double lat;
  private double lon;
  private long date;
  
  /**
   * @return the df
   */
  public int getDf() {
    return df;
  }
  /**
   * @param df the df to set
   */
  public void setDf(int df) {
    this.df = df;
  }
  /**
   * @return the icao
   */
  public int getIcao() {
    return icao;
  }
  /**
   * @param icao the icao to set
   */
  public void setIcao(int icao) {
    this.icao = icao;
  }
  /**
   * @return the tc
   */
  public int getTc() {
    return tc;
  }
  /**
   * @param tc the tc to set
   */
  public void setTc(int tc) {
    this.tc = tc;
  }
  /**
   * @return the surveillanceStatus
   */
  public int getSurveillanceStatus() {
    return surveillanceStatus;
  }
  /**
   * @param surveillanceStatus the surveillanceStatus to set
   */
  public void setSurveillanceStatus(int surveillanceStatus) {
    this.surveillanceStatus = surveillanceStatus;
  }
  /**
   * @return the nicsb
   */
  public int getNicsb() {
    return nicsb;
  }
  /**
   * @param nicsb the nicsb to set
   */
  public void setNicsb(int nicsb) {
    this.nicsb = nicsb;
  }
  /**
   * @return the altitude
   */
  public double getAltitude() {
    return altitude;
  }
  /**
   * @param altitude the altitude to set
   */
  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }
  /**
   * @return the time
   */
  public int getTime() {
    return time;
  }
  /**
   * @param time the time to set
   */
  public void setTime(int time) {
    this.time = time;
  }
  /**
   * @return the even_odd
   */
  public boolean isEven() {
    return even;
  }
  /**
   * @param even_odd the even_odd to set
   */
  public void setEven(boolean even_odd) {
    this.even = even_odd;
  }
  /**
   * @return the lat
   */
  public double getLat() {
    return lat;
  }
  /**
   * @param lat the lat to set
   */
  public void setLat(double lat) {
    this.lat = lat;
  }
  /**
   * @return the lon
   */
  public double getLon() {
    return lon;
  }
  /**
   * @param lon the lon to set
   */
  public void setLon(double lon) {
    this.lon = lon;
  }
 
  /**
   * 
   * @param dateMs
   */
  public void setDate(long dateMs) {
    this.date = dateMs;
  }
  /**
   * 
   * @return
   */
  public long getDate() {
    return date;
  }
}