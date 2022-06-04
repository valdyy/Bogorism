package com.akhmad.bogorism2.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.data.response.PlaceResultItem
import com.akhmad.bogorism2.databinding.ActivityMapsBinding
import com.akhmad.bogorism2.ui.search.SearchViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val mapsViewModel: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val myHome = LatLng( -6.343215613160273, 106.84473975089031)
//        mMap.addMarker(
//            MarkerOptions()
//                .position(myHome)
//                .title("My Home")
//                .snippet("Blok. I No.175"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myHome, 15f))
//
//
//
//        mMap.setOnPoiClickListener { pointOfInterest ->
//            val poiMarker = mMap.addMarker(
//                MarkerOptions()
//                    .position(pointOfInterest.latLng)
//                    .title(pointOfInterest.name)
//            )
//            poiMarker?.showInfoWindow()
//        }

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        getMyLocation()

        getMapViewModel()

        getObserve()

    }

    private fun getObserve() {
        mapsViewModel._categoryList.observe(this){
            showMyMarker(it.placeResult)
        }
    }

    private fun getMapViewModel(){
        mapsViewModel.getMarker()
    }


    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            mMap.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val loc = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10f))
                }
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun showMyMarker(list : List<PlaceResultItem>) {

        list.forEach {
            val startLocation = LatLng(it.lat, it.jsonMemberLong)
            val title = it.placeName
            mMap.addMarker(
                MarkerOptions()
                    .position(startLocation)
                    .title(title)
            )

        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
            isGranted: Boolean ->
        if (isGranted){
            getMyLocation()
        }
    }
}