package bo.vulcan.demoinvoice.ui.sync.companyData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.kraken.invoice.MIntegration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyncCompanyDataActivity extends AppCompatActivity implements Navigator {

    @BindView(R.id.btn_sync_company_data)
    Button btnSyncCompanyData;

    @BindView(R.id.btn_sync_siat_config)
    Button btnSyncSiatConfig;

    @BindView(R.id.tv_response)
    TextView tvResponse;

    private SyncCompanyDataViewModelFactory viewModelFactory;
    private SyncCompanyDataViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SyncCompanyDataActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_company_data);

        ButterKnife.bind(this);

        viewModelFactory = new SyncCompanyDataViewModelFactory(MIntegration.MIntegrationObject.getInstance(getApplication()), this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SyncCompanyDataViewModel.class);
    }

    @OnClick(R.id.btn_sync_company_data)
    void onSyncCompanyDataButtonClicked() {
        viewModel.syncCompany();
    }

    @OnClick(R.id.btn_sync_siat_config)
    void onSyncSiatConfigButtonClicked() {
        viewModel.syncSiatConfig();
    }

    @Override
    public void updateResponse(String response) {
        tvResponse.setText(response);
    }

    @Override
    public void navigateListActivity(List<String> list, String title) { }

    @Override
    public void updateUI(UIStatus status) {

        switch (status){
            case IN_PROCESS:
                btnSyncCompanyData.setEnabled(false);
                btnSyncSiatConfig.setEnabled(false);
                break;
            case SUCCESS:
            case ERROR:
                btnSyncCompanyData.setEnabled(true);
                btnSyncSiatConfig.setEnabled(true);
                break;
        }
    }
}