package com.mossco.za.mvvm.catlist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CatDto implements Parcelable {
    private String imageUrl;
    private String title;
    private String description;
    private String imageId;

    public CatDto(){

    }

    protected CatDto(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
        description = in.readString();
        imageId = in.readString();
    }

    public static final Creator<CatDto> CREATOR = new Creator<CatDto>() {
        @Override
        public CatDto createFromParcel(Parcel in) {
            return new CatDto(in);
        }

        @Override
        public CatDto[] newArray(int size) {
            return new CatDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageId);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public static Creator<CatDto> getCREATOR() {
        return CREATOR;
    }
}
