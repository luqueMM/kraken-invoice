package bo.vulcan.kraken.invoice.domain.services.siatapi.service.util;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDocumentSectorNotAvailableException;
import bo.vulcan.kraken.invoice.data.model.util.MyRoundNumberUtils;
import bo.vulcan.kraken.invoice.domain.services.siatapi.xml.FacturaElectronicaCompraVenta;
import bo.vulcan.kraken.invoice.utils.AppConstants;

public class InvoiceGenerationXMLService {

    public static Object createFacturaByInvoiceCompanyDocumentSectorType(Invoice invoice) throws DatatypeConfigurationException, InvoiceDocumentSectorNotAvailableException {
        DocumentSectorType documentSectorType = DocumentSectorType.getEnumFromInteger(Integer.parseInt(invoice.getDocumentSectorCode()));
        if (documentSectorType != null) {
            if (documentSectorType.equals(DocumentSectorType.BUY_AND_SELL_INVOICE)) {
                return createFacturaElectronicaCompraVenta(invoice);
            } //TODO ICE
            /*else if (documentSectorType.equals(DocumentSectorType.INVOICE_FOR_PRODUCTS_REACHED_BY_ICE)) {
                return createFacturaElectronicaAlcanzadaIce(invoice);
            } */else {
                throw new InvoiceDocumentSectorNotAvailableException();
            }
        } else
            throw new InvoiceDocumentSectorNotAvailableException();
    }

    //TODO FacturaElectronicaCompraVenta objeto xml - @XmlElement error
    private static FacturaElectronicaCompraVenta createFacturaElectronicaCompraVenta(Invoice invoice) throws DatatypeConfigurationException {
        FacturaElectronicaCompraVenta facturaElectronicaCompraVenta = new FacturaElectronicaCompraVenta();
        FacturaElectronicaCompraVenta.Cabecera header = new FacturaElectronicaCompraVenta.Cabecera();
        header.setNitEmisor(Long.parseLong(invoice.getCompanyNit()));
        header.setRazonSocialEmisor(invoice.getCompanyName());
        header.setMunicipio(invoice.getBranchCity());
        header.setTelefono(invoice.getBranchPhone());
        header.setNumeroFactura(invoice.getInvoiceNumber());
        header.setCuf(invoice.getControlCode());
        header.setCufd(invoice.getCufdCode());
        header.setCodigoSucursal(invoice.getBranchNumber());
        header.setDireccion(invoice.getBranchAddress());
        header.setCodigoPuntoVenta(invoice.getSalePointCode());

        header.setFechaEmision(DatatypeFactory.newInstance().newXMLGregorianCalendar(invoice.getEmissionDate().toString(AppConstants.DATETIME_FORMAT_XML)));
        header.setNombreRazonSocial(invoice.getInvoiceName());
        header.setCodigoTipoDocumentoIdentidad(Integer.parseInt(invoice.getDocumentTypeCode()));
        header.setNumeroDocumento(invoice.getDocumentNumber());
        header.setComplemento(invoice.getDocumentComplement());
        header.setCodigoCliente(invoice.getCustomerCode());
        header.setCodigoMetodoPago(Integer.parseInt(invoice.getPaymentMethodTypeCode()));
        if (invoice.getCardNumber() != null) {
            header.setNumeroTarjeta(Long.valueOf(invoice.getCardNumber()));
        }
        header.setMontoTotal(MyRoundNumberUtils.roundTwoDecimal(invoice.getTotalAmount()));
        header.setMontoTotalSujetoIva(MyRoundNumberUtils.roundTwoDecimal(invoice.getTotalAmountWithIva()));
        header.setCodigoMoneda(Integer.parseInt(invoice.getCurrencyCode()));
        header.setTipoCambio(MyRoundNumberUtils.scaleTwo(invoice.getExchangeRate()));
        header.setMontoTotalMoneda(MyRoundNumberUtils.roundTwoDecimal(invoice.getTotalAmountCurrency()));
        header.setLeyenda(invoice.getLegend());
        header.setUsuario(invoice.getUserCode());
        header.setCodigoDocumentoSector(BigInteger.valueOf(Long.parseLong(invoice.getDocumentSectorCode())));

        header.setMontoGiftCard(MyRoundNumberUtils.roundTwoDecimal(invoice.getGiftCardAmount()));
        header.setDescuentoAdicional(MyRoundNumberUtils.roundTwoDecimal(invoice.getAdditionalDiscount()));
        header.setCodigoExcepcion(invoice.getExceptionCode());
        header.setCafc(invoice.getCafc());

        List<FacturaElectronicaCompraVenta.Detalle> details = new ArrayList<FacturaElectronicaCompraVenta.Detalle>();

        for(InvoiceDetail invoiceDetails : invoice.getDetails()){
            FacturaElectronicaCompraVenta.Detalle detail = new FacturaElectronicaCompraVenta.Detalle();
            detail.setActividadEconomica(invoiceDetails.getSiatActivityCode());
            detail.setCodigoProductoSin(invoiceDetails.getSiatProductCode());
            detail.setCodigoProducto(invoiceDetails.getProductCode());
            detail.setDescripcion(invoiceDetails.getConcept());
            detail.setCantidad(MyRoundNumberUtils.roundTwoDecimal(invoiceDetails.getQuantity()));
            detail.setUnidadMedida(invoiceDetails.getSiatMeasurementUnitCode());
            detail.setPrecioUnitario(MyRoundNumberUtils.roundTwoDecimal(invoiceDetails.getUnitPrice()));
            detail.setMontoDescuento(MyRoundNumberUtils.roundTwoDecimal(invoiceDetails.getDiscountAmount()));
            detail.setSubTotal(MyRoundNumberUtils.roundTwoDecimal(invoiceDetails.getSubtotal()));
            detail.setNumeroSerie(invoiceDetails.getSeriesNumber());
            detail.setNumeroImei(invoiceDetails.getImeiNumber());
            details.add(detail);
        }
        facturaElectronicaCompraVenta.setCabecera(header);
        facturaElectronicaCompraVenta.setDetalle(details);
        return facturaElectronicaCompraVenta;
    }
}
