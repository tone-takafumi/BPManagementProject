package jp.co.cosmoroot.bpmp.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.cosmoroot.bpmp.core.entity.Department;

/**
 * @author cosmoroot
 *
 * 部署リポジトリ
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @EntityGraph(attributePaths = { "members", "parentDepartment", "members.role" })
    List<Department> findByDepartmentID(String departmentID);

    @EntityGraph(value = "Department.members")
    Optional<Department> findByDepartmentName(String departmentName);
}
