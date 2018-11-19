package com.moriahdp.baseapplication.baseclasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.moriahdp.baseapplication.R;


public abstract class BaseFragment extends Fragment {

    private Context context;
//    protected BasePresenter mBasePresenter;
    protected ConnectivityManager mConnectivityManager;
    private View mLoadingOverlay;
    private View mErrorConnectionFullScreenView;
    private View mErrorLoadingDataFullScreenView;
    private View mEmptyStateFullScreenView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout mMainContainer = rootView.findViewById(R.id.main_fragment_container);
        mLoadingOverlay = rootView.findViewById(R.id.pb_base);
        mErrorConnectionFullScreenView = rootView.findViewById(R.id.error_connection);
        mErrorLoadingDataFullScreenView = rootView.findViewById(R.id.error_loading_data);
        mEmptyStateFullScreenView = rootView.findViewById(R.id.empty_state);
        View content = onCreateEventView(inflater, null, savedInstanceState);
        mMainContainer.addView(content);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    //    public void setBasePresenter(BasePresenter basePresenter) {
//        mBasePresenter = basePresenter;
//    }

    //TODO implementar chequeo de conexion y si el modo Avion esta activado

    public void showEmptyStateLayout(final OnNoObjectiveCallback onNoObjectiveCallback) {
        mEmptyStateFullScreenView.setVisibility(View.VISIBLE);
        Button retryBtn = mEmptyStateFullScreenView.findViewById(R.id.btn_privacy_state);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoObjectiveCallback.onNoObjectiveCallback();
            }
        });
    }

    public void hideEmptyStateLayout() {
        mEmptyStateFullScreenView.setVisibility(View.GONE);
    }

    public void showErrorConectionFullScreen(final OnErrorConnectionCallback onErrorConectionCallback) {
        mErrorConnectionFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) mErrorConnectionFullScreenView.findViewById(R.id.rl_continue_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorConectionCallback.onErrorConnectionRetry();
            }
        });
    }

    public void hideErrorConectionFullScreen() {
        mErrorConnectionFullScreenView.setVisibility(View.GONE);
    }

    public void showErrorLoadingDataFullScreen(final OnErrorLoadingDataCallback onErrorLoadingDataCallback) {
        mErrorLoadingDataFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) mErrorLoadingDataFullScreenView.findViewById(R.id.rl_retry_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorLoadingDataCallback.onErrorLadingDataRetry();
            }
        });
    }

    public void hideErrorLoadingDataFullScreen() {
        mErrorConnectionFullScreenView.setVisibility(View.GONE);
    }

    public void showLoadingOverlay() {
        if (!isLoadingOverlayShowing()) {
            mLoadingOverlay.bringToFront();
            mLoadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingOverlay() {
        if (isLoadingOverlayShowing()) {
            mLoadingOverlay.setVisibility(View.GONE);
        }
    }

    private boolean isLoadingOverlayShowing() {
        return mLoadingOverlay.getVisibility() == View.VISIBLE;
    }

    protected abstract View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public interface OnErrorConnectionCallback {
        void onErrorConnectionRetry();
    }

    public interface OnNoObjectiveCallback {
        void onNoObjectiveCallback();
    }

    public interface OnErrorLoadingDataCallback {
        void onErrorLadingDataRetry();
    }

}
