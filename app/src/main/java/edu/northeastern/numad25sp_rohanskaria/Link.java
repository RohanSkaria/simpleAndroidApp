package edu.northeastern.numad25sp_rohanskaria;

import android.os.Parcelable;
import android.os.Parcel;

public class Link implements Parcelable {
    private String name;
    private String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    //Parcel to restore links
    protected Link(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel source) {
            return new Link(source);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };


    public String getName() {
        return name;

    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel end, int f) {
        end.writeString(name);
        end.writeString(url);
    }
}
