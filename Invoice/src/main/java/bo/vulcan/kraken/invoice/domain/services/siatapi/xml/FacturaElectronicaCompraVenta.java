package bo.vulcan.kraken.invoice.domain.services.siatapi.xml;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

//TODO xml Error
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlSchemaType;
//import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@Getter
@Setter
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "cabecera",
//        "detalle"
//})
//@XmlRootElement(name = "facturaElectronicaCompraVenta")
public class FacturaElectronicaCompraVenta {

//    @XmlElement(required = true)
    protected FacturaElectronicaCompraVenta.Cabecera cabecera;
//    @XmlElement(required = true)
    protected List<FacturaElectronicaCompraVenta.Detalle> detalle;

    @Getter
    @Setter
//    @XmlAccessorType(XmlAccessType.FIELD)
//    @XmlType(name = "", propOrder = {
//            "nitEmisor",
//            "razonSocialEmisor",
//            "municipio",
//            "telefono",
//            "numeroFactura",
//            "cuf",
//            "cufd",
//            "codigoSucursal",
//            "direccion",
//            "codigoPuntoVenta",
//            "fechaEmision",
//            "nombreRazonSocial",
//            "codigoTipoDocumentoIdentidad",
//            "numeroDocumento",
//            "complemento",
//            "codigoCliente",
//            "codigoMetodoPago",
//            "numeroTarjeta",
//            "montoTotal",
//            "montoTotalSujetoIva",
//            "codigoMoneda",
//            "tipoCambio",
//            "montoTotalMoneda",
//            "montoGiftCard",
//            "descuentoAdicional",
//            "codigoExcepcion",
//            "cafc",
//            "leyenda",
//            "usuario",
//            "codigoDocumentoSector"
//    })
    public static class Cabecera {

        protected long nitEmisor;
//        @XmlElement(required = true)
        protected String razonSocialEmisor;
//        @XmlElement(required = true)
        protected String municipio;
//        @XmlElement(required = true, nillable = true)
        protected String telefono;
        protected long numeroFactura;
//        @XmlElement(required = true)
        protected String cuf;
//        @XmlElement(required = true)
        protected String cufd;
        protected int codigoSucursal;
//        @XmlElement(required = true)
        protected String direccion;
//        @XmlElement(required = true, type = Integer.class, nillable = true)
        protected Integer codigoPuntoVenta;
//        @XmlElement(required = true)
//        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar fechaEmision;
//        @XmlElement(required = true, nillable = true)
        protected String nombreRazonSocial;
        protected int codigoTipoDocumentoIdentidad;
//        @XmlElement(required = true)
        protected String numeroDocumento;
//        @XmlElement(required = true, nillable = true)
        protected String complemento;
//        @XmlElement(required = true)
        protected String codigoCliente;
        protected int codigoMetodoPago;
//        @XmlElement(required = true, type = Long.class, nillable = true)
        protected Long numeroTarjeta;
//        @XmlElement(required = true)
        protected BigDecimal montoTotal;
//        @XmlElement(required = true)
        protected BigDecimal montoTotalSujetoIva;
        protected int codigoMoneda;
//        @XmlElement(required = true)
        protected BigDecimal tipoCambio;
//        @XmlElement(required = true)
        protected BigDecimal montoTotalMoneda;
//        @XmlElement(required = true, nillable = true)
        protected BigDecimal montoGiftCard;
//        @XmlElement(required = true, nillable = true)
        protected BigDecimal descuentoAdicional;
//        @XmlElement(required = true, type = Integer.class, nillable = true)
        protected Integer codigoExcepcion;
//        @XmlElement(required = true, nillable = true)
        protected String cafc;
//        @XmlElement(required = true)
        protected String leyenda;
//        @XmlElement(required = true)
        protected String usuario;
//        @XmlElement(required = true)
        protected BigInteger codigoDocumentoSector;

    }

    @Getter
    @Setter
//    @XmlAccessorType(XmlAccessType.FIELD)
//    @XmlType(name = "", propOrder = {
//            "actividadEconomica",
//            "codigoProductoSin",
//            "codigoProducto",
//            "descripcion",
//            "cantidad",
//            "unidadMedida",
//            "precioUnitario",
//            "montoDescuento",
//            "subTotal",
//            "numeroSerie",
//            "numeroImei"
//    })
    public static class Detalle {

//        @XmlElement(required = true)
        protected String actividadEconomica;
        protected long codigoProductoSin;
//        @XmlElement(required = true)
        protected String codigoProducto;
//        @XmlElement(required = true)
        protected String descripcion;
//        @XmlElement(required = true)
        protected BigDecimal cantidad;
        protected int unidadMedida;
//        @XmlElement(required = true)
        protected BigDecimal precioUnitario;
//        @XmlElement(required = true, nillable = true)
        protected BigDecimal montoDescuento;
//        @XmlElement(required = true)
        protected BigDecimal subTotal;
//        @XmlElement(required = true, nillable = true)
        protected String numeroSerie;
//        @XmlElement(required = true, nillable = true)
        protected String numeroImei;
    }

}