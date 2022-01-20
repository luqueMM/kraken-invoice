package bo.vulcan.kraken.invoice.di.component;

import android.content.Context;

import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.MIntegrationImpl;
import bo.vulcan.kraken.invoice.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MIntegrationImpl MIntegrationImpl);

}
