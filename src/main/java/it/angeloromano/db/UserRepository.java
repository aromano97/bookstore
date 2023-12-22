package it.angeloromano.db;

import org.springframework.data.jpa.repository.JpaRepository;

import it.angeloromano.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
