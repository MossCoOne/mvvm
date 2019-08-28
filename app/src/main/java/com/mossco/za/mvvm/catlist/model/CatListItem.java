package com.mossco.za.mvvm.catlist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CatListItem implements Parcelable {

    private String id;

    private String url;

    private String sourceUrl;

    protected CatListItem(Parcel in) {
        id = in.readString();
        url = in.readString();
        sourceUrl = in.readString();
    }

    public static final Creator<CatListItem> CREATOR = new Creator<CatListItem>() {
        @Override
        public CatListItem createFromParcel(Parcel in) {
            return new CatListItem(in);
        }

        @Override
        public CatListItem[] newArray(int size) {
            return new CatListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(url);
        parcel.writeString(sourceUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public static Creator<CatListItem> getCREATOR() {
        return CREATOR;
    }
}
