package com.wetech.aus.tennis.app.domain.courts.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.jeremyliao.liveeventbus.LiveEventBus
import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentMapsBinding
import dagger.hilt.android.AndroidEntryPoint
import permissions.dispatcher.ktx.PermissionsRequester
import permissions.dispatcher.ktx.constructPermissionsRequest
import timber.log.Timber


@AndroidEntryPoint
class MapsFragment :
    BaseFragment<FragmentMapsBinding>(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var mMapView: MapView

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(activity as Activity)
    }

    private lateinit var permissionsRequester: PermissionsRequester

    override fun onAttach(context: Context) {
        super.onAttach(context)
        permissionsRequester = constructPermissionsRequest(
            Manifest.permission.ACCESS_FINE_LOCATION,
            requiresPermission = ::getMyLocation
        )
    }

    companion object {
        const val MAP_FRAGMENT_RESUME = "MapsFragmentResume"
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
            mContext?.let {
                MapsInitializer.initialize(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mMapView.getMapAsync(this)
        return rootView
    }


    override fun onResume() {
        super.onResume()
        LiveEventBus.get(MAP_FRAGMENT_RESUME).post(0)
    }

    override fun onPause() {
        super.onPause()
        LiveEventBus.get(MAP_FRAGMENT_RESUME).post(1)
    }

    override fun init() {

    }

    @SuppressLint("MissingPermission")
    private fun getMyLocation() {
        Timber.i("enableMyLocation")
        if (!::map.isInitialized) return
        map.isMyLocationEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        //地图中心位置移动到定位位置，并设置地图缩放等级15
        fusedLocationClient.lastLocation.addOnSuccessListener {
            it?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setMinZoomPreference(15f)
        permissionsRequester.launch()
    }

}