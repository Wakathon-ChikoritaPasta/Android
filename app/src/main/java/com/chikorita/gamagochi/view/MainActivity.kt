package com.chikorita.gamagochi.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.data.MissionMapData
import com.chikorita.gamagochi.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


//MainActivity.kt
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    lateinit var mapView: MapView
    private lateinit var mapViewContainer : ViewGroup

    private var missionMapData = ArrayList<MissionMapData>()


    private var uLatitude : Double = 0.0
    private var uLongitude : Double = 0.0

    private lateinit var behavior : BottomSheetBehavior<ConstraintLayout>


    override fun initView() {

        // 위치 권한 허용
        getLocationPermission()

        initMapView()

        // 지도가 현재 위치로 표시
        setCurrentLocation()
        // 현재 위치 마커 표시
        addCustomMarker()
        // 하단 바텀 시트 표시
        setBottomSheet()
        // 위치 더미 데이터
        addDummyMapData()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun finish() {
        binding.activityMainKakaoMapView.removeView(mapView)
        super.finish()
    }

    private fun getLocationPermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            val userNowLocation : Location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
            uLatitude = userNowLocation.latitude
            uLongitude = userNowLocation.longitude
        } catch (e : NullPointerException) {
            Log.e("LOCATION_ERROR", e.toString())
            ActivityCompat.finishAffinity(this)

            finish()
        }
    }

    private fun initMapView() {
        mapView = MapView(this)
        mapViewContainer = binding.activityMainKakaoMapView
        mapViewContainer.addView(mapView)

        // 줌 레벨 변경
        mapView.setZoomLevel(1, true)
    }

    private fun setCurrentLocation() {
        val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)
        mapView.setMapCenterPoint(uNowPosition, true)

        Log.d("Map", "${uLatitude}, ${uLongitude}")
    }

    private fun addCustomMarker() {
        // 현재 위치에 마커 추가
        val marker = MapPOIItem()
        marker.apply {
            itemName = "현재 위치"   // 마커 이름
//            mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude) // 37.450191, 127.1297289
            mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)   // 좌표
            markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
            customImageResourceId = R.drawable.ic_custom_marker               // 커스텀 마커 이미지
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
            selectedMarkerType = MapPOIItem.MarkerType.RedPin // 클릭 시 마커 이미지
            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
            setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
        }
        mapView.addPOIItem(marker)
    }

    private fun addDummyMapData() {
        missionMapData.apply {
            add(MissionMapData("제2학생생활관", 37.4563, 127.13457))
            add(MissionMapData("법과대학", 37.44925, 127.1275))
            add(MissionMapData("AI 공학관", 37.45515, 127.1336))
            add(MissionMapData("가천관", 37.45035, 127.1298))
            add(MissionMapData("전기차 충전소", 37.452, 127.1305))
            add(MissionMapData("교육대학원", 37.4519, 127.1318))
            add(MissionMapData("AI 공학관", 37.45515, 127.1336))
            add(MissionMapData("글로벌센터", 37.4518, 127.1272))
        }
//        missionMapData.apply {
//            add(MissionMapData("정상에 서본 자", 37.4502726, 127.1297295))
//            add(MissionMapData("법을 잘 아는 자", 37.450191, 127.1297285))
//            add(MissionMapData("코딩의 신", 37.4502626, 127.1297291))
//        }

        Log.e("MapData", missionMapData.toString())

        for (data in missionMapData) {
            val marker = MapPOIItem()
            marker.apply {
                itemName = data.name
                mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
//                markerType = MapPOIItem.MarkerType.BluePin
                selectedMarkerType = MapPOIItem.MarkerType.RedPin
                userObject = "https://blog.kakaocdn.net/dn/mVAhb/btq6vToLTIw/Kv9wktJNgsmJOYKnqNd7kk/img.png"
            }
            mapView.addPOIItem(marker)
        }
    }

    private fun setBottomSheet() {
        behavior = BottomSheetBehavior.from(binding.activityMainBottom.dialogMapBehaviorView)

    }
}