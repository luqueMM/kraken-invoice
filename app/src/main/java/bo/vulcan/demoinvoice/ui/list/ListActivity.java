package bo.vulcan.demoinvoice.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bo.vulcan.demoinvoice.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.m_recycler_view)
    RecyclerView recyclerView;

    private ListAdapter recyclerViewAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, ListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        List<String> list = b.getStringArrayList("list");
        String title = b.getString("title");
        tvTitle.setText(title);

        initRecyclerView(list);
    }

    private void initRecyclerView(List<String> list) {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        if (list != null && !list.isEmpty()) {
            recyclerViewAdapter = new ListAdapter(list);
        } else {
            recyclerViewAdapter = new ListAdapter(new ArrayList<>());
        }
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}