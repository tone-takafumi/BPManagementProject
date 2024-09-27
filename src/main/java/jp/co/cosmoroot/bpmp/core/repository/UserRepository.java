package jp.co.cosmoroot.bpmp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.cosmoroot.bpmp.core.entity.User;

/**
 * @author cosmoroot
 *
 * ユーザリポジトリ
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "User.authorities")
    Optional<User> findByUsername(String username);
}
