package jp.co.cosmoroot.bpmp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.cosmoroot.bpmp.core.entity.Member;

/**
 * @author cosmoroot
 *
 * 人員リポジトリ
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberID(String memberID);

}
