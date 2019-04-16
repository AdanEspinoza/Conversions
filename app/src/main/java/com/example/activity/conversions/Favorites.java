package com.example.activity.conversions;


import android.os.Parcel;
import android.os.Parcelable;

public class Favorites implements Parcelable {

    private int id ;
    private String conversionFROM;
    private String conversionTO;
    private String measureUnit;
    private int id_localeFrom;
    private int id_localeTo;
    private int id_localeMeasure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConversionFROM() {
        return conversionFROM;
    }

    public void setConversionFROM(String conversionFROM) {
        this.conversionFROM = conversionFROM;
    }

    public String getConversionTO() {
        return conversionTO;
    }

    public void setConversionTO(String conversionTO) {
        this.conversionTO = conversionTO;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public int getId_localeFrom() {
        return id_localeFrom;
    }

    public void setId_localeFrom(int id_localeFrom) {
        this.id_localeFrom = id_localeFrom;
    }

    public int getId_localeTo() {
        return id_localeTo;
    }

    public void setId_localeTo(int id_localeTo) {
        this.id_localeTo = id_localeTo;
    }

    public int getId_localeMeasure() {
        return id_localeMeasure;
    }

    public void setId_localeMeasure(int id_localeMeasure) {
        this.id_localeMeasure = id_localeMeasure;
    }

    protected Favorites(Parcel in) {
        id = in.readInt();
        conversionFROM = in.readString();
        conversionTO = in.readString();
        measureUnit = in.readString();
        id_localeFrom = in.readInt();
        id_localeTo = in.readInt();
        id_localeMeasure = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(conversionFROM);
        dest.writeString(conversionTO);
        dest.writeString(measureUnit);
        dest.writeInt(id_localeFrom);
        dest.writeInt(id_localeTo);
        dest.writeInt(id_localeMeasure);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Favorites> CREATOR = new Parcelable.Creator<Favorites>() {
        @Override
        public Favorites createFromParcel(Parcel in) {
            return new Favorites(in);
        }

        @Override
        public Favorites[] newArray(int size) {
            return new Favorites[size];
        }
    };
}