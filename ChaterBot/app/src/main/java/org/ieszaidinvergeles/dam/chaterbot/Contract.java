package org.ieszaidinvergeles.dam.chaterbot;

import android.provider.BaseColumns;

public class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {}

    /* Inner class that defines the table contents */
    public static class ChatTable implements BaseColumns {
        public static final String TABLE_NAME = "chat";
        public static final String COLUMN_NAME_DATE= "date";
        public static final String COLUMN_NAME_CHATLOG = "chatlog";

        public static final String SQL_CREATE_CHAT =    "create table " + TABLE_NAME + " (" +
                                                            _ID + " integer primary key," + //No es necesario ponerle autoincrement por lo visto.
                                                            COLUMN_NAME_DATE + " date unique," +
                                                            COLUMN_NAME_CHATLOG + " text)";

        public static final String SQL_DROP_CHAT = "drop table if exists " + TABLE_NAME;
    }

}
