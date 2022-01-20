package bo.vulcan.kraken.invoice.data.model.enumeration;

import static bo.vulcan.kraken.invoice.data.model.util.DocumentSectorServiceConstant.*;

public enum DocumentSectorType {
    BUY_AND_SELL_INVOICE(1, "facturaElectronicaCompraVenta.xsd", BUY_SELL),
    PROPERTY_RENTAL_INVOICE(2, "facturaElectronicaAlquilerBienInmueble.xsd", ELECTRONIC),
    COMMERCIAL_EXPORT_INVOICE(3, "facturaElectronicaComercialExportacion.xsd", ELECTRONIC),
    COMMERCIAL_EXPORT_INVOICE_ON_FREE_CONSIGNMENT(4, "facturaElectronicaLibreConsignacion.xsd", ELECTRONIC),
    FOREIGN_CURRENCY_PURCHASE_AND_SALE_INVOICE(9, "facturaElectronicaMonedaExtranjera.xsd", ELECTRONIC),
    EDUCATIONAL_SECTORS_INVOICE(11, "facturaElectronicaSectorEducativo.xsd", ELECTRONIC),
    INVOICE_FOR_PRODUCTS_REACHED_BY_ICE(14, "facturaElectronicaAlcanzadaIce.xsd", ELECTRONIC),
    FINANCIAL_ENTITY_INVOICE(15, "facturaElectronicaEntidadFinanciera.xsd", FINANCIAL_ENTITY),
    TELECOMMUNICATIONS_INVOICE(22, "facturaElectronicaTelecomunicacion.xsd", TELECOMMUNICATIONS),
    CREDIT_DEBIT_NOTE(24, "notaFiscalElectronicaCreditoDebito.xsd", "CREDIT_DEBIT");

    public final Integer code;
    public final String xmlCode;
    public final String service;

    DocumentSectorType(Integer code, String xmlCode, String service) {
        this.code = code;
        this.xmlCode = xmlCode;
        this.service = service;
    }

    public static DocumentSectorType getEnumFromInteger(Integer code) {
        if (code == 1) {
            return DocumentSectorType.BUY_AND_SELL_INVOICE;
        } else if (code == 2) {
            return DocumentSectorType.PROPERTY_RENTAL_INVOICE;
        } else if (code == 3) {
            return DocumentSectorType.COMMERCIAL_EXPORT_INVOICE;
        } else if (code == 4) {
            return DocumentSectorType.COMMERCIAL_EXPORT_INVOICE_ON_FREE_CONSIGNMENT;
        } else if (code == 9) {
            return DocumentSectorType.FOREIGN_CURRENCY_PURCHASE_AND_SALE_INVOICE;
        } else if (code == 11) {
            return DocumentSectorType.EDUCATIONAL_SECTORS_INVOICE;
        } else if (code == 14) {
            return DocumentSectorType.INVOICE_FOR_PRODUCTS_REACHED_BY_ICE;
        } else if (code == 15) {
            return DocumentSectorType.FINANCIAL_ENTITY_INVOICE;
        } else if (code == 22) {
            return DocumentSectorType.TELECOMMUNICATIONS_INVOICE;
        } else if (code == 24) {
            return DocumentSectorType.CREDIT_DEBIT_NOTE;
        }
        return null;
    }

}
