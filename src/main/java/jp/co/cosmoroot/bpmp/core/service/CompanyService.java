package jp.co.cosmoroot.bpmp.core.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cosmoroot.bpmp.core.dao.CompanyDao;
import jp.co.cosmoroot.bpmp.core.dao.DBConnect;
import jp.co.cosmoroot.bpmp.core.model.Company;

public class CompanyService {

    public List<Company> viewCompanyList() throws SQLException {

        // DB接続
        DBConnect dbConnect = new DBConnect();
        Connection con = dbConnect.getConnection();

        // レスポンスデータ
        List<Company> res = new ArrayList<Company>();

        try {
            // 会社一覧取得
            CompanyDao companyDao = new CompanyDao(con);
            res = companyDao.selectAll();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return res;
    }

    public String createCompanyId() throws SQLException {
        // DB接続
        DBConnect dbConnect = new DBConnect();
        Connection con = dbConnect.getConnection();

        String companyId = "";
        try {
            // 会社追加
            CompanyDao companyDao = new CompanyDao(con);
            String maxCompanyId = companyDao.selectCompanyMaxID();
            companyId = String.format("%010d", Integer.parseInt(maxCompanyId) + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return companyId;
    }

    public void addCompany(Company company) throws SQLException {

        // DB接続
        DBConnect dbConnect = new DBConnect();
        Connection con = dbConnect.getConnection();

        try {
            // 会社追加
            CompanyDao companyDao = new CompanyDao(con);
            company.setUpdateAPL("Admin");
            company.setRegistrationAPL("Admin");
            companyDao.create(company);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
