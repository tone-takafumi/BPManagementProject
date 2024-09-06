package jp.co.cosmoroot.bpmp.core.service;

import java.sql.Connection;
import java.util.List;

import jp.co.cosmoroot.bpmp.core.dao.CompanyDao;
import jp.co.cosmoroot.bpmp.core.dao.DBConnect;
import jp.co.cosmoroot.bpmp.core.model.Company;

public class CompanyService {

    public List<Company> viewCompanyList() {
        // DB接続
        DBConnect dbConnect = new DBConnect();
        Connection con = dbConnect.getConnection();

        // 会社一覧取得
        CompanyDao companyDao = new CompanyDao(con);
        List<Company> res = companyDao.selectAll();

        return res;
    }

}
