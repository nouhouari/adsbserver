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
package com.hin.asdb.model;

public class Aircraft {

  private String Icao;
  private int Alt;
  private int GAlt;
  private double InHg;
  private int AltT;
  private String Call;
  private double Lat;
  private double Long;
  private boolean Mlat;
  private boolean Tisb;
  private double Spd;
  private double Trak;
  private boolean TrkH;
  private int Sqk;
  private boolean Help;
  private int Vsi;
  private int VsiT;
  private boolean Gnd;
  private int SpdTyp;
  private boolean CallSus;
  private int Trt;
  
  
  /**
   * @return the icao
   */
  public String getIcao() {
    return Icao;
  }
  /**
   * @param icao the icao to set
   */
  public void setIcao(String icao) {
    Icao = icao;
  }
  /**
   * @return the alt
   */
  public int getAlt() {
    return Alt;
  }
  /**
   * @param alt the alt to set
   */
  public void setAlt(int alt) {
    Alt = alt;
  }
  /**
   * @return the gAlt
   */
  public int getGAlt() {
    return GAlt;
  }
  /**
   * @param gAlt the gAlt to set
   */
  public void setGAlt(int gAlt) {
    GAlt = gAlt;
  }
  /**
   * @return the inHg
   */
  public double getInHg() {
    return InHg;
  }
  /**
   * @param inHg the inHg to set
   */
  public void setInHg(double inHg) {
    InHg = inHg;
  }
  /**
   * @return the altT
   */
  public int getAltT() {
    return AltT;
  }
  /**
   * @param altT the altT to set
   */
  public void setAltT(int altT) {
    AltT = altT;
  }
  /**
   * @return the call
   */
  public String getCall() {
    return Call;
  }
  /**
   * @param call the call to set
   */
  public void setCall(String call) {
    Call = call;
  }
  /**
   * @return the lat
   */
  public double getLat() {
    return Lat;
  }
  /**
   * @param lat the lat to set
   */
  public void setLat(double lat) {
    Lat = lat;
  }
  /**
   * @return the long
   */
  public double getLong() {
    return Long;
  }
  /**
   * @param l the long to set
   */
  public void setLong(double l) {
    Long = l;
  }
  /**
   * @return the mlat
   */
  public boolean isMlat() {
    return Mlat;
  }
  /**
   * @param mlat the mlat to set
   */
  public void setMlat(boolean mlat) {
    Mlat = mlat;
  }
  /**
   * @return the tisb
   */
  public boolean isTisb() {
    return Tisb;
  }
  /**
   * @param tisb the tisb to set
   */
  public void setTisb(boolean tisb) {
    Tisb = tisb;
  }
  /**
   * @return the spd
   */
  public double getSpd() {
    return Spd;
  }
  /**
   * @param spd the spd to set
   */
  public void setSpd(double spd) {
    Spd = spd;
  }
  /**
   * @return the trak
   */
  public double getTrak() {
    return Trak;
  }
  /**
   * @param trak the trak to set
   */
  public void setTrak(double trak) {
    Trak = trak;
  }
  /**
   * @return the trkH
   */
  public boolean isTrkH() {
    return TrkH;
  }
  /**
   * @param trkH the trkH to set
   */
  public void setTrkH(boolean trkH) {
    TrkH = trkH;
  }
  /**
   * @return the sqk
   */
  public int getSqk() {
    return Sqk;
  }
  /**
   * @param sqk the sqk to set
   */
  public void setSqk(int sqk) {
    Sqk = sqk;
  }
  /**
   * @return the help
   */
  public boolean isHelp() {
    return Help;
  }
  /**
   * @param help the help to set
   */
  public void setHelp(boolean help) {
    Help = help;
  }
  /**
   * @return the vsi
   */
  public int getVsi() {
    return Vsi;
  }
  /**
   * @param vsi the vsi to set
   */
  public void setVsi(int vsi) {
    Vsi = vsi;
  }
  /**
   * @return the vsiT
   */
  public int getVsiT() {
    return VsiT;
  }
  /**
   * @param vsiT the vsiT to set
   */
  public void setVsiT(int vsiT) {
    VsiT = vsiT;
  }
  /**
   * @return the gnd
   */
  public boolean isGnd() {
    return Gnd;
  }
  /**
   * @param gnd the gnd to set
   */
  public void setGnd(boolean gnd) {
    Gnd = gnd;
  }
  /**
   * @return the spdTyp
   */
  public int getSpdTyp() {
    return SpdTyp;
  }
  /**
   * @param spdTyp the spdTyp to set
   */
  public void setSpdTyp(int spdTyp) {
    SpdTyp = spdTyp;
  }
  /**
   * @return the callSus
   */
  public boolean isCallSus() {
    return CallSus;
  }
  /**
   * @param callSus the callSus to set
   */
  public void setCallSus(boolean callSus) {
    CallSus = callSus;
  }
  /**
   * @return the trt
   */
  public int getTrt() {
    return Trt;
  }
  /**
   * @param trt the trt to set
   */
  public void setTrt(int trt) {
    Trt = trt;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Aircraft [Icao=" + Icao + ", Alt=" + Alt + ", GAlt=" + GAlt + ", InHg=" + InHg + ", AltT=" + AltT + ", Call=" + Call + ", Lat="
        + Lat + ", Long=" + Long + ", Mlat=" + Mlat + ", Tisb=" + Tisb + ", Spd=" + Spd + ", Trak=" + Trak + ", TrkH=" + TrkH + ", Sqk="
        + Sqk + ", Help=" + Help + ", Vsi=" + Vsi + ", VsiT=" + VsiT + ", Gnd=" + Gnd + ", SpdTyp=" + SpdTyp + ", CallSus=" + CallSus
        + ", Trt=" + Trt + "]";
  }
  
}
