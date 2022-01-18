package bo.vulcan.demoinvoice.ui.base;

import java.util.List;

public interface Navigator {
    void updateResponse(String response);

    void navigateListActivity(List<String> list, String title);

    void updateUI(UIStatus status);
}

