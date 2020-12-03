package id.ac.its.attendance.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Attendance.DataAgenda;
import id.ac.its.attendance.Response.Attendance.DataRuangan;
import id.ac.its.attendance.Response.Attendance.ResponseAgendaAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.AccessToken;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



//public class MainAttendanceFragment extends Fragment implements
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener,
//        LocationListener {

public  class MainAttendanceFragment extends Fragment{


    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x1;
    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x2;
    private Spinner agendaSpinner;
    private ArrayList<String> agendaNama;
    private HashMap<String, DataAgenda> agendaMap;
    private Button btnGo;
    private GoogleApiClient googleApiClient;
    private Location mylocation;
    private Location ruangLoc;
    private String idAgenda;
    private TokenManager tokenManager;
    RecyclerView recyclerView;
    ListAdapter adapter;
    LinearLayoutManager linearLayoutManager;

//    View view = inflater.inflate(R.layout.fragment_main_attendance, container, false);

    public MainAttendanceFragment() {
        // Required empty public constructor
    }
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_attendance, container, false);
        return view;

    }


}
