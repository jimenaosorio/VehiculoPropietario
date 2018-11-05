package cl.inacap.puntaarenas.vehiculopropietario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.inacap.puntaarenas.vehiculopropietario.modelo.Propietario;
import cl.inacap.puntaarenas.vehiculopropietario.modelo.Vehiculo;
import cl.inacap.puntaarenas.vehiculopropietario.modelo.VehiculosDatabaseHelper;

public class IngresarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
    }
    public void ingresarVehiculo(View view)
    {
        String patente=((EditText)findViewById(R.id.patente)).
                getText().toString();
        String marca=((EditText)findViewById(R.id.marca)).
                getText().toString();
        String modelo=((EditText)findViewById(R.id.modelo)).
                getText().toString();
        String rut=((EditText)findViewById(R.id.rut)).
                getText().toString();
        String nombre=((EditText)findViewById(R.id.nombre)).
                getText().toString();
        String celular=((EditText)findViewById(R.id.celular)).
                getText().toString();
        //validar
        if(patente.compareTo("")!=0 && marca.compareTo("")!=0
                && modelo.compareTo("")!=0 && rut.compareTo("")!=0
                && nombre.compareTo("")!=0 && celular.compareTo("")!=0)
        {
            //Tiene todos los datos
            Vehiculo v=new Vehiculo(patente,marca,modelo);
            Propietario p=new Propietario(rut,nombre,celular);
            v.setPropietario(p);
            VehiculosDatabaseHelper helper=
                    new VehiculosDatabaseHelper(this);
            helper.ingresarPropietario(p);
            helper.ingresarVehiculo(v);
            Toast.makeText(this,"Vehiculo ingresado",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this,"Falta completar informacion",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
