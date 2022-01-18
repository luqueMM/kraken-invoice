package bo.vulcan.demoinvoice.ui.invoice.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.demoinvoice.ui.invoice.create.CreateInvoiceActivity;
import bo.vulcan.kraken.invoice.MIntegration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoicesActivity extends AppCompatActivity implements Navigator {


    private InvoicesViewModelFactory viewModelFactory;
    private InvoicesViewModel viewModel;

    private InvoiceListAdapter recyclerViewAdapter;

    @BindView(R.id.invoice_recycler_view)
    RecyclerView recyclerView;

    public static Intent newIntent(Context context) {
        return new Intent(context, InvoicesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initRecyclerView();

        viewModelFactory = new InvoicesViewModelFactory(MIntegration.MIntegrationObject.getInstance(getApplication()), this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InvoicesViewModel.class);

        viewModel.invoices().observe(this, recyclerViewAdapter::updateInvoiceList);
    }

    @OnClick(R.id.fab)
    void onAddInvoiceButtonClicked() {
        Intent intent = CreateInvoiceActivity.newIntent(InvoicesActivity.this);
        startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new InvoiceListAdapter(new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void updateResponse(String response) {

    }

    @Override
    public void navigateListActivity(List<String> list, String title) {

    }

    @Override
    public void updateUI(UIStatus status) {

    }
}