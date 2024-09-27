package jp.co.cosmoroot.bpmp.core.dto;

import lombok.Data;

/**
 * @author cosmoroot
 *
 * ログインDTO
 */
@Data
public class Login {

    // ユーザ名
    private String userName;

    // パスワード
    private String password;
}
