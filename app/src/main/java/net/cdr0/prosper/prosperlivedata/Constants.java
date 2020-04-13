package net.cdr0.prosper.prosperlivedata;

import androidx.lifecycle.Lifecycle;

public class Constants {

  // ------------------------------------------------------------------------------------------------------------------
  public static String lifecycleState(Lifecycle.State x) {
    switch(x) {
      case STARTED:           return "STARTED";
      case CREATED:           return "CREATED";
      case DESTROYED:         return "DESTROYED";
      case INITIALIZED:       return "INITIALIZED";
      case RESUMED:           return "RESUMED";

      default:
        assert(false);
        return "~UNDEFINED~";
    }
  }

  // ------------------------------------------------------------------------------------------------------------------

}
