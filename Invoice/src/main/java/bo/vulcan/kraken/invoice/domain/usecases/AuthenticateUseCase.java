package bo.vulcan.kraken.invoice.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.request.AuthenticationRequest;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.utils.ErrorHelper;
import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import io.reactivex.Single;

@Singleton
public class AuthenticateUseCase {

    private final DataManager mDataManager;

    @Inject
    public AuthenticateUseCase(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public Single<AuthenticationResponse> authenticate(String email, String password) {
        AuthenticationRequest.ServerLoginRequest request = new AuthenticationRequest.ServerLoginRequest(email, password);

        return ErrorsManager.startValidate(request.validate())
                .flatMap(response -> this.mDataManager.authentication(request))
                .onErrorResumeNext(new ErrorHelper()::onError);
    }
}