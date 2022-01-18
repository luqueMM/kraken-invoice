/*
package bo.vulcan.kraken.invoice.data.remote.retrofit;

import java.io.IOException;

import bo.vulcan.kraken.invoice.data.model.request.AuthenticationRequest;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import retrofit2.Response;
import timber.log.Timber;

class AuthenticationServiceRemote {

    public void authenticate(AuthenticationRequest request) throws IOException, RemoteException {
        AuthenticationApi service = RemoteService.getInstance().getAuthenticationApi();

        // Remote call can be executed synchronously since the job calling it is already backgrounded.
        Response<AuthenticationResponse> response = service.authentication(request).execute();

        if (response == null || !response.isSuccessful() || response.errorBody() != null) {
            throw new RemoteException(response);
        }

        Timber.d("successful remote response: " + response.body());
    }
}
*/
