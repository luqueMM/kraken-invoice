package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;

import android.util.Log;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.datatype.DatatypeConfigurationException;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.Cufd;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.Reception;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.dto.RemoveNegativeDetailsResult;
import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.enumeration.EmissionType;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceSize;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceState;
import bo.vulcan.kraken.invoice.data.model.enumeration.ReceptionGroup;
import bo.vulcan.kraken.invoice.data.model.enumeration.ReceptionState;
import bo.vulcan.kraken.invoice.data.model.errors.BranchActivityLegendNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.CompanyDefaultNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.DigitalSignNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDetailsNegativeException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDetailsSubtotalException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDocumentSectorNotAvailableException;
import bo.vulcan.kraken.invoice.data.model.errors.MeasurementUnitNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.PaymentMethodParameterMissingException;
import bo.vulcan.kraken.invoice.data.model.errors.ProductNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatActivityNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatPaymentMethodTypeNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatProductNotFoundException;
import bo.vulcan.kraken.invoice.data.model.util.MyRoundNumberUtils;
import bo.vulcan.kraken.invoice.data.model.util.NumberToLetterService;
import bo.vulcan.kraken.invoice.data.model.util.RandomUtil;
import bo.vulcan.kraken.invoice.domain.services.siatapi.service.util.SiatUtilService;
import bo.vulcan.kraken.invoice.domain.services.siatapi.vm.GenerateCufRequestVM;

import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.CURRENCY_CODE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.DOCUMENT_TYPE_CODE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.EXCHANGE_RATE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.PAYMENT_METHOD_TYPE_CODE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.PRODUCT_CODE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.SIAT_MEASUREMENT_UNIT_CODE;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.SIAT_PRODUCT_CODE;

@Singleton
public class InvoiceService {

    private final DataManager mDataManager;

    private final InvoiceUtilService invoiceUtilService;

    @Inject
    public InvoiceService(DataManager mDataManager, InvoiceUtilService invoiceUtilService) {
        this.mDataManager = mDataManager;
        this.invoiceUtilService = invoiceUtilService;
    }

    public Invoice createInvoice(Invoice invoice,
                                 Company company,
                                 Branch branch,
                                 SalePoint salePoint,
                                 DateTime invoiceDate,
                                 Long economicActivityId,
                                 CompanyDefaultsDTO companyAllDefaults,
                                 DocumentSectorType documentSectorType,
                                 boolean nitCheck) throws DigitalSignNotFoundException, CompanyDefaultNotFoundException, BranchActivityLegendNotFoundException, PaymentMethodParameterMissingException, SiatPaymentMethodTypeNotFoundException, ProductNotFoundException, SiatProductNotFoundException, MeasurementUnitNotFoundException, SiatActivityNotFoundException, InvoiceDetailsNegativeException, InvoiceDetailsSubtotalException, InvoiceDocumentSectorNotAvailableException, DatatypeConfigurationException {

        DigitalSign digitalSign = mDataManager.getDigitalSign();
        if (digitalSign == null || !digitalSign.getEnabled()) {
            throw new DigitalSignNotFoundException();
        }

        BranchActivityLegend branchActivityLegend = mDataManager.branchActivityLegendGet(branch.getId(), economicActivityId);
        if (branchActivityLegend == null) {
            throw new BranchActivityLegendNotFoundException();
        }

        invoice.setEconomicActivityDescription(branchActivityLegend.getEconomicActivity());
        invoice.setLegend(branchActivityLegend.getLegend());

        if (invoice.getDocumentTypeCode() == null) {
            if (companyAllDefaults.getDocumentTypeCode() != null) {
                invoice.setDocumentTypeCode(companyAllDefaults.getDocumentTypeCode());
            } else {
                throw new CompanyDefaultNotFoundException(DOCUMENT_TYPE_CODE);
            }
        }
        if (invoice.getPaymentMethodTypeCode() == null) {
            if (companyAllDefaults.getPaymentMethodTypeCode() != null) {
                invoice.setPaymentMethodTypeCode(companyAllDefaults.getPaymentMethodTypeCode());
            } else {
                throw new CompanyDefaultNotFoundException(PAYMENT_METHOD_TYPE_CODE);
            }
        }
        invoiceUtilService.validatePaymentMethodParameters(invoice);
        if (invoice.getExchangeRate() == null) {
            if (companyAllDefaults.getExchangeRate() != null) {
                invoice.setExchangeRate(companyAllDefaults.getExchangeRate());
            } else {
                throw new CompanyDefaultNotFoundException(EXCHANGE_RATE);
            }
        }
        if (invoice.getCurrencyCode() == null) {
            if (companyAllDefaults.getCurrencyCode() != null) {
                invoice.setCurrencyCode(companyAllDefaults.getCurrencyCode());
            } else {
                throw new CompanyDefaultNotFoundException(CURRENCY_CODE);
            }
        }
        /*Company Additional data*/

        if (invoice.getDocumentComplement() != null && invoice.getDocumentComplement().trim().isEmpty())
            invoice.setDocumentComplement(null);
        invoice.setCompanyNit(company.getNit());
        invoice.setCompanyName(company.getName());
        invoice.setCompanyNameLogo(company.getImageName());

        /* Branch Additional data */
        invoice.setBranchName(branch.getName());
        invoice.setBranchNumber(branch.getNumber());
        invoice.setBranchAddress(branch.getAddress());
        invoice.setBranchPhone(branch.getPhone());
        invoice.setBranchCountry(branch.getCountry());
        invoice.setBranchCity(branch.getCity());

        /* SalePoint Additional data */
        if (salePoint != null) {
            invoice.setSalePointCode(salePoint.getCode());
        } else {
            invoice.setSalePointCode(0);
        }

        /* Customer Additional data */
        invoice.setInvoiceName(!invoice.getInvoiceName().equals("0") ? MyStringUtils.formatName(invoice.getInvoiceName()).toUpperCase() : "SIN NOMBRE");
        invoice.setDocumentNumber(!invoice.getDocumentNumber().equals("0") ? MyStringUtils.formatNit(invoice.getDocumentNumber()) : "0");

        if (invoice.getCustomerCode() == null)
            invoice.setCustomerCode("SN");

        /* Sale Additional data */

        if (invoice.getInvoiceTypeCode() == null) {
            invoice.setInvoiceTypeCode("1");
        }

        if (documentSectorType == null) {
            invoice.setDocumentSectorCode(company.getDocumentSectorType().code.toString());
        } else
            invoice.setDocumentSectorCode(documentSectorType.code.toString());

        /* Invoice Additional data */
        invoice.setInvoiceDate(invoiceDate.toLocalDate());
        if (invoice.getEmissionDate() == null) {
            invoice.setEmissionDate(invoiceDate);
        }

        invoice.setInvoiceState(InvoiceState.PENDING);

        Cufd cufd = mDataManager.getCufd();

        if (invoice.getLegend() == null) {
            invoice.setLegend("Ley N° 453: Tienes derecho a un trato equitativo sin discriminación en la oferta de servicios");
        }

        if (invoice.getUserCode() == null) {
            invoice.setUserCode("usuario-facturacion");
        }

        /* Total literal */

        /* Invoice message */
        if (invoice.getExtraMesssage() == null || invoice.getExtraMesssage().isEmpty())
            invoice.setExtraMesssage(company.getInvoiceMessage());

        /* Relationships entities */
        invoice.setCompanyId(company.getId());
        invoice.setBranchId(branch.getId());
        invoice.setSalePointId(salePoint.getId());
        invoice.setEnvironment(company.getEnvironment());
        invoice.setSystemCode(company.getSystemCode());
        invoice.setMode(company.getMode());

        /* Save invoice and invoice details */
        ArrayList<InvoiceDetail> temporalList = new ArrayList<>();
        int i = 1;
        for (InvoiceDetail item : invoice.getDetails()) {
            item.setInvoiceDate(invoice.getInvoiceDate());
            if (item.getSequence() == null) item.setSequence(i++);

            if (item.getProductCode() != null) {
                String productCode = item.getProductCode();
                Product product = mDataManager.product().findFirstByCompanyIdAndProductCodeAndProductOrigin(productCode, invoice.getInvoiceType());
                if(product == null){
                    throw new ProductNotFoundException();
                }
                item.setProductCode(product.getProductCode());
                item.setSiatProductCode(product.getSiatProductCode());
                item.setSiatMeasurementUnitCode(Integer.parseInt(product.getSiatMeasurementUnitCode().toString()));
                item.setSiatActivityCode(product.getActivityCode());
            } else {
                if (companyAllDefaults.getProductCode() == null) {
                    throw new CompanyDefaultNotFoundException(PRODUCT_CODE);
                }
                if (companyAllDefaults.getSiatProductCode() == null) {
                    throw new CompanyDefaultNotFoundException(SIAT_PRODUCT_CODE);
                }
                if (companyAllDefaults.getSiatMeasurementUnitCode() == null) {
                    throw new CompanyDefaultNotFoundException(SIAT_MEASUREMENT_UNIT_CODE);
                }
                String productCode = companyAllDefaults.getProductCode();
                Long siatProductCode = companyAllDefaults.getSiatProductCode();
                Integer siatMeasurementUnitCode = companyAllDefaults.getSiatMeasurementUnitCode();
                SiatProduct siatProduct = mDataManager.siatProductRepository().findFirstByProductCodeAndCompanyId(siatProductCode, company.getId());
                if (siatProduct == null){
                    throw new SiatProductNotFoundException();
                }

                String siatActivityCode = siatProduct.getActivityCode();
                if (productCode == null) {
                    throw new ProductNotFoundException();
                }
                if (siatProductCode == null) {
                    throw new SiatProductNotFoundException();
                }
                if (siatMeasurementUnitCode == null) {
                    throw new MeasurementUnitNotFoundException();
                }
                if (siatActivityCode == null) {
                    throw new SiatActivityNotFoundException();
                }
                item.setProductCode(productCode);
                item.setSiatProductCode(siatProductCode);
                item.setSiatMeasurementUnitCode(siatMeasurementUnitCode);
                item.setSiatActivityCode(siatActivityCode);
            }
            temporalList.add(item);
        }

        invoice.setLocalId(RandomUtil.generateUuid());
        invoice.setSync(false);
        final Invoice _invoice = invoice;

        RemoveNegativeDetailsResult result = invoiceUtilService.getNegativeAmounts(temporalList);
        if (result.isHasError()) {
            throw new InvoiceDetailsNegativeException();
        }
        if (result.getTotalNegative().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal previousAdditionalDiscount = invoice.getAdditionalDiscount() == null ? BigDecimal.ZERO : invoice.getAdditionalDiscount();
            invoice.setAdditionalDiscount(previousAdditionalDiscount.add(result.getTotalNegative()));
        }

        calculateAllStep(invoice);

        invoice.setDetails(result.setInvoiceIdToItems(_invoice));

        //TODO OFFLINE - EVENTO SIGNIFICATIVO
        //check exists initialized significantEvent
//        if (significantEventService.checkInitializedSignificantEvent(company.getId(), invoice.getBranchId(), invoice.getSalePointId())) {
//            if (invoice.getEmissionTypeCode().equals(EmissionType.MASSIVE)) {
//                TODO: ask if should throw an exception;
//            }
            invoice.setEmissionTypeCode(EmissionType.OFFLINE);
//        }

        if (invoice.getInvoiceNumber() == null) {
            Invoice lastInvoice = mDataManager.invoiceRepository().findFirstInvoiceOrderByCreationDateDesc();
            if (lastInvoice != null){
                invoice.setInvoiceNumber(lastInvoice.getInvoiceNumber()+1);
            } else {
                invoice.setInvoiceNumber(1L);
            }
        }

        /*Generate CUF*/
        String cuf = SiatUtilService.generateCuf(new GenerateCufRequestVM(
                Long.valueOf(invoice.getCompanyNit()),
                invoice.getEmissionDate(),
                invoice.getBranchNumber(),
                company.getMode().code,
                invoice.getEmissionTypeCode().code,
                Integer.parseInt(invoice.getInvoiceTypeCode()),
                Integer.parseInt(invoice.getDocumentSectorCode()),
                invoice.getInvoiceNumber(),
                invoice.getSalePointCode()
        ), cufd.getControlCode());

        invoice.setControlCode(cuf);

        invoice.setCufdCode(cufd.getCode());

        invoice.setCuisCode(cufd.getCuis().getCode());

        //TODO conectar a impuestos
//        if (nitCheck) {
//            try {
//                boolean nit = nitService.nitCheck(Long.valueOf(invoice.getDocumentNumber()), cufd.getCuis());
//                if (nit)
//                    invoice.setDocumentTypeCode("5");
//                else {
//                    if (!invoice.getDocumentTypeCode().equalsIgnoreCase("4"))
//                        invoice.setDocumentTypeCode("1");
//                }
//            } catch (Exception e) {
//                log.error("Error al verificar nit");
//            }
//        }

        /* Set qrcode string */
        invoice.setQrcodeString(
                invoiceUtilService.generateQrString(company.getEnvironment(), Long.parseLong(invoice.getCompanyNit()), invoice.getControlCode(), invoice.getInvoiceNumber(), InvoiceSize.HALF_SHEET)
        );

        if (invoice.getExceptionCode() == null)
            invoice.setExceptionCode(0);

        if (invoice.getBillingPeriod() == null || invoice.getBillingPeriod() == 0) {
            int billingPeriod = invoice.getInvoiceDate().getYear();
            billingPeriod = billingPeriod * 100;
            billingPeriod = billingPeriod + invoice.getInvoiceDate().getMonthOfYear();
            invoice.setBillingPeriod(billingPeriod);
        }

        if (invoice.getEmissionTypeCode().equals(EmissionType.ONLINE)) {
            Reception reception = new Reception();
            reception.setCompanyId(company.getId());
            reception.setState(ReceptionState.PENDING);
            reception.setReceptionGroup(ReceptionGroup.INDIVIDUAL);
            reception.setInvoiceQuantity(1);

            //TODO save reception
            reception = ReceptionService.sendInvoiceReception(company, invoice, reception);
//            if (reception != null) {
//                invoice.setReception(reception);
//                receptionRepository.save(reception);
//            }
//            invoice = invoiceRepository.save(invoice);
//            if (reception != null && reception.getStateCode() != null)
//                alarmNotificationService.save(invoice.getId(), String.valueOf(reception.getStateCode()), reception.getDescriptionCode());
//            invoiceDetailsRepository.saveAll(invoice.getDetails());
        } else {
//            if (invoice.getEmissionTypeCode().equals(EmissionType.MASSIVE)) {
//                if (!massiveInvoiceService.checkMassiveInvoiceTime(invoice)) {
//                    throw new MassiveInvoiceTimeOutOfRangeException();
//                }
//            }

//            invoice.setServerOrigin(applicationProperties.getServer().getId());
            //TODO cuando se cree en contingencia asignar invalidNit como true

            invoice = mDataManager.invoiceSave(invoice);
            mDataManager.invoiceDetailSaveAll(invoice.getDetails());
        }
//        if (invoice.getVoucherState() == null || invoice.getVoucherState().equals(VoucherState.PC))
//            invoiceNotificationService.saveInvoiceNotification(invoice.getId(), EventInvoice.GENERATED);
//        else if (invoice.getVoucherState() != null && invoice.getVoucherState().equals(VoucherState.CA))
//            invoiceNotificationService.saveInvoiceNotification(invoice.getId(), EventInvoice.PAID);
//        else if (invoice.getVoucherState() != null && invoice.getVoucherState().equals(VoucherState.AN))
//            invoiceNotificationService.saveInvoiceNotification(invoice.getId(), EventInvoice.CANCELED);
//        log.info("Create invoice: '{}', with id: {}, invoiceDate: {}, totalAmount: {}, cuf: '{}'", invoice.getInvoiceType(), invoice.getId(), invoice.getInvoiceDate(), invoice.getTotalAmount(), invoice.getControlCode());
        return invoice;

    }

    private void calculateAllStep(Invoice invoice) throws InvoiceDetailsSubtotalException {
        /* Calculate subtotal */
        DocumentSectorType documentSectorType = DocumentSectorType.getEnumFromInteger(Integer.parseInt(invoice.getDocumentSectorCode()));
        if (documentSectorType != null) {
            if (documentSectorType.equals(DocumentSectorType.BUY_AND_SELL_INVOICE) ||
                    documentSectorType.equals(DocumentSectorType.TELECOMMUNICATIONS_INVOICE) ||
                    documentSectorType.equals(DocumentSectorType.EDUCATIONAL_SECTORS_INVOICE) ||
                    documentSectorType.equals(DocumentSectorType.FOREIGN_CURRENCY_PURCHASE_AND_SALE_INVOICE)
            ) {
                BigDecimal calculatedTotal = BigDecimal.ZERO;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                }
                invoice.setSubtotal(calculatedTotal);
                if (invoice.getAdditionalDiscount() != null) {
                    calculatedTotal = calculatedTotal.subtract(invoice.getAdditionalDiscount());
                }
                invoice.setTotalAmount(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));

                if (invoice.getGiftCardAmount() != null) {
                    calculatedTotal = calculatedTotal.subtract(invoice.getGiftCardAmount());
                }
                invoice.setTotalAmountWithIva(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));
                if (documentSectorType.equals(DocumentSectorType.FOREIGN_CURRENCY_PURCHASE_AND_SALE_INVOICE)) {
                    if (invoice.getCodeTypeOperation().equals(1)) {
                        invoice.setIncomeDifferenceChange(invoice.getExchangeRate().subtract(invoice.getOfficialExchangeRate()));
                    } else {
                        invoice.setIncomeDifferenceChange(invoice.getOfficialExchangeRate().subtract(invoice.getExchangeRate()));
                    }
                    invoice.setTotalAmountWithIva(BigDecimal.ZERO);
                }
            } else if (documentSectorType.equals(DocumentSectorType.PROPERTY_RENTAL_INVOICE)) {
                BigDecimal calculatedTotal = BigDecimal.ZERO;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                }
                invoice.setSubtotal(calculatedTotal);
                if (invoice.getAdditionalDiscount() != null) {
                    calculatedTotal = calculatedTotal.subtract(invoice.getAdditionalDiscount());
                }
                invoice.setTotalAmount(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));
                invoice.setTotalAmountWithIva(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));
            } else if (documentSectorType.equals(DocumentSectorType.INVOICE_FOR_PRODUCTS_REACHED_BY_ICE)) {

                BigDecimal calculatedTotal = BigDecimal.ZERO;
                BigDecimal calculatedAmountIceSpecific = null;
                BigDecimal calculatedAmountIcePercentage = null;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }

                    if (item.getMarkIce().equals(1)) {
                        item.setAliquotIva( subtotalDetail.multiply(new BigDecimal("0.13") ));
                        item.setPriceNetSaleIce(subtotalDetail.subtract(item.getAliquotIva()));
                        if (item.getAliquotSpecify() != null && item.getQuantityIce() != null) {
                            item.setAmountIceSpecific(item.getQuantityIce().multiply(item.getAliquotSpecify()));
                        }
                        if (item.getPriceNetSaleIce() != null && item.getAliquotPercentage() != null) {
                            item.setAmountIcePercentage(item.getPriceNetSaleIce().multiply(item.getAliquotPercentage()));
                        }
                        item.setSubtotal(subtotalDetail);
                        if (item.getAmountIcePercentage() != null) {
                            item.setSubtotal(item.getSubtotal().add(item.getAmountIcePercentage()));
                        }
                        if (item.getAmountIceSpecific() != null) {
                            item.setSubtotal(item.getSubtotal().add(item.getAmountIceSpecific()));
                        }
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                    if (item.getAmountIceSpecific() != null) {
                        BigDecimal previousValue = calculatedAmountIceSpecific == null ? BigDecimal.ZERO : calculatedAmountIceSpecific;
                        calculatedAmountIceSpecific = previousValue.add(item.getAmountIceSpecific());
                    }
                    if (item.getAmountIcePercentage() != null) {
                        BigDecimal previousValue = calculatedAmountIcePercentage == null ? BigDecimal.ZERO : calculatedAmountIcePercentage;
                        calculatedAmountIcePercentage = previousValue.add(item.getAmountIcePercentage());
                    }
                }

                invoice.setSubtotal(calculatedTotal);
                if (invoice.getAdditionalDiscount() != null) {
                    calculatedTotal = calculatedTotal.subtract(invoice.getAdditionalDiscount());
                }

                invoice.setAmountIceSpecific(calculatedAmountIceSpecific);
                invoice.setAmountIcePercentage(calculatedAmountIcePercentage);
                invoice.setTotalAmount(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));

                invoice.setTotalAmountWithIva(calculatedTotal);
                if (calculatedAmountIceSpecific != null) {
                    invoice.setTotalAmountWithIva( invoice.getTotalAmountWithIva().subtract(calculatedAmountIceSpecific) );
                }
                if (calculatedAmountIcePercentage != null) {
                    invoice.setTotalAmountWithIva( invoice.getTotalAmountWithIva().subtract(calculatedAmountIcePercentage) );
                }

            } else if (documentSectorType.equals(DocumentSectorType.COMMERCIAL_EXPORT_INVOICE)) {
                BigDecimal calculatedTotal = BigDecimal.ZERO;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                }
                invoice.setSubtotal(calculatedTotal);
                invoice.setAmountDetail(calculatedTotal);
                invoice.setTotalNationalFobExpenses(invoice.getTotalNationalFobExpenses().add(invoice.getAmountDetail()));

                invoice.setTotalAmountCurrency(invoice.getTotalInternationalExpenses().add(invoice.getTotalNationalFobExpenses()));
                invoice.setTotalAmount(MyRoundNumberUtils.roundTwoDecimal(invoice.getTotalAmountCurrency().multiply(invoice.getExchangeRate())));
                invoice.setTotalLiteral(NumberToLetterService.convert(invoice.getTotalAmount(), "Bolivianos", true));
                invoice.setTotalAmountWithIva(BigDecimal.ZERO);
                return;
            } else if (documentSectorType.equals(DocumentSectorType.COMMERCIAL_EXPORT_INVOICE_ON_FREE_CONSIGNMENT)) {
                BigDecimal calculatedTotal = BigDecimal.ZERO;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                }
                invoice.setSubtotal(calculatedTotal);
                invoice.setTotalAmountCurrency(calculatedTotal);
                invoice.setTotalAmount(invoice.getTotalAmountCurrency().multiply(invoice.getExchangeRate()));
                invoice.setTotalLiteral(NumberToLetterService.convert(invoice.getTotalAmount(), "Bolivianos", true));
                invoice.setTotalAmountWithIva(BigDecimal.ZERO);
                return;
            } else if (documentSectorType.equals(DocumentSectorType.FINANCIAL_ENTITY_INVOICE)) {
                BigDecimal calculatedTotal = BigDecimal.ZERO;
                for (InvoiceDetail item : invoice.getDetails()) {
                    BigDecimal subtotalDetail = item.getUnitPrice().multiply(item.getQuantity());
                    if (item.getDiscountAmount() != null) {
                        subtotalDetail = subtotalDetail.subtract(item.getDiscountAmount());
                    }
                    if (MyRoundNumberUtils.roundFiveDecimal(subtotalDetail).compareTo(MyRoundNumberUtils.roundFiveDecimal(item.getSubtotal())) != 0) {
                        throw new InvoiceDetailsSubtotalException();
                    }
                    calculatedTotal = calculatedTotal.add(item.getSubtotal());
                }
                invoice.setSubtotal(calculatedTotal);
                if (invoice.getTotalAmountFinancialLease() != null) {
                    calculatedTotal = calculatedTotal.add(invoice.getTotalAmountFinancialLease());
                }
                if (invoice.getAdditionalDiscount() != null) {
                    calculatedTotal = calculatedTotal.subtract(invoice.getAdditionalDiscount());
                }
                invoice.setTotalAmount(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));
                if (invoice.getTotalAmountFinancialLease() != null) {
                    invoice.setTotalAmountWithIva(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal.subtract(invoice.getTotalAmountFinancialLease())));
                } else {
                    invoice.setTotalAmountWithIva(MyRoundNumberUtils.roundTwoDecimal(calculatedTotal));
                }
            } else {
                BigDecimal subTotal = invoice.getTotalAmount();
                if (invoice.getAdditionalDiscount() != null) {
                    subTotal = subTotal.add(invoice.getAdditionalDiscount());
                }

                invoice.setSubtotal(subTotal);
            }
        }
        invoice.setTotalAmountCurrency(MyRoundNumberUtils.roundTwoDecimal(invoice.getTotalAmount().divide(invoice.getExchangeRate(), 2, RoundingMode.HALF_UP)));
        invoice.setTotalLiteral(NumberToLetterService.convert(invoice.getTotalAmount(), "Bolivianos", true));
    }
}
