package cl.inacap.puntaarenas.vehiculopropietario.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class VehiculosDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="vehiculos.db";
    private static final int DB_VERSION=1;

    public VehiculosDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlPropietario="CREATE TABLE PROPIETARIOS("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"RUT TEXT, NOMBRE TEXT, CELULAR TEXT);";
        db.execSQL(sqlPropietario);
        String sqlVehiculo="CREATE TABLE VEHICULOS("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"PATENTE TEXT, MARCA TEXT, MODELO TEXT, "
                +"RUT_PROPIETARIO TEXT)";
        db.execSQL(sqlVehiculo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int anterior, int nueva) {
        db.execSQL("DROP TABLE IF EXISTS PROPIETARIOS");
        db.execSQL("DROP TABLE IF EXISTS VEHICULOS");
        onCreate(db);
    }
    public void ingresarPropietario(Propietario propietario)
    {
        ContentValues valores=new ContentValues();
        valores.put("RUT",propietario.getRut());
        valores.put("NOMBRE",propietario.getNombre());
        valores.put("CELULAR",propietario.getCelular());
        getWritableDatabase().insert("PROPIETARIOS",
                null, valores);
    }
    public void ingresarVehiculo(Vehiculo vehiculo)
    {
        ContentValues valores=new ContentValues();
        valores.put("PATENTE",vehiculo.getPatente());
        valores.put("MARCA",vehiculo.getMarca());
        valores.put("MODELO",vehiculo.getModelo());
        valores.put("RUT_PROPIETARIO",vehiculo.getPropietario().getRut());
        getWritableDatabase().insert("VEHICULOS",
                null,valores);
    }
    public Propietario buscarPropietario(String rut)
    {
        Propietario propietario=null;
        String sqlTxt="SELECT NOMBRE, CELULAR FROM PROPIETARIOS "
                +"WHERE RUT = ?";
        Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,
                new String[]{rut});
        cursor.moveToFirst();
        String nombre=cursor.getString(0);
        String celular=cursor.getString(1);
        propietario=new Propietario(rut,nombre,celular);
        cursor.close();
        return propietario;
    }
    public List<Vehiculo> listaVehiculos()
    {
        List<Vehiculo> vehiculos=new ArrayList<>();
        String sqlTxt="SELECT PATENTE, MARCA, MODELO, RUT_PROPIETARIO "
                +"FROM VEHICULOS";
        try{
            Cursor cursor=getReadableDatabase().rawQuery(sqlTxt,
                    null);
            String rut;
            Propietario p;
            Vehiculo v;
            cursor.moveToFirst();
            do{
                v=new Vehiculo(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                rut=cursor.getString(3);
                p=buscarPropietario(rut);
                v.setPropietario(p);
                vehiculos.add(v);
            }while(cursor.moveToNext());
            cursor.close();
        }catch (CursorIndexOutOfBoundsException e){
            vehiculos=null;
        }
        return vehiculos;
    }

}
