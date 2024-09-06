package jp.co.cosmoroot.bpmp.core.dao;

import java.sql.Connection;
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

    public List<Company> selectAll() {

        ArrayList<Company> companyList = new ArrayList<>();

        String sql = "SELECT * FROM company";

        PreparedStatement stmt = null;
        ResultSet res = null;
        Company company = null;

        try {
            // SQLの設定
            stmt = con.prepareStatement(sql);
            // SQL分の実行
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e3) {
                    // TODO 自動生成された catch ブロック
                    e3.printStackTrace();
                }
            }
        }
        return companyList;
    }
}
