//package bo.vulcan.kraken.invoice.domain.services.siatapi.service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Collections;
//
//import javax.inject.Singleton;
//
//import bo.gob.impuestos.sfe.codes.RespuestaVerificarNit;
//import bo.gob.impuestos.sfe.codes.ServicioFacturacionCodigos;
//import bo.gob.impuestos.sfe.codes.ServicioFacturacionCodigos_Service;
//import bo.vulcan.kraken.invoice.BuildConfig;
//import bo.vulcan.kraken.invoice.data.model.enumeration.Environment;
//import bo.vulcan.kraken.invoice.data.model.errors.BaseSiatException;
//import bo.vulcan.kraken.invoice.data.model.errors.CuisRequestException;
//import bo.vulcan.kraken.invoice.domain.services.siatapi.vm.NitRequestVM;
//
//@Singleton
//public class CodeSiatService {
//
//    private ServicioFacturacionCodigos proxy;
//
//    public boolean nitRequest(String token, NitRequestVM request) throws CuisRequestException {
//        try {
//            createProxy(token, request.getEnvironmentCode());
//            RespuestaVerificarNit respuestaVerificarNit = proxy.verificarNit(request.toSolicitudVerificarNit());
//            return respuestaVerificarNit.isTransaccion();
//        } catch (MalformedURLException e) {
//            throw new CuisRequestException(Collections.singletonList(new BaseSiatException.CodeMessage(0, null, e.getMessage())));
//        }
//    }
//
//    private void createProxy(String token, Integer environment) throws MalformedURLException {
//        String endpoint = "https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl";
//        if (environment.equals(Integer.valueOf(Environment.TEST.code)))
//            endpoint = BuildConfig.SIAT_API_TEST_code;
//        if (environment.equals(Integer.valueOf(Environment.PRODUCTION.code)))
//            endpoint = BuildConfig.SIAT_API_PROD_code;
//        ServicioFacturacionCodigos_Service port = new ServicioFacturacionCodigos_Service(new URL(endpoint));
//        port.setHandlerResolver(new TokenHandlerResolver(token));
//        proxy = port.getServicioFacturacionCodigosPort();
//        ((BindingProvider)proxy).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, applicationProperties.getSiatTimeout().getRequest());
//        ((BindingProvider)proxy).getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, applicationProperties.getSiatTimeout().getConnect());
//    }
//}
