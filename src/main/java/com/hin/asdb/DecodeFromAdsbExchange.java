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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hin.asdb.model.Aircraft;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DecodeFromAdsbExchange extends AbstractDecoder {

  /**
   * Host name.
   */
  private String host;
  /**
   * Port number
   */
  private int port;
  private boolean stop;

  /**
   * Build new decoder.
   * 
   * @param host
   * @param port
   */
  public DecodeFromAdsbExchange(String host, int port) {
    this.host = host;
    this.port = port;
  }

  /**
   * Asynchronous method that start to decode in another thread.
   */
  public void start() {
    new Thread(() -> {
      connect();
    }).start();
  }

  private void connect() {
    Socket socket = null;
    try {
      socket = new Socket(host, port);
      final InputStream inputStream = socket.getInputStream();
      readStream(inputStream);

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (socket != null)
          socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private void readStream(InputStream inputStream) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonFactory factory = mapper.getFactory();

    JsonParser jParser = factory.createJsonParser(inputStream);
    while (!jParser.isClosed() && !stop) {

      while (jParser.nextToken() != JsonToken.START_OBJECT) {
      }

      Aircraft aircraft = new Aircraft();
      JsonToken token;
      while ((token = jParser.nextToken()) != JsonToken.END_OBJECT) {

        if (!JsonToken.FIELD_NAME.equals(token)) {
          String fieldname = jParser.getCurrentName();
          String value = jParser.getValueAsString();
          /**
           * private String Icao; private int Alt; private int GAlt; private double InHg; private int AltT; private String Call; private
           * double Lat; private double Long; private boolean Mlat; private boolean Tisb; private double Spd; private double Trak; private
           * boolean TrkH; private int Sqk; private boolean Help; private int Vsi; private int VsiT; private boolean Gnd; private int
           * SpdTyp; private boolean CallSus; private int Trt;
           */
          if ("Icao".equals(fieldname)) {
            aircraft.setIcao(value);
          } else if ("Alt".equals(fieldname)) {
            aircraft.setAlt(Integer.parseInt(value));
          } else if ("Lat".equals(fieldname)) {
            aircraft.setLat(Double.parseDouble(value));
          } else if ("Long".equals(fieldname)) {
            aircraft.setLong(Double.parseDouble(value));
          } else if ("Call".equals(fieldname)) {
            aircraft.setCall(value);
          } else if ("Spd".equals(fieldname)) {
            aircraft.setSpd(Double.parseDouble(value));
          } else if ("Trak".equals(fieldname)) {
            aircraft.setTrak(Double.parseDouble(value));
          }
        }
      }
      notifyListener(aircraft);
    }

    jParser.close();
  }

  /**
   * Stop reading from stream.
   */
  public void stop() {
   this.stop = true; 
  }

}
