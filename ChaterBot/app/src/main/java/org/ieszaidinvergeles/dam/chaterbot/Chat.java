package org.ieszaidinvergeles.dam.chaterbot;

import android.os.Parcel;
import android.os.Parcelable;

public class Chat implements Parcelable {
    private long id;
    private String date, chatlog;

    public Chat() {
    }

    public Chat(String date, String chatlog) {
        this.date = date;
        this.chatlog = chatlog;
    }

    protected Chat(Parcel in) {
        id = in.readLong();
        date = in.readString();
        chatlog = in.readString();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChatlog() {
        return chatlog;
    }

    public void setChatlog(String chatlog) {
        this.chatlog = chatlog;
    }

    public void appendChatLog(String chatlog){
        this.chatlog += chatlog;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "date='" + date + '\'' +
                ", chatlog='" + chatlog + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(date);
        dest.writeString(chatlog);
    }
}
