package com.yht.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangHaiTao on 2018/9/12.
 * E-mail: yanghaitao@ren001.com
 */
public class Book implements Parcelable{
    long bookId;
    String bookName;

    public Book(long bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }
    public Book(String bookName) {
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        bookId = in.readLong();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(bookId);
        parcel.writeString(bookName);
    }
}
