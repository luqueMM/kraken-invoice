package bo.vulcan.demoinvoice.ui.invoice.list;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bo.vulcan.demoinvoice.R;
import bo.vulcan.kraken.invoice.data.model.db.InvoicePdf;
import timber.log.Timber;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {

    private final List<InvoicePdf> invoices;

    public InvoiceListAdapter(List<InvoicePdf> list) {
        this.invoices = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView invoiceText = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_row, parent, false);
        return new ViewHolder(invoiceText);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final InvoicePdf item = invoices.get(position);
        if (item.isSyncPending()) {
            holder.contentRow.setTextColor(Color.LTGRAY);
        } else {
            holder.contentRow.setTextColor(Color.BLACK);
        }
        holder.contentRow.setText((position+1)+": " + item);
    }

    @Override
    public int getItemCount() {
        return invoices == null ? 0 : invoices.size();
    }

    public void updateInvoiceList(List<InvoicePdf> newList) {
        Timber.d("Got new invoices " + newList.size());
        this.invoices.clear();
        this.invoices.addAll(newList);
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
