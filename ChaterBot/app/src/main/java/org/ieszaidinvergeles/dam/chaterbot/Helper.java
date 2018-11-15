package org.ieszaidinvergeles.dam.chaterbot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "chat.sqlite"; //Nombre del archivo en el que vamos a guardar la bd, cualquier extensión vale.
    public static final int DATABASE_VERSION = 1; //Siempre se empieza por la versión uno, va incrementando.

    public Helper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Cuando corre por primera vez llama a esto.
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contract.ChatTable.SQL_CREATE_CHAT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Contract.ChatTable.SQL_DROP_CHAT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }

}
