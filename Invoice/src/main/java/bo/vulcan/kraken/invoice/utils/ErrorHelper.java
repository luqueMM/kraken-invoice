package bo.vulcan.kraken.invoice.utils;

import com.androidnetworking.error.ANError;

import bo.vulcan.kraken.invoice.data.model.errors.BadRequestException;
import io.reactivex.Single;
import io.reactivex.SingleSource;

public class ErrorHelper<T> {
    
    public SingleSource<? extends T> onError(Throwable e){
        if(e instanceof ANError){
            return Single.error(new BadRequestException(((ANError) e).getErrorCode(), ((ANError) e).getErrorDetail(), ((ANError) e).getErrorBody()));
        } else {
            return Single.error(new Exception(e.toString()));
        }
    }
}
