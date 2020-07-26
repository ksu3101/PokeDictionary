package com.swkang.pokedictionary.view.pokemap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.swkang.pokedictionary.R

class PokeMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    private val coordinate by navArgs<PokeMapsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        val markerBounds = LatLngBounds.Builder()
        val pokeInfos = coordinate.pokeMapInfos
        for (info in pokeInfos) {
            val targetCoordinates = LatLng(info.latitude, info.longitude)
            gMap.addMarker(MarkerOptions().position(targetCoordinates).title(info.name))
            markerBounds.include(targetCoordinates)
        }
        val padding = 50 // px
        gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(markerBounds.build(), padding))
    }
}