package bo.vulcan.kraken.invoice.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.local.db.DbHelper;
import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDetailDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.ProductDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatCurrencyTypeDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatMeasurementUnitDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatPaymentMethodTypeDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatProductDao;
import bo.vulcan.kraken.invoice.data.local.prefs.PreferencesHelper;
import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.Cufd;
import bo.vulcan.kraken.invoice.data.model.db.Cuis;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.SiatActivity;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.db.SystemStatus;
import bo.vulcan.kraken.invoice.data.model.db.UserFilter;
import bo.vulcan.kraken.invoice.data.model.request.AuthenticationRequest;
import bo.vulcan.kraken.invoice.data.model.response.AccountResponse;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.data.model.response.BranchActivityLegendResponse;
import bo.vulcan.kraken.invoice.data.model.response.SiatConfigurationResponse;
import bo.vulcan.kraken.invoice.data.remote.ApiHeader;
import bo.vulcan.kraken.invoice.data.remote.ApiHelper;
import bo.vulcan.kraken.invoice.utils.CommonUtils;
import bo.vulcan.kraken.invoice.utils.TestConstants;
import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Gson mGson;
    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(Context context, Gson gson, PreferencesHelper preferencesHelper, ApiHelper apiHelper, DbHelper dbHelper) {
        mGson = gson;
        mContext = context;

        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mDbHelper = dbHelper;
    }

    /*------------------------------------- DataManger -------------------------------------*/
    @Override
    public void updateApiHeader(String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public Single<AccountResponse> syncUserInfo() {
        return account().doOnSuccess(
                response -> {
                    setUser(response.getUserFilter());
                    setCompany(response.getCompany());
                    setBranch(response.getBranch());
                    setSalePoint(response.getSalePoint());
                    setCompanyDefaults(response.getCompanyDefaults());
                });
    }

    @Override
    public Single<SiatConfigurationResponse> syncSiatConfiguration() {
        return getSiatConfiguration().doOnSuccess(
                response -> {
                    setDigitalSign(response.getDigitalSign());
                    Cuis _cuis = response.getCufd().getCuis();
                    _cuis.setCompany(getCompany());
                    _cuis.setBranch(getBranch());
                    _cuis.setSalePoint(getSalePoint());
                    setCuis(_cuis);
                    setCufd(response.getCufd());
                }
        );
    }

    @Override
    public Single<SystemStatus> syncSystemStatus() {
        //TODO add service and init run JOB: debe habe un job que verifique la conexion cada cierto tiempo
        try {
            Type type = new TypeToken<SystemStatus>() {
            }.getType();
            SystemStatus value = mGson.fromJson(CommonUtils.loadJSONFromAsset(mContext, TestConstants.SIAT_JOB_SYSTEM_STATUS), type);

            setSystemStatus(value);

            return Single.just(value);
        } catch (IOException e) {
            return Single.error(e);
        }
    }

    @Override
    public Single<Boolean> syncSiatCurrencyTypeList(List<SiatCurrencyType> siatCurrencyTypeList) {
        return siatCurrencyTypeDeleteAll()
                .andThen(siatCurrencyTypeInsertList(siatCurrencyTypeList));
    }

    @Override
    public Single<Boolean> syncSiatDocumentTypeList(List<SiatDocumentType> siatDocumentTypeList) {
        return siatDocumentTypeDeleteAll()
                .andThen(siatDocumentTypeInsertList(siatDocumentTypeList));
    }

    @Override
    public Single<Boolean> syncSiatPaymentMethodTypeList(List<SiatPaymentMethodType> siatPaymentMethodTypeList) {
        return siatPaymentMethodTypeDeleteAll()
                .andThen(siatPaymentMethodTypeInsertList(siatPaymentMethodTypeList));
    }

    @Override
    public Single<Boolean> syncProductList(List<Product> productList) {
        return productDeleteAll()
                .andThen(productInsertList(productList));
    }

    @Override
    public Single<Boolean> syncSiatProductList(List<SiatProduct> siatProductList) {
        return siatProductDeleteAll()
                .andThen(siatProductInsertList(siatProductList));
    }

    @Override
    public Single<Boolean> syncBranchActivityLegendList(List<BranchActivityLegendResponse> branchActivityLegendResponses) {
        List<BranchActivityLegend> branchActivityLegends = new ArrayList<>();
        List<SiatActivity> siatActivities = new ArrayList<>();

        for (BranchActivityLegendResponse item : branchActivityLegendResponses) {
            siatActivities.add(item.getSiatActivity());
            BranchActivityLegend _branch = item.getBranchActivityLegend();
            _branch.setActivityId(item.getSiatActivity().getId());
            _branch.setEconomicActivity(item.getSiatActivity().getDescription());
            branchActivityLegends.add(_branch);

        }

        return siatActivityDeleteAll()
                .andThen(siatActivityInsertList(siatActivities)
                        .flatMap(response -> branchActivityLegendDeleteAll()
                                .andThen(branchActivityLegendInsertList(branchActivityLegends))));
    }

    @Override
    public Single<Boolean> syncSiatMeasurementUnitList(List<SiatMeasurementUnit> siatMeasurementUnitList) {
        return siatMeasurementUnitDeleteAll()
                .andThen(siatMeasurementUnitInsertList(siatMeasurementUnitList));
    }


    /*------------------------------------- PreferencesHelper -------------------------------------*/
    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdW5uYSIsImF1dGgiOiIxIiwiZXhwIjoxNjQzMzgyNDQ5fQ.eOZ_1t2axxIBVEDsiYfcbSVsnGsYN40H0J_adskbdTahPSJ9nfqmezZc8QFi-DjFxUVfjZI3KiM0sPw0YdsNWg";
            accessToken = "Bearer " + accessToken;
        }

        mPreferencesHelper.setAccessToken(accessToken);
        updateApiHeader(accessToken);
    }

    @Override
    public UserFilter getUser() {
        return mPreferencesHelper.getUser();
    }

    @Override
    public void setUser(UserFilter value) {
        mPreferencesHelper.setUser(value);
    }

    @Override
    public Company getCompany() {
        return mPreferencesHelper.getCompany();
    }

    @Override
    public void setCompany(Company value) {
        mPreferencesHelper.setCompany(value);
    }

    @Override
    public Branch getBranch() {
        return mPreferencesHelper.getBranch();
    }

    @Override
    public void setBranch(Branch value) {
        mPreferencesHelper.setBranch(value);
    }

    @Override
    public SalePoint getSalePoint() {
        return mPreferencesHelper.getSalePoint();
    }

    @Override
    public void setSalePoint(SalePoint value) {
        mPreferencesHelper.setSalePoint(value);
    }

    @Override
    public CompanyDefaultsDTO getCompanyDefaults() {
        return mPreferencesHelper.getCompanyDefaults();
    }

    @Override
    public void setCompanyDefaults(CompanyDefaultsDTO value) {
        mPreferencesHelper.setCompanyDefaults(value);
    }

    @Override
    public DigitalSign getDigitalSign() {
        return mPreferencesHelper.getDigitalSign();
    }

    @Override
    public void setDigitalSign(DigitalSign value) {
        mPreferencesHelper.setDigitalSign(value);
    }

    @Override
    public Cufd getCufd() {
        return mPreferencesHelper.getCufd();
    }

    @Override
    public void setCufd(Cufd value) {
        mPreferencesHelper.setCufd(value);
    }

    @Override
    public Cuis getCuis() {
        return mPreferencesHelper.getCuis();
    }

    @Override
    public void setCuis(Cuis value) {
        mPreferencesHelper.setCuis(value);
    }

    @Override
    public SystemStatus getSystemStatus() {
        return mPreferencesHelper.getSystemStatus();
    }

    @Override
    public void setSystemStatus(SystemStatus value) {
        mPreferencesHelper.setSystemStatus(value);
    }


    /*------------------------------------- ApiHelper -------------------------------------*/
    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<AuthenticationResponse> authentication(AuthenticationRequest.ServerLoginRequest request) {
        return mApiHelper.authentication(request).doOnSuccess(
                response -> {
                    setAccessToken(response.getIdToken());
//                    updateUserInfo();
                }
        ).map(response -> {
            response.setIdToken("good!");
            return response;
        });
    }

    @Override
    public Single<AccountResponse> account() {
        return mApiHelper.account();
    }

    @Override
    public Single<SiatConfigurationResponse> getSiatConfiguration() {
        return mApiHelper.getSiatConfiguration();
    }

    @Override
    public Single<List<SiatCurrencyType>> getAllCurrencyTypes(Long companyID) {
        return mApiHelper.getAllCurrencyTypes(companyID);
    }

    @Override
    public Single<List<SiatDocumentType>> getAllDocumentTypes(Long companyID) {
        return mApiHelper.getAllDocumentTypes(companyID);
    }

    @Override
    public Single<List<SiatPaymentMethodType>> getAllPaymentMethodTypes(Long companyID) {
        return mApiHelper.getAllPaymentMethodTypes(companyID);
    }

    @Override
    public Single<List<Product>> getAllProducts(Long companyID) {
        return mApiHelper.getAllProducts(companyID);
    }

    @Override
    public Single<List<SiatProduct>> getAllSiatProducts(Long companyID) {
        return mApiHelper.getAllSiatProducts(companyID);
    }

    @Override
    public Single<List<BranchActivityLegendResponse>> getAllBranchActivityLegends(Long branchID) {
        return mApiHelper.getAllBranchActivityLegends(branchID);
    }

    @Override
    public Single<List<SiatMeasurementUnit>> getAllSiatMeasurementUnit(Long companyID) {
        return mApiHelper.getAllSiatMeasurementUnit(companyID);
    }


    /*------------------------------------- DataBase -------------------------------------*/
    //------- SiatCurrencyType ------------
    @Override
    public Single<Boolean> siatCurrencyTypeInsertList(List<SiatCurrencyType> siatCurrencyTypeList) {
        return mDbHelper.siatCurrencyTypeInsertList(siatCurrencyTypeList);
    }

    @Override
    public Completable siatCurrencyTypeDeleteAll() {
        return mDbHelper.siatCurrencyTypeDeleteAll();
    }

    @Override
    public Single<List<SiatCurrencyType>> siatCurrencyTypeGetList() {
        return mDbHelper.siatCurrencyTypeGetList();
    }

    @Override
    public SiatCurrencyTypeDao siatCurrencyTypeRepository() {
        return mDbHelper.siatCurrencyTypeRepository();
    }

    //------- Document Type ------------
    @Override
    public Single<Boolean> siatDocumentTypeInsertList(List<SiatDocumentType> siatDocumentTypeList) {
        return mDbHelper.siatDocumentTypeInsertList(siatDocumentTypeList);
    }

    @Override
    public Completable siatDocumentTypeDeleteAll() {
        return mDbHelper.siatDocumentTypeDeleteAll();
    }

    @Override
    public Single<List<SiatDocumentType>> siatDocumentTypeGetList() {
        return mDbHelper.siatDocumentTypeGetList();
    }

    @Override
    public Single<SiatDocumentType> siatDocumentTypeGet(String classifierCode) {
        return mDbHelper.siatDocumentTypeGet(classifierCode);
    }

    //------- Payment Method  Type ------------

    @Override
    public SiatPaymentMethodTypeDao siatPaymentMethodType() {
        return mDbHelper.siatPaymentMethodType();
    }

    @Override
    public Single<Boolean> siatPaymentMethodTypeInsertList(List<SiatPaymentMethodType> siatPaymentMethodTypeList) {
        return mDbHelper.siatPaymentMethodTypeInsertList(siatPaymentMethodTypeList);
    }

    @Override
    public Completable siatPaymentMethodTypeDeleteAll() {
        return mDbHelper.siatPaymentMethodTypeDeleteAll();
    }

    @Override
    public Single<List<SiatPaymentMethodType>> siatPaymentMethodTypeGetList() {
        return mDbHelper.siatPaymentMethodTypeGetList();
    }

    //------- Products ------------

    @Override
    public Single<Boolean> productInsertList(List<Product> productList) {
        return mDbHelper.productInsertList(productList);
    }

    @Override
    public Completable productDeleteAll() {
        return mDbHelper.productDeleteAll();
    }

    @Override
    public Single<List<Product>> productGetList() {
        return mDbHelper.productGetList();
    }

    @Override
    public ProductDao product() {
        return mDbHelper.product();
    }

    //------- Siat Products ------------

    @Override
    public Single<Boolean> siatProductInsertList(List<SiatProduct> siatProductList) {
        return mDbHelper.siatProductInsertList(siatProductList);
    }

    @Override
    public Completable siatProductDeleteAll() {
        return mDbHelper.siatProductDeleteAll();
    }

    @Override
    public Single<List<SiatProduct>> siatProductGetList() {
        return mDbHelper.siatProductGetList();
    }

    @Override
    public SiatProductDao siatProductRepository() {
        return mDbHelper.siatProductRepository();
    }

    //------- Branch Activity Legends ------------

    @Override
    public Single<Boolean> branchActivityLegendInsertList(List<BranchActivityLegend> branchActivityLegendList) {
        return mDbHelper.branchActivityLegendInsertList(branchActivityLegendList);
    }

    @Override
    public Completable branchActivityLegendDeleteAll() {
        return mDbHelper.branchActivityLegendDeleteAll();
    }

    @Override
    public Single<List<BranchActivityLegend>> branchActivityLegendGetList() {
        return mDbHelper.branchActivityLegendGetList();
    }

    @Override
    public BranchActivityLegend branchActivityLegendGet(Long branchId, Long activityId) {
        return mDbHelper.branchActivityLegendGet(branchId, activityId);
    }

    //------- Siat Activity ------------

    @Override
    public Single<Boolean> siatActivityInsertList(List<SiatActivity> siatActivityList) {
        return mDbHelper.siatActivityInsertList(siatActivityList);
    }

    @Override
    public Completable siatActivityDeleteAll() {
        return mDbHelper.siatActivityDeleteAll();
    }

    //------- Invoice ------------

    @Override
    public Single<Boolean> invoiceInsertList(List<Invoice> invoiceList) {
        return mDbHelper.invoiceInsertList(invoiceList);
    }

    @Override
    public Invoice invoiceSave(Invoice invoice) {
        return mDbHelper.invoiceSave(invoice);
    }

    @Override
    public Completable invoiceDeleteAll() {
        return mDbHelper.invoiceDeleteAll();
    }

    @Override
    public Single<List<Invoice>> invoiceGetList() {
        return mDbHelper.invoiceGetList();
    }

    @Override
    public InvoiceDao invoiceRepository() {
        return mDbHelper.invoiceRepository();
    }

    //------- Invoice Details------------

    @Override
    public Single<Boolean> invoiceDetailInsertList(List<InvoiceDetail> invoiceDetailList) {
        return mDbHelper.invoiceDetailInsertList(invoiceDetailList);
    }

    @Override
    public InvoiceDetail invoiceDetailSave(InvoiceDetail invoiceDetail) {
        mDbHelper.invoiceDetailSave(invoiceDetail);
        return invoiceDetail;
    }

    @Override
    public void invoiceDetailSaveAll(Set<InvoiceDetail> invoiceDetailList) {
        mDbHelper.invoiceDetailSaveAll(invoiceDetailList);
    }

    @Override
    public Completable invoiceDetailDeleteAll() {
        return mDbHelper.invoiceDetailDeleteAll();
    }

    @Override
    public Single<List<InvoiceDetail>> invoiceDetailGetList() {
        return mDbHelper.invoiceDetailGetList();
    }

    @Override
    public InvoiceDetailDao invoiceDetailRepository() {
        return mDbHelper.invoiceDetailRepository();
    }

    //------- Siat Measurement Unit------------

    @Override
    public Single<Boolean> siatMeasurementUnitInsertList(List<SiatMeasurementUnit> siatMeasurementUnitList) {
        return mDbHelper.siatMeasurementUnitInsertList(siatMeasurementUnitList);
    }

    @Override
    public Completable siatMeasurementUnitDeleteAll() {
        return mDbHelper.siatMeasurementUnitDeleteAll();
    }

    @Override
    public Single<List<SiatMeasurementUnit>> siatMeasurementUnitGetList() {
        return mDbHelper.siatMeasurementUnitGetList();
    }

    @Override
    public SiatMeasurementUnitDao siatMeasurementUnitRepository() {
        return mDbHelper.siatMeasurementUnitRepository();
    }
}
