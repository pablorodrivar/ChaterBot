package org.ieszaidinvergeles.dam.chaterbot;

import android.content.ContentValues;

public class Utilities {

    //Transformamos un objeto contacto en un objeto contentvalues.
    public static ContentValues contentValues(Chat c){

        ContentValues contentValues = new ContentValues();

        //contentValues.put(Contract.TablaContacto._ID, c.getId()); Puede dar problemas.

        contentValues.put(Contract.ChatTable.COLUMN_NAME_DATE, c.getDate());
        contentValues.put(Contract.ChatTable.COLUMN_NAME_CHATLOG, c.getChatlog());

        return contentValues;
    }


}
