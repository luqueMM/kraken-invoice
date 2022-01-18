package bo.vulcan.kraken.invoice;

import android.app.Application;

import bo.vulcan.kraken.invoice.di.component.AppComponent;
import bo.vulcan.kraken.invoice.di.component.DaggerAppComponent;
import bo.vulcan.kraken.invoice.di.module.AppModule;

public class DaggerWrapper {
    public static AppComponent component;

//    static {
//        System.out.println("android library started");
//        component = DaggerAppComponent
//                .builder()
//                .appModule(new AppModule())
//                .build();
//
//        System.out.println("android library ended");
//    }
//
//    public static void main(String[] args){
//        System.out.println("library was running standlone");
//    }

    public static AppComponent getComponent(Application application) {
        if (component == null) {
            component = DaggerAppComponent
                    .builder()
                    .appModule(new AppModule(application))
                    .build();
        }
        return component;
    }
}
