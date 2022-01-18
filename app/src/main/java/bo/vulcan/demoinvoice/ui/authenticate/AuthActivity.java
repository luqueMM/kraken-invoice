package bo.vulcan.demoinvoice.ui.authenticate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.kraken.invoice.MIntegration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends AppCompatActivity implements Navigator {

    @BindView(R.id.user_edittext)
    EditText userEditText;

    @BindView(R.id.pass_edittext)
    EditText pwEditText;

    @BindView(R.id.response_auth)
    TextView tvResponse;

    private AuthViewModelFactory viewModelFactory;
    private AuthViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);

        viewModelFactory = new AuthViewModelFactory(MIntegration.MIntegrationObject.getInstance(getApplication()), this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel.class);
    }


    @OnClick(R.id.send_button)
    void onAuthButtonClicked() {

        hideKeyboard();

        // TODO add validation
        viewModel.authenticate(userEditText.getText().toString(), pwEditText.getText().toString());

        clearEditText();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void clearEditText() {
        userEditText.getText().clear();
        pwEditText.getText().clear();
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