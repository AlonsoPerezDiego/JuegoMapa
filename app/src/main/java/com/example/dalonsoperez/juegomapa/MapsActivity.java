package com.example.dalonsoperez.juegomapa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "gpslog";
    private GoogleMap mMap;
    private LocationManager mLocMgr;
    public Location tPizza, rRosalia, cap;
    private Button qr;
    //Minimo tiempo para updates en Milisegundos
    private static final long MIN_CAMBIO_DISTANCIA_PARA_UPDATES = (long) 5; // 5 metro
    //Minimo tiempo para updates en Milisegundos
    private static final long MIN_TIEMPO_ENTRE_UPDATES = 5000; // 5 sg

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Requiere permisos para Android 6.0
            Log.e(TAG, "No se tienen permisos necesarios!, se requieren.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
            return;
        } else {
            Log.i(TAG, "Permisos necesarios OK!.");
            // registra el listener para obtener actualizaciones
            mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIEMPO_ENTRE_UPDATES, MIN_CAMBIO_DISTANCIA_PARA_UPDATES, localizacion, Looper.getMainLooper());

        }
    }

    public LatLng miPosicion;
    public Location miLocalizacion = new Location("miLocalizacion");

    public LocationListener localizacion = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.v(TAG, "lat " + location.getLatitude() + "   lon " + location.getLongitude());
            miPosicion = new LatLng(location.getLatitude(), location.getLongitude());
            miLocalizacion.setLatitude(miPosicion.latitude);
            miLocalizacion.setLongitude(miPosicion.longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(TAG, "onStatusChanged()");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i(TAG, "onProviderEnabled()");
        }


        @Override
        public void onProviderDisabled(String provider) {
            Log.i(TAG, "onProviderDisabled()");
        }
    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng castelao = new LatLng(42.236341691474884, -8.714316040277481);
        LatLng telepizza = new LatLng(42.23674243457500, -8.712683245539665);
        LatLng telepizzab = new LatLng(42.23667173871569, -8.71264100074768);
        LatLng rotondaRosalia = new LatLng(42.23787355754216, -8.714309334754944);
        LatLng rotondaRosaliab = new LatLng(42.23793233682352, -8.714262396097183);
        LatLng capilla = new LatLng(42.23689812397819, -8.717962503433228);
        LatLng capillab = new LatLng(42.23682066912768, -8.7181106954813);
        BitmapDrawable bmd = (BitmapDrawable) getResources().getDrawable(R.drawable.marcador);
        Bitmap bm = bmd.getBitmap();
        Bitmap finalBitmap = Bitmap.createScaledBitmap(bm, 200, 200, false);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(finalBitmap)).position(castelao).title("Marker in Castelao"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(castelao, 18));

        Circle circulo1 = mMap.addCircle(new CircleOptions().center(telepizza).radius(22).strokeColor(Color.BLACK));
        Circle circulo1b = mMap.addCircle(new CircleOptions().center(telepizzab).radius(11).strokeColor(Color.BLACK));
        circulo1b.setVisible(false);
        Circle circulo2 = mMap.addCircle(new CircleOptions().center(rotondaRosalia).radius(24).strokeColor(Color.RED));
        Circle circulo2b = mMap.addCircle(new CircleOptions().center(rotondaRosaliab).radius(12).strokeColor(Color.RED));
        circulo2b.setVisible(false);
        Circle circulo3 = mMap.addCircle(new CircleOptions().center(capilla).radius(30).strokeColor(Color.BLUE));
        Circle circulo3b = mMap.addCircle(new CircleOptions().center(capillab).radius(15).strokeColor(Color.BLUE));
        circulo3b.setVisible(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        double latPunt = 0.000008595, lonPunt = 0.000012497;

        tPizza = new Location("telepizza");
        tPizza.setLatitude(telepizzab.latitude);
        tPizza.setLongitude(telepizzab.longitude);
        rRosalia = new Location("rosalia");
        rRosalia.setLatitude(rotondaRosaliab.latitude);
        rRosalia.setLongitude(rotondaRosaliab.longitude);
        cap = new Location("capilla");
        cap.setLatitude(capillab.latitude);
        cap.setLongitude(capillab.longitude);

        final Circle[] circulos = new Circle[6];
        circulos[0] = circulo1;
        circulos[1] = circulo1b;
        circulos[2] = circulo2;
        circulos[3] = circulo2b;
        circulos[4] = circulo3;
        circulos[5] = circulo3b;


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                compruebaPosicion(circulos);
            }
        });

        qr = (Button)findViewById(R.id.irAlQR);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaQR = new Intent(MapsActivity.this,QRActivity.class);
                startActivity(pantallaQR);
            }
        });
    }

    public void compruebaPosicion(Circle circulos[]){

        if(tPizza.distanceTo(miLocalizacion)<11){
            reducir(circulos[0], circulos[1]);
        }else if(rRosalia.distanceTo(miLocalizacion)<12){
            reducir(circulos[2], circulos[3]);
        }else if(cap.distanceTo(miLocalizacion)<15){
            reducir(circulos[4], circulos[5]);
        }

    }

    //sirve para hacer visible el circulo pequeño e invisible el grande.
    public void reducir(Circle circuloG, Circle circuloP){
        circuloG.setVisible(false);
        circuloP.setVisible(true);
    }

    public void cualTieneCerca(){

    }
}
