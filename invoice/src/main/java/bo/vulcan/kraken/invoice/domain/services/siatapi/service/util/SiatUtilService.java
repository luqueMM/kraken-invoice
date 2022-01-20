package bo.vulcan.kraken.invoice.domain.services.siatapi.service.util;

import android.util.Log;

import java.io.File;
import java.math.BigInteger;

import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.domain.services.siatapi.vm.GenerateCufRequestVM;

public class SiatUtilService {

//    public void generateXmlFile(Object invoiceXml, String nitCompany, String fileName, DocumentSectorType documentSectorType) throws GenerateXmlArchiveException {
//        File fileXml = fileUtilService.createInvoiceFile(nitCompany, fileName);
//        if (fileName == null)
//            throw new GenerateXmlArchiveException("No su pudo crear el archivo");
//        try {
//            JAXBContext context = JAXBContext.newInstance(invoiceXml.getClass());
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, documentSectorType.xmlCode);
//            m.marshal(invoiceXml, fileXml);
//        } catch (JAXBException e) {
//            throw new GenerateXmlArchiveException(e.getMessage(), e.getCause());
//        }
//    }

    public static String generateCuf(GenerateCufRequestVM invoice, String controlCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%013d", invoice.getNit()));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        stringBuilder.append(String.format("%017d", Long.valueOf(invoice.getDateTime().toString("yyyyMMddHHmmssSSS"))));
        stringBuilder.append(String.format("%04d", invoice.getBranchCode()));
        stringBuilder.append(invoice.getModalityCode());
        stringBuilder.append(invoice.getEmissionType());
        stringBuilder.append(invoice.getInvoiceType());
        stringBuilder.append(String.format("%02d", invoice.getSectorDocumentType()));
        stringBuilder.append(String.format("%010d", invoice.getInvoiceNumber()));
        stringBuilder.append(String.format("%04d", invoice.getPointSaleCode() != null ? invoice.getPointSaleCode() : 0));
        Log.d("generateCuf", "Cuf not module : {} " + stringBuilder.toString());
        String modulo11 = getModule11(stringBuilder.toString());
        stringBuilder.append(modulo11);
        Log.d("generateCuf", stringBuilder.toString());
        String cuf = new BigInteger(stringBuilder.toString()).toString(16).toUpperCase();
        Log.d("generateCuf", cuf);
        cuf = cuf.concat(controlCode);
        Log.d("generateCuf", cuf);
        return cuf.toUpperCase();
    }

    private static String getModule11(String pCadena) {
        return calculateDigitMod11(pCadena, 1, 9, false);
    }

    private static String calculateDigitMod11(String cadena, int numDig, int limMult, boolean x10) {
        int mult, suma, i, n, dig;
        if (!x10) numDig = 1;
        for (n = 1; n <= numDig; n++) {
            suma = 0;
            mult = 2;
            for (i = cadena.length() - 1; i >= 0; i--) {
                suma += (mult * Integer.parseInt(cadena.substring(i, i + 1)));
                if (++mult > limMult) mult = 2;
            }
            if (x10) {
                dig = ((suma * 10) % 11) % 10;
            } else {
                dig = suma % 11;
            }
            if (dig == 10) {
                cadena += "1";
            }
            if (dig == 11) {
                cadena += "0";
            }
            if (dig < 10) {
                cadena += String.valueOf(dig);
            }
        }
        return cadena.substring(cadena.length() - numDig, cadena.length());
    }

}
