package bo.vulcan.demoinvoice.ui.invoice.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.demoinvoice.ui.list.ListActivity;
import bo.vulcan.demoinvoice.ui.sync.parametric.SyncParametricActivity;
import bo.vulcan.kraken.invoice.MIntegration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateInvoiceActivity extends AppCompatActivity implements Navigator {

    @BindView(R.id.tv_response)
    TextView tvResponse;

    private CreateInvoiceViewModelFactory viewModelFactory;
    private CreateInvoiceViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateInvoiceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        ButterKnife.bind(this);

        viewModelFactory = new CreateInvoiceViewModelFactory(MIntegration.MIntegrationObject.getInstance(getApplication()), this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateInvoiceViewModel.class);
    }

    @OnClick(R.id.btn_buy_and_sell)
    void onSiatCurrencyTypeButtonClicked() {
        viewModel.createInvoiceBuynAndSell();
    }

    @Override
    public void updateResponse(String response) {
        tvResponse.setText(response);
    }

    @Override
    public void navigateListActivity(List<String> list, String title) {

    }

    @Override
    public void updateUI(UIStatus status) {

    }
}