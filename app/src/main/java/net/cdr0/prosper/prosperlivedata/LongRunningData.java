package net.cdr0.prosper.prosperlivedata;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import static net.cdr0.prosper.prosperlivedata.Constants.lifecycleState;


public class LongRunningData {

  private static final String TAG = "LongRunningData";

  public static void bindLongRunningDataIn(LifecycleOwner lifecycleOwner, LocationListener locationListener, Context context /*, other-params */) {
    new BoundLongRunningData(lifecycleOwner, locationListener, context /*, other-params */);
  }

  @SuppressWarnings("MissingPermission")
  static class BoundLongRunningData implements LifecycleObserver, LocationListener {

    private         LocationManager   locationManager;
    private         LocationListener registeredLocationListener;
    private final   Context           context;

    public BoundLongRunningData(LifecycleOwner lifecycleOwner, LocationListener locationListener, Context context /*, other-params */) {
      this.context                    = context;
      this.registeredLocationListener = locationListener;

      lifecycleOwner.getLifecycle().addObserver(this);

      locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    // ==================================================================================================================
    // LifecycleObserver
    // ==================================================================================================================

    // NOTE: There is `@OnLifecycleEvent(Lifecycle.Event.ON_ANY)`, which sends a 2nd parameter to the method
    // public void onAny(LifecycleOwner lifecycleOwner, ?something-here?) { ... }


    // The states it might be in:
    // Lifecycle.State.STARTED;
    // Lifecycle.State.CREATED;
    // Lifecycle.State.DESTROYED;
    // Lifecycle.State.INITIALIZED;
    // Lifecycle.State.RESUMED;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onCreate, state: " + lifecycleState(state));

      // TODO: Will only be called once for the activity. Initialize things that do not require blocking.
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onStart, state: " + lifecycleState(state));

      // TODO: Start your async op (or you could do it in onResume)
      // TODO:    The diff is that the UI is not setup here, but it will be in onResume
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onResume, state: " + lifecycleState(state));

//      if (lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//        // The UI is setup
//      }

      // TODO: Start your async op (if you did not in onStart)
      // TODO:    The diff is that the UI is setup here, but not in onStart, but onStart is earlier.
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onPause, state: " + lifecycleState(state));

      // TODO: stop or deregister things (or do it in onStop)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onStop, state: " + lifecycleState(state));

      // TODO: stop or deregister things (if you have not done it in onPause)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner lifecycleOwner) {
      Lifecycle.State state = lifecycleOwner.getLifecycle().getCurrentState();
      Log.d(TAG, "onDestroy, state: " + lifecycleState(state));

      // TODO: Clean up. The Activity is NOT comming back.
    }

    // from LocationListener
    @Override
    public void onLocationChanged(Location location) {
      // TODO: Log params

      if (registeredLocationListener != null) {
        registeredLocationListener.onLocationChanged(location);
      }
    }

    // from LocationListener
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      // TODO: Log params

      if (registeredLocationListener != null) {
        registeredLocationListener.onStatusChanged(provider, status, extras);
      }
    }

    // from LocationListener
    @Override
    public void onProviderEnabled(String provider) {
      // TODO: Log params

      if (registeredLocationListener != null) {
        registeredLocationListener.onProviderEnabled(provider);
      }
    }

    // from LocationListener
    @Override
    public void onProviderDisabled(String provider) {
      // TODO: Log params

      if (registeredLocationListener != null) {
        registeredLocationListener.onProviderDisabled(provider);
      }
    }


  }


}
