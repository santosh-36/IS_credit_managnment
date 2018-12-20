package com.santosh.androiddev.is_sparks_cm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FragmentUser extends Fragment {
    WebView wv;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doJob(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, null);
    }
    private void doJob(View view) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                wv =  view.findViewById(R.id.wvuser);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setFocusable(true);
                wv.setFocusableInTouchMode(true);
                wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                wv.getSettings().setDomStorageEnabled(true);
                wv.getSettings().setAppCacheEnabled(true);
                wv.getSettings().setDatabaseEnabled(true);
                wv.getSettings().setSupportZoom(true);
                wv.getSettings().setBuiltInZoomControls(true);
                wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv.loadUrl("http://santosh36.ga/internship/androiduser.php");
                wv.setWebViewClient(new WebViewClient());
            }
            else{
                Toast.makeText(getContext(), "Cannot connect to internet.Connect to internet and reload the app.", Toast.LENGTH_LONG).show();
            }
        }
    }
