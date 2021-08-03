package com.wetech.aus.tennis.app.domain.courts.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jeremyliao.liveeventbus.LiveEventBus
import com.permissionx.guolindev.PermissionX
import com.poker.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentMapsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(), OnMapReadyCallback {
    private lateinit var mMapView: MapView
    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        const val MAP_FRAGMENT_RESUME = "MapsFragmentResume"
    }

    @SuppressLint("MissingPermission")
    override fun init() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                mMap.addMarker(
                    MarkerOptions().position(LatLng(it.latitude, it.longitude))
                )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_maps, container, false)
        mMapView = rootView.findViewById<View>(R.id.mapView) as MapView
        mMapView.onCreate(savedInstanceState)
        mMapView.onResume()
        try {
            MapsInitializer.initialize(mContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mMapView.getMapAsync(this)
        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Timber.d("onMapReady")
        mMap = googleMap
    }

    override fun onResume() {
        super.onResume()
        LiveEventBus.get(MAP_FRAGMENT_RESUME, Int::class.java).post(0)
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        PermissionX.init(this)
            .permissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .request { allGranted, _, _ ->
                if (allGranted) {
                    getLocation()
                }
            }

    }


    override fun onPause() {
        super.onPause()
        LiveEventBus.get(MAP_FRAGMENT_RESUME, Int::class.java).post(1)
    }


    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        fusedLocationClient.requestLocationUpdates(
            createLocationRequest(),
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return
                    for (location in locationResult.locations) {
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    location.latitude,
                                    location.longitude
                                ), 13F
                            )
                        )
                    }
                }
            },
            Looper.getMainLooper()
        )
    }
}