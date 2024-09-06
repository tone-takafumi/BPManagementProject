package jp.co.cosmoroot.bpmp.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    // 接続用の情報をフィールドとして保存
    private static final String RDB_DRIVE = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // データベース接続を行うメソッド
    public Connection getConnection() {
        try {
            Class.forName(RDB_DRIVE);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
