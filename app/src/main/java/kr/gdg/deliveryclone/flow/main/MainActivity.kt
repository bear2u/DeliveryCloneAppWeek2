package kr.gdg.deliveryclone.flow.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kr.gdg.deliveryclone.R
import kr.gdg.deliveryclone.model.MainItemModel
import kr.gdg.deliveryclone.mvp.BaseMvpActivity
import android.support.v7.widget.RecyclerView
import android.support.annotation.DimenRes
import android.content.Context
import android.graphics.Rect
import android.support.annotation.NonNull
import android.view.View
import android.widget.TextView
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import android.location.Criteria
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kr.gdg.deliveryclone.utils.CheckPermission


class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View, NavigationView.OnNavigationItemSelectedListener, LocationListener {
    private lateinit var locationManager : LocationManager
    private lateinit var provider: String


    override var mPresenter: MainContract.Presenter = MainPresenter()

    val items = arrayOf(
            MainItemModel(0, R.drawable.bdt_home_a_ctgr_all),
            MainItemModel(1, R.drawable.bdt_home_a_ctgr_seasonal, R.drawable.bdt_btn_brown),
            MainItemModel(2, R.drawable.bdt_home_a_ctgr_chicken),
            MainItemModel(3, R.drawable.bdt_home_a_ctgr_chinese),
            MainItemModel(4, R.drawable.bdt_home_a_ctgr_pizza),
            MainItemModel(5, R.drawable.bdt_home_a_ctgr_bossam),
            MainItemModel(6, R.drawable.bdt_home_a_ctgr_burger),
            MainItemModel(7, R.drawable.bdt_home_a_ctgr_japanese)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        checkGPS()
        initMyLocation()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        val mTitle = toolbar.findViewById<TextView>(R.id.toolbar_title)
        mTitle.text = getString(R.string.app_name)
        toggle.syncState()
        toolbar.setNavigationIcon(R.drawable.bdt_navi_side)
        nav_view.setNavigationItemSelectedListener(this)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager = GridLayoutManager(getContext(), 2)
        mainRecyclerView.adapter = MainRecylerViewAdapter(items, getContext())
        val itemDecoration = ItemOffsetDecoration(getContext(), R.dimen.item_offset)
        mainRecyclerView.addItemDecoration(itemDecoration)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun updateView(count: Int) {
//        resultTxt.text = count.toString()
    }

    private fun initMyLocation() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            updateMyLocation()
        } else {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1);
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions.size == 1 &&
                permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }else {
            updateMyLocation()
        }
    }


    override fun onLocationChanged(p0: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    internal inner class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

        constructor(@NonNull context: Context, @DimenRes itemOffsetId: Int) : this(context.getResources().getDimensionPixelSize(itemOffsetId)) {}

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                           state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
        }
    }

    fun checkGPS() {
        val service = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

// check if enabled and if not send user to the GSP settings
// Better solution would be to display a dialog and suggesting to
// go to the settings
        if (!enabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    fun updateMyLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        provider = locationManager.getBestProvider(criteria, false);
        val location = locationManager.getLastKnownLocation(provider);
        if(location != null)
            print("${location.latitude},${location.longitude}")
    }


}
