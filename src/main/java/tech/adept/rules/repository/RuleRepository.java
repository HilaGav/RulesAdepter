package tech.adept.rules.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.adept.rules.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
