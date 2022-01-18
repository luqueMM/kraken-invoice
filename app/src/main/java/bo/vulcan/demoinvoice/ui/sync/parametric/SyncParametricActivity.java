package bo.vulcan.demoinvoice.ui.sync.parametric;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.demoinvoice.ui.list.ListActivity;
import bo.vulcan.kraken.invoice.MIntegration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyncParametricActivity extends AppCompatActivity implements Navigator {

    @BindView(R.id.tv_response)
    TextView tvResponse;

    @BindView(R.id.btn_sync_all_parametrics)
    Button btnSyncAllParametrics;

    @BindView(R.id.btn_sync_siat_currency_type)
    Button btnSyncSiatCurrencyType;

    @BindView(R.id.btn_siat_currency_type_list)
    Button btnGetSiatCurrencyType;

    @BindView(R.id.btn_sync_document_type)
    Button btnSyncDocumentType;

    @BindView(R.id.btn_document_type_list)
    Button btnGetDocumentType;

    @BindView(R.id.btn_sync_payment_method_type)
    Button btnSyncPaymentMethodType;

    @BindView(R.id.btn_payment_method_type_list)
    Button btnGetPaymentMethodType;

    @BindView(R.id.btn_sync_products)
    Button btnSyncProducts;

    @BindView(R.id.btn_products_list)
    Button btnGetProducts;

    @BindView(R.id.btn_sync_siat_products)
    Button btnSyncSiatProducts;

    @BindView(R.id.btn_siat_products_list)
    Button btnGetSiatProducts;

    @BindView(R.id.btn_sync_branch_activity_legend)
    Button btnSyncBranchActivityLegend;

    @BindView(R.id.btn_branch_activity_legend_list)
    Button btnGetBranchActivityLegend;

    @BindView(R.id.btn_sync_siat_measurement_unit)
    Button btnSyncSiatMeasurementUnit;

    @BindView(R.id.btn_siat_measurement_unit_list)
    Button btnGetSiatMeasurementUnit;

    private SyncParametricViewModelFactory viewModelFactory;
    private SyncParametricViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SyncParametricActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_parametrics);

        ButterKnife.bind(this);

        viewModelFactory = new SyncParametricViewModelFactory(MIntegration.MIntegrationObject.getInstance(getApplication()), this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SyncParametricViewModel.class);
    }

    @OnClick(R.id.btn_sync_all_parametrics)
    void onSyncAllParametricsButtonClicked() {
        viewModel.syncAllParametrics();
    }

    @OnClick(R.id.btn_sync_siat_currency_type)
    void onSyncSiatCurrencyTypeButtonClicked() {
        viewModel.syncSiatCurrencyType();
    }

    @OnClick(R.id.btn_siat_currency_type_list)
    void onGetSiatCurrencyTypeListButtonClicked() {
        viewModel.getListSiatCurrencyType();
    }

    @OnClick(R.id.btn_sync_document_type)
    void onSyncDocuemntTypeButtonClicked() {
        viewModel.syncDocumentType();
    }

    @OnClick(R.id.btn_document_type_list)
    void onGetDocuemntTypeListButtonClicked() {
        viewModel.getListDocumentType();
    }

    @OnClick(R.id.btn_sync_payment_method_type)
    void onSyncPaymentMethodTypeButtonClicked() {
        viewModel.syncPaymentMethodType();
    }

    @OnClick(R.id.btn_payment_method_type_list)
    void onGetPaymentMethodTypeListButtonClicked() {
        viewModel.getListPaymentMethodType();
    }

    @OnClick(R.id.btn_sync_products)
    void onSyncProductsButtonClicked() {
        viewModel.syncProducts();
    }

    @OnClick(R.id.btn_products_list)
    void onGetProductsListButtonClicked() {
        viewModel.getListProducts();
    }

    @OnClick(R.id.btn_sync_siat_products)
    void onSyncSiatProductsButtonClicked() {
        viewModel.syncSiatProducts();
    }

    @OnClick(R.id.btn_siat_products_list)
    void onGetSiatProductsListButtonClicked() {
        viewModel.getListSiatProducts();
    }

    @OnClick(R.id.btn_sync_branch_activity_legend)
    void onSyncBranchActivityLegendButtonClicked() {
        viewModel.syncBranchActivityLegend();
    }

    @OnClick(R.id.btn_branch_activity_legend_list)
    void onGetBranchActivityLegendListButtonClicked() {
        viewModel.getListBranchActivityLegend();
    }

    @OnClick(R.id.btn_sync_siat_measurement_unit)
    void onSyncSiatMeasurementUnitButtonClicked() {
        viewModel.syncSiatMeasurementUnit();
    }

    @OnClick(R.id.btn_siat_measurement_unit_list)
    void onGetSiatMeasurementUnitListButtonClicked() {
        viewModel.getListSiatMeasurementUnit();
    }

    @Override
    public void updateResponse(String response) {
        tvResponse.setText(response);
    }

    @Override
    public void navigateListActivity(List<String> list, String title) {
        Intent intent = ListActivity.newIntent(SyncParametricActivity.this);
        Bundle b = new Bundle();
        b.putStringArrayList("list", new ArrayList<String>(list)); //Your id
        b.putString("title", title);
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    @Override
    public void updateUI(UIStatus status) {
        switch (status){
            case IN_PROCESS:
                btnSyncAllParametrics.setEnabled(false);
                btnSyncSiatCurrencyType.setEnabled(false);
                btnGetSiatCurrencyType.setEnabled(false);
                btnSyncDocumentType.setEnabled(false);
                btnGetDocumentType.setEnabled(false);
                btnSyncPaymentMethodType.setEnabled(false);
                btnGetPaymentMethodType.setEnabled(false);
                btnSyncProducts.setEnabled(false);
                btnGetProducts.setEnabled(false);
                btnSyncSiatProducts.setEnabled(false);
                btnGetSiatProducts.setEnabled(false);
                btnSyncBranchActivityLegend.setEnabled(false);
                btnGetBranchActivityLegend.setEnabled(false);
                btnSyncSiatMeasurementUnit.setEnabled(false);
                btnGetSiatMeasurementUnit.setEnabled(false);
                break;
            case SUCCESS:
            case ERROR:
                btnSyncAllParametrics.setEnabled(true);
                btnSyncSiatCurrencyType.setEnabled(true);
                btnGetSiatCurrencyType.setEnabled(true);
                btnSyncDocumentType.setEnabled(true);
                btnGetDocumentType.setEnabled(true);
                btnSyncPaymentMethodType.setEnabled(true);
                btnGetPaymentMethodType.setEnabled(true);
                btnSyncProducts.setEnabled(true);
                btnGetProducts.setEnabled(true);
                btnSyncSiatProducts.setEnabled(true);
                btnGetSiatProducts.setEnabled(true);
                btnSyncBranchActivityLegend.setEnabled(true);
                btnGetBranchActivityLegend.setEnabled(true);
                btnSyncSiatMeasurementUnit.setEnabled(true);
                btnGetSiatMeasurementUnit.setEnabled(true);
                break;
        }
    }
}