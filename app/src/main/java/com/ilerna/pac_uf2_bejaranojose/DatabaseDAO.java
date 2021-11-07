package com.ilerna.pac_uf2_bejaranojose;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Data Object Manager -
 * @author Jose F Bejarano
 * @version 1.0
 * @since 2020
 */
public class DatabaseDAO extends SQLiteOpenHelper {

    SQLiteDatabase  db_readable;

    public DatabaseDAO(@Nullable Context context,
                       @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    /**
     * Metodo para crear la BD SQLite
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Sentencia SQLite para crear la BD. Consultar tipos de Datos de SQLite
        String  crearTabla= "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, " +
                "apellido TEXT, " +
                "usuario TEXT, " +
                "pass TEXT, " +
                "email TEXT);";
        //Ejecutamos la sentencia
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    /**
     * Metodo para insertar los datos de registro en la BD
     *
     * @return true si lo hemos insertado/ false si ya exitia el usuario
     */
    public boolean insertData(String nombre, String apellido,
                              String usuario, String pass, String email) {
        //Llamamos al mto userExists que devolvera false si el usuario no existe en la BD
        if(!userExists(usuario)){
                SQLiteDatabase db_writable= getWritableDatabase();
                //OJO al escribir la sentencia
                String insert="INSERT INTO usuarios (nombre, apellido, usuario, pass, email) " +
                        "VALUES (\""+ nombre + "\",\""
                        + apellido + "\",\""
                        + usuario + "\",\""
                        + pass + "\",\""
                        + email + "\");";
                db_writable.execSQL(insert);
                db_writable.close();
                return true;
        }
        return false;
    }

    /**
     * Este metodo se encarga de comprobar si el usuario ya existe en la BD
     * @param user
     * @return true si ya existia/ false si no existe
     */
    public boolean userExists(String user) {
       Cursor cursor= getCursor();
        if (cursor != null && cursor.moveToFirst())
            do {
                if (user.compareTo(cursor.getString(3)) == 0) {
                    db_readable.close();
                    return true;
                }
            } while(cursor.moveToNext());
        db_readable.close();
        return false;
    }

    /**
     * **************  Metodo que consulta los datos de la BD ********************
     * @param pass contrasenia introducida  en la GUI
     * @param user nombre de usuario introducido en la GUI
     *
     * @return si el usuario y la contrasenia no coinciden
     * carga los datos del usuario en el DTO y devuelve este ultimo
     *  En caso contrario devuelve el dto con valor null
     */
    public UsuarioDTO login(String user, String pass){

        UsuarioDTO user_dto= new UsuarioDTO();
        Cursor cursor= getCursor();
        if (cursor!=null && cursor.moveToFirst()){
            do{
                //Comprobamos si el usuario y la contrasenia coinciden con los de algun usuario
                if ( pass.compareTo(cursor.getString(4)) == 0 &&  user.compareTo(cursor.getString(3)) == 0) {
                                user_dto.setNombre(cursor.getString(1));
                                user_dto.setApellido(cursor.getString(2));
                                user_dto.setUsuario(cursor.getString(3));
                                user_dto.setPass(cursor.getString(4));
                                user_dto.setEmail(cursor.getString(5));
                                db_readable.close();
                                return user_dto;
                }
            }while (cursor.moveToNext());
        }
        user_dto=null;
        db_readable.close();
        return user_dto;
     }

    /**
     *  ***************   Metodo que nos devuelve un Cursor ************************
     * @return  Cursor
     *
     * Como en principio no sabemos el num de registros que se recuperaran de la BD
     *         tenemos que usar un Cursor. El mto rawQuery nos devuelve un cursor.
     *          La clase Cursor nos servira para iterar por los registros  devueltos
     */
    private  Cursor getCursor(){
        db_readable = getReadableDatabase();
        String select = "SELECT * FROM usuarios";
        return db_readable.rawQuery(select, null);
    }

    /**
     ********************* METODOS DE AYUDA PARA EL DESARROLLO ********************
     * Los siguientes metodos  son de control  y  pruebas en desarrollo
     * No tiene funcionalidad en la aplicacion
     */
    //Metodo para ver todos los usuarios
     public void showAll(){
         Cursor cursor= getCursor();
         if (cursor!=null && cursor.moveToFirst())
             do {
                 System.out.println("1. Nombre: " + cursor.getString(1));
                 System.out.println("2. Apellido: " + cursor.getString(2));
                 System.out.println("3. Usuario: " + cursor.getString(3));
                 System.out.println("4. Password: " + cursor.getString(4));
                 System.out.println("5. Email: " + cursor.getString(5));
             } while (cursor.moveToNext());
        db_readable.close();
     }
//Metodo para borrar todos los usuarios
     public void delete(){
         SQLiteDatabase db_writable= getWritableDatabase();
         String borrar= "DELETE  FROM usuarios";
         db_writable.execSQL(borrar);
         System.out.println("Borrados todos los usuarios");
         db_writable.close();
     }
}
