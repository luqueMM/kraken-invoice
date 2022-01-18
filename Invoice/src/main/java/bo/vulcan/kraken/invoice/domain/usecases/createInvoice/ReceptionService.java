package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;

import java.io.File;
import java.time.ZonedDateTime;

import javax.xml.datatype.DatatypeConfigurationException;

import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.Reception;
import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDocumentSectorNotAvailableException;
import bo.vulcan.kraken.invoice.domain.services.siatapi.service.util.InvoiceGenerationXMLService;
import bo.vulcan.kraken.invoice.domain.services.siatapi.service.util.SiatUtilService;

public class ReceptionService {
    public static Reception sendInvoiceReception(Company company, Invoice invoice, Reception reception) throws DatatypeConfigurationException,/* GenerateXmlArchiveException, ReceiptInvoiceException, VerifyCommunicationException,*/ InvoiceDocumentSectorNotAvailableException {

        /*Generate file invoice*/
        String fileName = invoice.getControlCode().concat(".xml");
        Object factura = InvoiceGenerationXMLService.createFacturaByInvoiceCompanyDocumentSectorType(invoice);
//        DocumentSectorType documentSectorType = DocumentSectorType.getEnumFromInteger(Integer.parseInt(invoice.getDocumentSectorCode()));;
//        SiatUtilService.generateXmlFile(factura, company.getNit(), fileName, documentSectorType);
//        DigitalSign digitalSign = companyDigitalSignService.getCompanyDigitalSign(company);
//        SiatUtilService.signFile(company.getNit(), fileName, digitalSign.getPrivateKeyName(), digitalSign.getPublicKeyName());
//        if (documentSectorType != null && applicationProperties.getSiat().getValidationXsd()) {
//            siatUtilService.validateXml(new File(
//                            applicationProperties.getSiat().getPathArchive().concat("/xsd/" + documentSectorType.xmlCode)),
//                    company.getNit(),
//                    SIGNATURE_PREFIX.concat(fileName)
//            );
//        }
//        byte[] archive = siatUtilService.generateArchive(company.getNit(), SIGNATURE_PREFIX.concat(fileName));
//        log.debug("Archivo : {}", archive.length);
//        String hashArchive = siatUtilService.hash256(archive);
//        log.debug("hashArchive : {}", hashArchive);
//
//        ReceptionRequestVM receptionRequestVM = new ReceptionRequestVM(
//                invoice.getEnvironment().code,
//                invoice.getSalePoint() != null ? invoice.getSalePoint().getCode() : 0,
//                invoice.getSystemCode(),
//                invoice.getBranchNumber(),
//                Long.parseLong(invoice.getCompanyNit()),
//                Integer.parseInt(invoice.getDocumentSectorCode()),
//                invoice.getEmissionTypeCode().code,
//                invoice.getMode().code,
//                invoice.getCufdCode(),
//                invoice.getCuisCode(),
//                Integer.parseInt(invoice.getInvoiceTypeCode()),
//                archive,
//                ZonedDateTime.now(),
//                hashArchive
//        );
//        ReceptionResponseVM receptionResponseVM = null;
//        log.debug("Recepcionando factura: {}", receptionRequestVM);
//        if (documentSectorType != null) {
//            switch (documentSectorType.service) {
//                case DocumentSectorServiceConstant.TELECOMMUNICATIONS:
//                    receptionResponseVM = telecommunicationsInvoiceSiatService.receiptInvoice(companyJWTService.authorize(company), receptionRequestVM);
//                    break;
//                case DocumentSectorServiceConstant.BUY_SELL:
//                    receptionResponseVM = buyAndSellInvoiceSiatService.receiptInvoice(companyJWTService.authorize(company), receptionRequestVM);
//                    break;
//                case DocumentSectorServiceConstant.FINANCIAL_ENTITY:
//                    receptionResponseVM = financialEntityInvoiceSiatService.receiptInvoice(companyJWTService.authorize(company), receptionRequestVM);
//                    break;
//                case DocumentSectorServiceConstant.ELECTRONIC:
//                    receptionResponseVM = electronicInvoiceSiatService.receiptInvoice(companyJWTService.authorize(company), receptionRequestVM);
//                    break;
//            }
//        }
//        log.debug("Response recpecion de factura: {}", receptionResponseVM);
//        reception.setState(ReceptionState.ACCEPTED);
//        siatUtilService.cleanInvoicesFiles(invoice.getCompanyNit(), invoice.getControlCode().concat(".xml"));
//        String messageReception = null;
//        String receptionCode = null;
//        if (receptionResponseVM != null) {
//            messageReception = receptionUtilService.getMessageReception(receptionResponseVM);
//            receptionCode = receptionUtilService.getReceptionCode(receptionResponseVM);
//        }
//        invoice.setMessageReception( (messageReception != null && messageReception.length() > 1024) ? messageReception.substring(0, 1024): messageReception);
//        invoice.setReceptionCode(receptionCode);
//        reception.setCode(receptionResponseVM != null ? receptionResponseVM.getReceptionCode() : "");
//        reception.setStateCode(receptionResponseVM != null ? receptionResponseVM.getStateCode() : null);
//        reception.setDescriptionCode(receptionResponseVM != null ? receptionResponseVM.getDescriptionCode() : null);
//        return reception;
        return null;
    }
}
