package bo.vulcan.demoinvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import bo.vulcan.demoinvoice.ui.authenticate.AuthActivity;
import bo.vulcan.demoinvoice.ui.invoice.list.InvoicesActivity;
import bo.vulcan.demoinvoice.ui.sync.companyData.SyncCompanyDataActivity;
import bo.vulcan.demoinvoice.ui.sync.parametric.SyncParametricActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_auth)
    void onAuthButtonClicked() {
        Intent intent = AuthActivity.newIntent(MainActivity.this);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btn_sync_company)
    void onSyncCompanyDataButtonClicked() {
        Intent intent = SyncCompanyDataActivity.newIntent(MainActivity.this);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btn_sync_parametric)
    void onSyncParametricButtonClicked() {
        Intent intent = SyncParametricActivity.newIntent(MainActivity.this);
        startActivity(intent);
//        finish();
    }

    @OnClick(R.id.btn_invoices)
    void onCreateInvoiceButtonClicked() {
//        Intent intent = CreateInvoiceActivity.newIntent(MainActivity.this);
        Intent intent = InvoicesActivity.newIntent(MainActivity.this);
        startActivity(intent);
//        finish();
    }
}