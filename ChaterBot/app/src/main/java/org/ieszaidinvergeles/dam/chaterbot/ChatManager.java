package org.ieszaidinvergeles.dam.chaterbot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static org.ieszaidinvergeles.dam.chaterbot.MainActivity.TAG;

public class ChatManager {

    private Helper ayudante;
    private SQLiteDatabase bd;

    public ChatManager(Context c) {
        this(c, true);
    }

    public ChatManager(Context c, boolean write) {
        this.ayudante = new Helper(c);

        if(write){
            bd = this.ayudante.getWritableDatabase();

        }else if(!write){
            bd = this.ayudante.getReadableDatabase();

        }
    }

    /*  Recomienda cerrar la conexión con la bd a nivel de actividad.
        Ver pdf donde se habla al respecto.
        Cerrar: en el onPause() Reabrir: onResume()
    */
    public void cerrar(){
        this.ayudante.close(); //Al cerrar el ayudante se cierra la conexión con la bd.
    }

    public long insert(Chat c){

        //El long es el id con el que el objeto se ha insertado.
        //El nullColumnHack:
        Log.v(TAG, c.getChatlog());
        return bd.insert(Contract.ChatTable.TABLE_NAME, null, Utilities.contentValues(c));
    }

    public int deleteTodo(){
        int delete = bd.delete(Contract.ChatTable.TABLE_NAME, null,null);

        return delete;
    }

    public int delete(long id){

        String condicion = Contract.ChatTable._ID + " = ?";

        String[] argumentos = { id + "" };

        int cuenta = bd.delete(Contract.ChatTable.TABLE_NAME, condicion,argumentos);

        return cuenta;
    }

    public int delete(String nombre){

        String condicion = Contract.ChatTable.COLUMN_NAME_DATE + " = ?";

        String[] argumentos = { nombre };

        int cuenta = bd.delete(Contract.ChatTable.TABLE_NAME, condicion,argumentos);

        return cuenta;
    }

    public int update(Chat c) {

        return bd.update(   Contract.ChatTable.TABLE_NAME,
                            Utilities.contentValues(c),
                            Contract.ChatTable._ID + " = ?",
                            new String[]{c.getId() + ""});
    }

    //Objeto que representa una esstructura de datos mediante el cual puedo recorrer la consulta.
    public Cursor getCursor(String condicion, String[] argumentos) {

        return bd.query(    Contract.ChatTable.TABLE_NAME,
                            null, //null = *
                            condicion, //where
                            argumentos, //Array de los argumentos del select
                            null, //Lo que va después del group by
                            null, //Lo que va después del having (va solo con group by)
                            Contract.ChatTable.COLUMN_NAME_DATE + " asc"); //Ordenar
    }

    public Cursor getCursor() {

        return getCursor(null, null);
    }

    //Nos devuelve la fila del cursor.
    public Chat getRow(Cursor c) {

        Chat contacto = new Chat();

        //Vemos la importancia del contrato.
        contacto.setId(c.getLong(c.getColumnIndex(Contract.ChatTable._ID)));
        contacto.setDate(c.getString(c.getColumnIndex(Contract.ChatTable.COLUMN_NAME_DATE)));
        contacto.setChatlog(c.getString(c.getColumnIndex(Contract.ChatTable.COLUMN_NAME_CHATLOG)));

        return contacto;
    }

    public List<Chat> getContactos(String condicion, String[] argumentos) {

        List<Chat> listaContactos = new ArrayList<>();

        Cursor cursor = getCursor(condicion,argumentos);

        while (cursor.moveToNext ()) {

            listaContactos.add(getRow(cursor));
        }

        cursor.close();

        return listaContactos;
    }

    public String getContactosArchivo() {
        String archivo = "";
        List<Chat> listaContactos = new ArrayList<>();

        Cursor cursor = getCursor(null,null);

        while (cursor.moveToNext ()) {
            archivo += "'" + getRow(cursor).getId() + "';'"+ getRow(cursor).getDate() + "';'"+getRow(cursor).getChatlog()+"';\n";
        }

        cursor.close();

        return archivo;
    }



}
