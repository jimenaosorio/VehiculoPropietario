package cl.inacap.puntaarenas.vehiculopropietario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cl.inacap.puntaarenas.vehiculopropietario.modelo.Vehiculo;
import cl.inacap.puntaarenas.vehiculopropietario.modelo.VehiculosDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VehiculosDatabaseHelper helper=new VehiculosDatabaseHelper(this);
        List<Vehiculo> vehiculos=helper.listaVehiculos();
        if(vehiculos==null){
            TextView mensaje=(TextView)findViewById(R.id.mensaje);
            mensaje.setText("No existen vehiculos ingresados");
        }
        else{
            ListView lista=(ListView) findViewById(R.id.lista_vehiculos);
            ArrayAdapter<Vehiculo> adapter=new ArrayAdapter<Vehiculo>
                    (this, android.R.layout.simple_list_item_1,
                            vehiculos);
            lista.setAdapter(adapter);
        }

    }
    public void ingresarClick(View view)
    {
        Intent intent=new Intent(this,
                IngresarActivity.class);
        startActivity(intent);
    }
}
