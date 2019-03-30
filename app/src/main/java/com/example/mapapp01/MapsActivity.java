package com.example.mapapp01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


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

        // マーカーを追加
        LatLng au = new LatLng(40.784372, 140.780566);mMap.addMarker(new MarkerOptions().position(au).title("青森大学の池"));
       mMap.addMarker(new MarkerOptions().position(new LatLng(40.833972, 140.725910)).title("もやし軍曹"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(40.828090, 140.722182)).title("ジブラルタル"));

        //情報つきマーカー
        MarkerOptions opt = new MarkerOptions();
        opt.position(new LatLng(40.805179, 140.782655));
        opt.title("ドラゴンカフェ");
        opt.snippet("いいセンスな名前");
        opt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        Marker m1 = mMap.addMarker(opt);
        m1.showInfoWindow();



        //拠点を移動 +　ズーム(2-21)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.784372, 140.780566),13));

//       現在地を表示
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this,new String[]{
                   Manifest.permission.ACCESS_FINE_LOCATION
           },1);
            return;
        }
        mMap.setMyLocationEnabled(true);

        //ロングクリック
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("ここ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))

                );
            }
        });


//        //クリック
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                mMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .title("ここ")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//
//                );
//            }
//        });


    }
}
