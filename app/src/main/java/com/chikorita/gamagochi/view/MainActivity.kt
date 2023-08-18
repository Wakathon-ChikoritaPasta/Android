package com.chikorita.gamagochi.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseBindingActivity
import com.chikorita.gamagochi.data.MissionMapData
import com.chikorita.gamagochi.databinding.ActivityMainBinding
import com.chikorita.gamagochi.view.ranking.RankingActivity
import com.chikorita.gamagochi.viewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.StandardCharsets

//MainActivity.kt
class MainActivity : BaseBindingActivity<ActivityMainBinding>(R.layout.activity_main){
    private val viewModel: MainViewModel by viewModels()

    lateinit var mapView: MapView
    private lateinit var mapViewContainer : ViewGroup

    private var missionMapData = ArrayList<MissionMapData>()


    private var uLatitude : Double = 0.0
    private var uLongitude : Double = 0.0

    private lateinit var behavior : BottomSheetBehavior<ConstraintLayout>


    private var serviceIntent: Intent? = null
    private lateinit var handler: Handler


    override fun initView() {
        //binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

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

        setRankerBackground()
        initClickListener()
    }
    private fun startLocationUpdates() {
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(locationUpdateRunnable, 60000) // 10초마다 실행
    }

    private val locationUpdateRunnable = object : Runnable {
        override fun run() {
            sendLocationToServer()

            // 다음 위치 정보 전송 작업을 예약
            handler.postDelayed(this, 10000) // 10초마다 실행
        }
    }

    private fun sendLocationToServer() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            val userNowLocation: Location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
            val latitude = userNowLocation.latitude
            val longitude = userNowLocation.longitude

            // 실제 서버 통신은 여기에 구현
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    // 서버로 위치 정보를 전송하는 코드를 구현
                    // sendLocationInfoToServer(latitude, longitude)
                }
            }

            Log.d("LOCATION_UPDATE", "Latitude: $latitude, Longitude: $longitude")
        } catch (e: SecurityException) {
            Log.e("LOCATION_ERROR", e.toString())
            Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("LOCATION_ERROR", e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 핸들러에 예약된 작업 제거
        handler.removeCallbacks(locationUpdateRunnable)
    }

    private fun initClickListener(){
        val bottomDialog = binding.activityMainBottom

        val intent = Intent(this, RankingActivity::class.java)
        val bundle = Bundle()


        bottomDialog.schoolRankingBtn.setOnClickListener {
            bundle.putString("key","school")
            intent.putExtra("bundle",bundle)
            startActivity(intent)
        }
        bottomDialog.majorRankingBtn.setOnClickListener {
            bundle.putString("key","major")
            intent.putExtra("bundle",bundle)
            startActivity(intent)
        }

        binding.activityMainFeedBtn.setOnClickListener {
            startLocationUpdates()
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun setRankerBackground() {
        val bottomDialog = binding.activityMainBottom

        with(viewModel) {
            if (SchoolRankerArray[0].nickName == "로건") {
                bottomDialog.rankUser0.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
                bottomDialog.rankUser1.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankUser2.root.setBackgroundResource(R.drawable.border_rectangle)

            } else if (SchoolRankerArray[2].nickName == "로건") {
                bottomDialog.rankUser0.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankUser1.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankUser2.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
            } else {
                bottomDialog.rankUser0.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankUser1.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
                bottomDialog.rankUser2.root.setBackgroundResource(R.drawable.border_rectangle)
            }


            if (MajorRankerArray[0].major == "소프트웨어") {
                bottomDialog.rankMajor0.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
                bottomDialog.rankMajor1.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankMajor2.root.setBackgroundResource(R.drawable.border_rectangle)

            } else if (MajorRankerArray[2].major == "소프트웨어") {
                bottomDialog.rankMajor0.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankMajor1.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankMajor2.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
            } else {
                bottomDialog.rankMajor0.root.setBackgroundResource(R.drawable.border_rectangle)
                bottomDialog.rankMajor1.root.setBackgroundResource(R.drawable.gradation_rectangle_2)
                bottomDialog.rankMajor2.root.setBackgroundResource(R.drawable.border_rectangle)
            }
        }

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

    private fun sendInfoToServer(){
        // 서버 통신을 별도의 쓰레드에서 처리

        Thread(Runnable {

            getLocationPermission()


        }).start()
    }
}