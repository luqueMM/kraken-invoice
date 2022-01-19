package bo.vulcan.kraken.invoice.domain.services.siatapi.vm;

//import bo.gob.impuestos.sfe.codes.SolicitudVerificarNit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString
public class NitRequestVM {
    private final String systemCode;
    private final Long nit;
    private final Integer environmentCode;
    private final Integer branchCode;
    private final Integer modalityCode;
    private final String cuis;
    private final Long nitCheck;

//    public SolicitudVerificarNit toSolicitudVerificarNit() {
//        SolicitudVerificarNit result = new SolicitudVerificarNit();
//        result.setCodigoAmbiente(getEnvironmentCode());
//        result.setCodigoModalidad(getModalityCode());
//        result.setCodigoSistema(getSystemCode());
//        result.setCodigoSucursal(getBranchCode());
//        result.setCuis(getCuis());
//        result.setNit(getNit());
//        result.setNitParaVerificacion(getNitCheck());
//        return result;
//    }
}
