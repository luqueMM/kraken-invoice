package bo.vulcan.kraken.invoice.data.model.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.SiatActivity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchActivityLegendAndSiatActivity {

    @Embedded
    private BranchActivityLegend branchActivityLegend;

    @Relation(
            parentColumn = "activity_id",
            entityColumn = "id"
    )
    private SiatActivity siatActivity;
}
