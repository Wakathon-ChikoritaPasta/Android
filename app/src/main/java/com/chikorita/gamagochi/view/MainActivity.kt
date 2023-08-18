package com.chikorita.gamagochi.view

import android.Manifest
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
import android.app.Activity
import android.bluetooth.BluetoothClass.Device.Major
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityMainBinding
import com.chikorita.gamagochi.model.MajorRanker
import com.chikorita.gamagochi.model.SchoolRanker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


//MainActivity.kt
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    lateinit var mapView: MapView
    private lateinit var mapViewContainer : ViewGroup


    private var uLatitude : Double = 0.0
    private var uLongitude : Double = 0.0

    private lateinit var behavior : BottomSheetBehavior<ConstraintLayout>

    lateinit var SchoolRankerArray : ArrayList<SchoolRanker>
    lateinit var MajorRankerArray : ArrayList<MajorRanker>

    val bottomDialog = binding.activityMainBottom


    override fun initView() {

        initMapView()
        getLocationPermission()
        setCurrentLocation()
        addCustomMarker()
        setBottomSheet()
        initSchoolRanker()
        initMajorRanker()
        setRankerBackground()
    }

    private fun initClickListener(){
        val intent = Intent(this,RankingActivity::class.java)

        bottomDialog.schoolRankingBtn.setOnClickListener {

        }
    }

    private fun setRankerBackground(){


        // 1등일 때
        if(SchoolRankerArray[0].nickName == "로건"){
            bottomDialog.rankUser0.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }else{
            bottomDialog.rankUser0.root.background = null
            bottomDialog.rankUser1.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }

        // 꼴등일 때
        if(SchoolRankerArray[2].nickName == "로건"){
            bottomDialog.rankUser2.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }else{
            bottomDialog.rankUser2.root.background = null
            bottomDialog.rankUser1.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }

        if(MajorRankerArray[0].major == "소프트웨어학과"){
            bottomDialog.rankMajor0.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }else{
            bottomDialog.rankMajor0.root.background = null
            bottomDialog.rankMajor1.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }

        if(MajorRankerArray[2].major == "소프트웨어학과"){
            bottomDialog.rankMajor2.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }else{
            bottomDialog.rankMajor2.root.background = null
            bottomDialog.rankMajor2.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
        }

    }

    private fun initSchoolRanker() {
        SchoolRankerArray = arrayListOf(
            SchoolRanker(16, "로건", "무당신", 3400),
            SchoolRanker(17, "마라", "무당짱", 2300),
            SchoolRanker(18, "코코아", "무당이", 1700)
        )
    }
    private fun initMajorRanker() {
        MajorRankerArray = arrayListOf(
            MajorRanker(1, "소프트웨어학과", 450000),
            MajorRanker(2, "인공지능학과", 390000),
            MajorRanker(3, "컴퓨터공학과", 380000)
        )
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.finishAffinity(this)
            } else {
                ActivityCompat.finishAffinity(this)
            }

            finish()
        }
    }

    private fun initMapView() {
        mapView = MapView(this)
        mapViewContainer = binding.activityMainKakaoMapView
        mapViewContainer.addView(mapView)
    }

    private fun setCurrentLocation() {
        val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)
        mapView.setMapCenterPoint(uNowPosition, true)
    }

    private fun addCustomMarker() {
        // 서울시청에 마커 추가
        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울시청"   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)   // 좌표
            markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
            customImageResourceId = R.drawable.ic_custom_marker               // 커스텀 마커 이미지
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
            customSelectedImageResourceId = R.drawable.ic_custom_marker       // 클릭 시 커스텀 마커 이미지
            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
            setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
        }
        mapView.addPOIItem(marker)
    }

    private fun setBottomSheet() {
        behavior = BottomSheetBehavior.from(binding.activityMainBottom.dialogMapBehaviorView)



    }
}