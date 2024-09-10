package jp.co.cosmoroot.bpmp.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cosmoroot.bpmp.core.model.Company;

public class CompanyDao {

    private Connection con = null;

    public CompanyDao(Connection con) {
        this.con = con;
    }

    public List<Company> selectAll() throws SQLException {

        ArrayList<Company> companyList = new ArrayList<>();

        String sql = "SELECT * FROM company";

        ResultSet res = null;
        Company company = null;
        PreparedStatement stmt = null;

        try {
            // SQLの設定
            stmt = con.prepareStatement(sql);
            // SQL文の実行
            res = stmt.executeQuery();
            // 結果セットから情報を取り出す
            while (res.next()) {
                // Companyオブジェクトの生成
                company = new Company();
                company.setCompanyID(res.getString("COMPANY_ID"));
                company.setCompanyName(res.getString("COMPANY_NAME"));
                company.setCompanyInformation(res.getString("COMPANY_INFORMATION"));
                company.setAddress(res.getString("ADDRESS"));
                company.setUrl(res.getString("URL"));
                company.setUpdateDate(res.getDate("UPD_DATE"));
                company.setUpdateAPL(res.getString("UPD_APL"));
                company.setRegistrationDate(res.getDate("REG_DATE"));
                company.setRegistrationAPL("REG_APL");
                company.setVersion(res.getInt("VERSION"));
                companyList.add(company);
            }
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return companyList;
    }

    public String selectCompanyMaxID() throws SQLException {

        String maxId = "";

        String sql = "SELECT max(company_id) as max_id FROM company";

        ResultSet res = null;
        Company company = null;
        PreparedStatement stmt = null;

        try {
            // SQLの設定
            stmt = con.prepareStatement(sql);
            // SQL文の実行
            res = stmt.executeQuery();
            // 結果セットから情報を取り出す
            while (res.next()) {
                // 会社IDの上限値を取得
                maxId = res.getString("max_id");
            }
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return maxId;
    }

    public int create(Company company) throws SQLException {
        // 返り値
        int ret = 0;

        String sql = "INSERT INTO company(company_id, company_name, company_information, address, url, upd_date, upd_apl, reg_date, reg_apl, version) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;

        // 現在日時を取得
        Date nowDate = new Date(System.currentTimeMillis());

        try {
            // SQLの設定
            stmt = con.prepareStatement(sql);

            stmt.setString(1, company.getCompanyID());
            stmt.setString(2, company.getCompanyName());
            stmt.setString(3, company.getCompanyInformation());
            stmt.setString(4, company.getAddress());
            stmt.setString(5, company.getUrl());
            stmt.setDate(6, nowDate);
            stmt.setString(7, company.getUpdateAPL());
            stmt.setDate(8, nowDate);
            stmt.setString(9, company.getRegistrationAPL());
            stmt.setInt(10, company.getVersion());

            // SQL文を実行し、処理された行数をretに代入
            ret = stmt.executeUpdate();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return ret;
    }
}
