package bo.vulcan.kraken.invoice.utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ErrorsManager {
    private List<String> errors;


    public ErrorsManager() {
        this.errors = new ArrayList<>();
    }

    public void check(String field, String error) {
        if (error != null) {
            this.errors.add(field + " " + error);
        }
    }

    public String getErrors() {
        if (this.errors.isEmpty()) {
            return null;
        }

        String errors = "";

        for (String value : this.errors) {
            errors += value + "\n";
        }

        return errors.substring(0, errors.length()-1);
    }

    public List<String> getList(){
        return errors;
    }

    public void addAll(List<String> list){
        errors.addAll(list);
    }

    public static Single<Boolean> startValidate(String errors){
        return Single.create(subscriber -> {
            if (errors == null || errors.isEmpty()){
                subscriber.onSuccess(true);
            } else {
                subscriber.onError(new IllegalArgumentException("Validation error in fields: "+errors));
            }
        });
    }
}
