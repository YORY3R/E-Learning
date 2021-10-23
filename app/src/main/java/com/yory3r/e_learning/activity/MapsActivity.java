package com.yory3r.e_learning.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener
{
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private ActivityMapsBinding activityMapsBinding;
    private int menuItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this,getString(R.string.mapbox_access_token));

        activityMapsBinding = DataBindingUtil.setContentView(this,R.layout.activity_maps);
        activityMapsBinding.setActivityMaps(this);

        activityMapsBinding.mapView.onCreate(savedInstanceState);
        activityMapsBinding.mapView.getMapAsync(this);
    }

    public Toolbar.OnMenuItemClickListener topAppBar = new Toolbar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            if(item.getItemId() == R.id.menuStreetMaps)
            {
                menuItem = 0;
            }
            else if(item.getItemId() == R.id.menuDarkMaps)
            {
                menuItem = 1;
            }
            else if(item.getItemId() == R.id.menuLightMaps)
            {
                menuItem = 2;
            }
            else if(item.getItemId() == R.id.menuOutdoorsMaps)
            {
                menuItem = 3;
            }
            else if(item.getItemId() == R.id.menuSatelliteMaps)
            {
                menuItem = 4;
            }
            else
            {
                menuItem = 5;
            }

            activityMapsBinding.mapView.getMapAsync(MapsActivity.this);

            return true;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain)
    {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted)
    {
        if(granted)
        {
            mapboxMap.getStyle(new Style.OnStyleLoaded()
            {
                @Override
                public void onStyleLoaded(@NonNull Style style)
                {
                    enableLocationComponent(style);
                }
            });
        }
        else
        {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap)
    {
        MapsActivity.this.mapboxMap = mapboxMap;
        String style = Style.MAPBOX_STREETS;

        if(menuItem == 0)
        {
            style = Style.MAPBOX_STREETS;
        }
        else if(menuItem == 1)
        {
            style = Style.DARK;
        }
        else if(menuItem == 2)
        {
            style = Style.LIGHT;
        }
        else if(menuItem == 3)
        {
            style = Style.OUTDOORS;
        }
        else if(menuItem == 4)
        {
            style = Style.SATELLITE;
        }
        else
        {
            style = Style.SATELLITE_STREETS;
        }

        mapboxMap.setStyle(style, new Style.OnStyleLoaded()
        {
            @Override
            public void onStyleLoaded(@NonNull Style style)
            {
                enableLocationComponent(style);
            }
        });
    }

    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle)
    {
        if(PermissionsManager.areLocationPermissionsGranted(this))
        {
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this).pulseEnabled(true).build();
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this,loadedMapStyle).locationComponentOptions(customLocationComponentOptions).build());
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.NORMAL);
        }
        else
        {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        activityMapsBinding.mapView.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        activityMapsBinding.mapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        activityMapsBinding.mapView.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        activityMapsBinding.mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        activityMapsBinding.mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        activityMapsBinding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        activityMapsBinding.mapView.onLowMemory();
    }

    @Override
    public void onBackPressed()
    {
        activityMapsBinding.mapView.onStop();
        finish();
        super.onBackPressed();
    }
}