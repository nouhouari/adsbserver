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

import com.hin.asdb.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SampleDecode {

  private static final int DF_MASK = 0b11111000;
  private static final int CA_MASK = 0b00000111;
  private static final int SIX_BIT = 0b111111;
  private static final String LOOKUP_CHAR = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
  private static final double LAT_LON_LSB = Math.pow(2, 17);

  private static Map<Integer, CPRPositionReport> positionReports = new HashMap<>();

  public static void main(String[] args) {
    try {

      // System.out.println(Long.toBinaryString(Long.parseLong("4840D6",16)));

      // ModeSReply even = Decoder.genericDecoder("31847CD5B5C100");
      // final subtype type = even.getType();
      // System.out.println(type);

      Socket socket = new Socket("localhost", 30002);
      final InputStream inputStream = socket.getInputStream();
      //FileInputStream inputStream = new FileInputStream("src/main/resources/adsb.txt");
      readStream(inputStream);

      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private static void readStream(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line = null;

    int nb = 0;

    while ((line = reader.readLine()) != null) {
      String lineReplaced = line.replace("*", "").replace(";", "");

      String df_ca = lineReplaced.substring(0, 2);
      String icao = lineReplaced.substring(2, 8);
      int df_ca_data = Integer.parseInt(df_ca, 16);
      // int icao_data = Integer.parseInt(icao, 16);
      int df = ((df_ca_data & DF_MASK) >> 3);
      int ca = (df_ca_data & CA_MASK);
      // System.out.println("df=" +df + " , ca=" + ca);

      if (lineReplaced.length() <= 14) {
        continue;
      }
      String data = lineReplaced.substring(8, 22);

      long data_data = Long.parseLong(data, 16);

      int tc = (int) (data_data >> 51);

      //System.out.println("df=" +df + " , ca=" + ca + ", tc=" + tc);
      // System.out.println("icao=" + icao_data +" (hex:"+icao+")" + " (bin:"+Long.toBinaryString(icao_data)+")");
      // System.out.println(data_data + " hex:" + data + ", binary:" + Long.toBinaryString(data_data));

      // System.out.println("tc dec=" + tc + ", binary="+Integer.toBinaryString(tc));

      if (df==17 && (tc >= 1 && tc <= 4)) {
        // Identification message
        String callSign = "";
        for (int i = 0; i < 8; i++) {
          int C = (int) ((data_data >> (42 - i * 6)) & SIX_BIT);
          callSign += LOOKUP_CHAR.charAt(C);
        }
        System.out.println("CallSign: " + callSign);
      } else if (df == 17 && (tc >= 9 && tc <= 18)) {
        // Aircraft airborne position
        //System.out.println(Long.toHexString(data_data));
//        System.out.println("Position df=" + df + " , ca=" + ca + ", tc=" + tc + " ,icao=" + icao);

        /*
         * MSG bits # bits Abbr Content 1-5 5 DF Downlink format // DATA 33-37 5 TC Type code 38-39 2 SS Surveillance status 40 1 NICsb NIC
         * supplement-B 41-52 12 ALT Altitude 53 1 T Time 54 1 F CPR odd/even frame flag 55-71 17 LAT-CPR Latitude in CPR format 72-88 17
         * LON-CPR Longitude in CPR format
         */
        int surveillanceStatus = (int) ((data_data >> 38) & 0b11);
        int nicsb = (int) ((data_data >> 40) & 0b1);
        int altitude = (int) ((data_data >> 36) & 0b111111111111);
        int time = (int) ((data_data >> 35) & 0b1);
        byte even_odd = (byte) ((data_data >> 34) & 0b1);
        int cpr_lat = (int) ((data_data >> 17) & 0b11111111111111111);
        int cpr_lon = (int) ((data_data) & 0b11111111111111111);

//        System.out.println(Integer.toBinaryString(altitude));
//        System.out.println("flag:" + even_odd + " ,lat=" + Integer.toBinaryString(cpr_lat) + " ,lon=" + Integer.toBinaryString(cpr_lon)
//            + ", alt=" + Integer.toBinaryString(altitude));

        double lat = cpr_lat / LAT_LON_LSB;
        double lon = cpr_lon / LAT_LON_LSB;

        // Altitude
        int Q = (altitude & 0b10000) >> 4;
        int feet = (Q == 0) ? 100 : 25;
        // Remove the Q bit
        int N = (altitude & 0b111111100000) >> 1 | (altitude & 0b1111);
        double alt_feet = N * feet - 1000.0;
//        System.out.println("Q=" + Q + ", N=" + N + " " + Integer.toBinaryString(N));
//        System.out.println("lat=" + lat + " ,lon=" + lon + ", alt=" + alt_feet);

        CPRPositionReport currentPositionReport = new CPRPositionReport();
        currentPositionReport.setAltitude(alt_feet);
        currentPositionReport.setDf(df);
        currentPositionReport.setEven((even_odd == 0) ? true : false);
        currentPositionReport.setIcao(Integer.parseInt(icao, 16));
        currentPositionReport.setLat(lat);
        currentPositionReport.setLon(lon);
        currentPositionReport.setNicsb(nicsb);
        currentPositionReport.setSurveillanceStatus(surveillanceStatus);
        currentPositionReport.setTc(tc);
        currentPositionReport.setTime(time);
        currentPositionReport.setDate(System.currentTimeMillis());

        
        CPRPositionReport previousPositionReport = positionReports.get(currentPositionReport.getIcao());

        System.out.println("icao : " + Integer.toHexString(currentPositionReport.getIcao()) + " previous:" + previousPositionReport);
        
        if (previousPositionReport != null && (previousPositionReport.isEven() ^ currentPositionReport.isEven())) {
//          System.out.println("Even and Odd");
          // One even and one odd
          CPRPositionReport evenPositionReport = currentPositionReport.isEven() ? currentPositionReport : previousPositionReport;
          CPRPositionReport oddPositionReport = currentPositionReport.isEven() ? previousPositionReport : currentPositionReport;

          // Compute position
          int indexJ = Utils.latIndex(evenPositionReport.getLat(), oddPositionReport.getLat());

//          System.out.println("Index J=" + indexJ);

          double latEven = Utils.latEven(indexJ, evenPositionReport.getLat());
          double latOdd = Utils.latOdd(indexJ, oddPositionReport.getLat());

//          System.out.println("Lat even = " + latEven);
//          System.out.println("Lat odd  = " + latOdd);

          boolean isSameLatitudeZone = Utils.NLat(latEven) == Utils.NLat(latOdd);
//          System.out.println("Consistency = " + isSameLatitudeZone);

          if (isSameLatitudeZone) {
            double latitude = 0;
            
//          System.out.println(" >>>> Latitude : " + latitude);
            
            /**
             * nidLonmLon=max(NL(Lateven),1)=360ni=floor(LoncprEven⋅[NL(Lateven)−1]−LoncprOdd⋅NL(Lateven)+12)=dLon⋅(mod(m,ni)+LoncprEven)
             * ni=max(NL(Lateven),1)dLon=360nim=floor(LoncprEven⋅[NL(Lateven)−1]−LoncprOdd⋅NL(Lateven)+12)Lon=dLon⋅(mod(m,ni)+LoncprEven)
             */
            double longitude = 0;
            if(evenPositionReport.getDate() > oddPositionReport.getDate()){
              int ni = Math.max(Utils.NLat(latEven), 1);
              double dLon = 360.0 / ni;
              // LoncprEven⋅[NL(Lateven)−1]−LoncprOdd⋅NL(Lateven)+12
              double m = Math.floor(evenPositionReport.getLon() * (Utils.NLat(latEven) - 1) - oddPositionReport.getLon() * Utils.NLat(latEven) + 0.5);
              // dLon⋅(mod(m,ni)+LoncprOdd)
              latitude = latEven;
              longitude = dLon *(m%ni+evenPositionReport.getLon());   
            }else{
              int ni = Math.max(Utils.NLat(latOdd)-1, 1);
              double dLon = 360.0 / ni;
              // LoncprEven⋅[NL(Lateven)−1]−LoncprOdd⋅NL(Lateven)+12
              double m = Math.floor(evenPositionReport.getLon() * (Utils.NLat(latOdd) - 1) - oddPositionReport.getLon() * Utils.NLat(latOdd) + 0.5);
              // dLon⋅(mod(m,ni)+LoncprOdd)
              latitude = latOdd;
              longitude = dLon *(m%ni+oddPositionReport.getLon());   
            }
            if(longitude >= 180){
              longitude -= 360;
            }
            System.out.println(">>>>> Latitude:" + latitude + ", Longitude: " + longitude + ", Altitude:" + altitude);
          }
        }
        positionReports.put(currentPositionReport.getIcao(), currentPositionReport);
      }

//      if (nb++ > 2)
//        break;
    }
  }

}
