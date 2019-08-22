package cn.csfz.eajon.tools.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created By eajon on 2019/5/16.
 */
public class Entity2 implements Parcelable {

    String haha;
    String test;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.haha);
        dest.writeString(this.test);
    }

    public Entity2() {
    }

    protected Entity2(Parcel in) {
        this.haha = in.readString();
        this.test = in.readString();
    }

    public static final Parcelable.Creator<Entity2> CREATOR = new Parcelable.Creator<Entity2>() {
        @Override
        public Entity2 createFromParcel(Parcel source) {
            return new Entity2(source);
        }

        @Override
        public Entity2[] newArray(int size) {
            return new Entity2[size];
        }
    };
}
