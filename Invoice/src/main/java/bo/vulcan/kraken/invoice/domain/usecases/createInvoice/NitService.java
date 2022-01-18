//package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;
//
//import bo.vulcan.kraken.invoice.data.model.db.Cuis;
//import bo.vulcan.kraken.invoice.data.model.errors.CuisRequestException;
//import bo.vulcan.kraken.invoice.domain.services.siatapi.vm.NitRequestVM;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class NitService {
//
//    private final CodeSiatService codeSiatService;
//    private final CompanyJWTService companyJWTService;
//
//
//    public boolean nitCheck(Long nitCheck, Cuis cuis) throws CuisRequestException {
//        NitRequestVM nitRequestVM = new NitRequestVM(
//            cuis.getCompany().getSystemCode(),
//            Long.valueOf(cuis.getCompany().getNit()),
//            cuis.getCompany().getEnvironment().code,
//            cuis.getBranch().getNumber(),
//            cuis.getCompany().getMode().code,
//            cuis.getCode(),
//            nitCheck
//        );
//        return codeSiatService.nitRequest(companyJWTService.authorize(cuis.getCompany()), nitRequestVM);
//    }
//
//}
