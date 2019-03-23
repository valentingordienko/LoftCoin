package ru.valentingordienko.loftcoin.screens.wellcome;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class WellComePage implements Parcelable {

    @DrawableRes
    private int icon;

    @StringRes
    private int title;

    @StringRes
    private int subtitle;

    public WellComePage(int icon, int title, int subtitle) {
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }

    public int getSubtitle() {
        return subtitle;
    }


    protected WellComePage(Parcel in) {
        icon = in.readInt();
        title = in.readInt();
        subtitle = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeInt(title);
        dest.writeInt(subtitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WellComePage> CREATOR = new Creator<WellComePage>() {
        @Override
        public WellComePage createFromParcel(Parcel in) {
            return new WellComePage(in);
        }

        @Override
        public WellComePage[] newArray(int size) {
            return new WellComePage[size];
        }
    };


}
