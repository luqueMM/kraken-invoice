package bo.vulcan.demoinvoice.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bo.vulcan.demoinvoice.R;
import timber.log.Timber;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<String> list;

    public ListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView commentText = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.m_row, parent, false);
        return new ViewHolder(commentText);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String item = list.get(position);
//        if (item.isSyncPending()) {
//            holder.contentRow.setTextColor(Color.LTGRAY);
//        } else {
//            holder.contentRow.setTextColor(Color.BLACK);
//        }
        holder.contentRow.setText((position+1)+": " + item);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void updateCommentList(List<String> newList) {
        Timber.d("Got new comments " + newList.size());
        this.list.clear();
        this.list.addAll(newList);
        notifyDataSetChanged();
    }

    /**
     * View holder for shopping list items of this adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView contentRow;

        public ViewHolder(final TextView contentRow) {
            super(contentRow);
            this.contentRow = contentRow;
        }
    }
}
