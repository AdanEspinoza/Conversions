package com.example.activity.conversions;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;


public class CurrencyClient {

    private static String LOG_TAG = CurrencyClient.class.getSimpleName();
    private static String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";
    private static String NAMESPACE = "http://www.webserviceX.NET/";
    private static String METHOD_NAME = "ConversionRate";
    private static String URL = "http://www.webservicex.net/CurrencyConvertor.asmx";

    public static String ERROR="ERROR";
    public static String INTERNET_ERROR="NOINTERNET";
    public static String NOERROR="NOERROR";
    public static String FROM="FROM";
    public static String TO="TO";

    public HashMap<String,String> calculateConversion(String valueTyped, String currencyFrom, String currencyTo)
            throws XmlPullParserException, IOException{

        HashMap hashResponse = new HashMap();
        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("FromCurrency", currencyFrom.substring(0, 3));
        request.addProperty("ToCurrency", currencyTo.substring(0, 3));

        SoapSerializationEnvelope soapEnvolve = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvolve.setOutputSoapObject(request);
        soapEnvolve.dotNet = true;
        System.setProperty("http.keepAlive", "false");
        Double conversionRate;
        String sentenceResponse=ERROR;
        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            httpTransportSE.call(SOAP_ACTION, soapEnvolve);

            //Response
            SoapPrimitive response = (SoapPrimitive) soapEnvolve.getResponse();
            try {
                conversionRate = Double.parseDouble(response.toString());
                Log.d(LOG_TAG, "ConversionRATE: "+String.valueOf(conversionRate));
                conversionRate = conversionRate*Double.parseDouble(valueTyped);
                Log.d(LOG_TAG, "ConversionCURRENCY: " + String.valueOf(conversionRate));
                DecimalFormat twoDecimal = new DecimalFormat("#.#####");
                conversionRate = Double.valueOf(twoDecimal.format(conversionRate));
                Log.d(LOG_TAG, "Conversion2DECIMAL: " + String.valueOf(conversionRate));
                if(conversionRate>0) {
                    sentenceResponse = String.valueOf(conversionRate);
                    //Values format
                    double resultFromDouble = Double.parseDouble(valueTyped);
                    double resultToDouble = Double.parseDouble(sentenceResponse);
                    DecimalFormat formatter = new DecimalFormat("#,###.#####");
                    valueTyped = formatter.format(resultFromDouble);
                    sentenceResponse = formatter.format(resultToDouble);
                    hashResponse.put(FROM, valueTyped+" "+currencyFrom);
                    hashResponse.put(TO, sentenceResponse+" "+currencyTo);
                    hashResponse.put(ERROR, NOERROR);
                }else{
                    hashResponse.put(ERROR, ERROR);
                }
                Log.d(LOG_TAG, "ConversionRESPONSE: " + sentenceResponse);
            } catch (NumberFormatException e) {
                Log.d(LOG_TAG, "NumberFormatException: " + e.getMessage());
            }
        }catch (Exception e) {
            Log.d(LOG_TAG, "Exception: " + e.getMessage());
            hashResponse.put(ERROR, INTERNET_ERROR);
        }
        return hashResponse;
    }
}

