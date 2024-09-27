INSERT INTO users (id, username, password, email, enabled, account_non_expired, account_non_locked, credentials_non_expired, upd_date, upd_apl, reg_date, reg_apl, version)
VALUES (1, 'user1', '$2a$10$gmArnjyRKTr/jL66i1bNKO3np.aCZLLlsm1cTgu08stN/EFRkfBM.', 'user1@example.com', true, true, true, true, CURRENT_DATE, 'system', CURRENT_DATE, 'system', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO authorities (id, user_id, authority)
VALUES (1, 1, 'ROLE_USER')
ON CONFLICT (id) DO NOTHING;