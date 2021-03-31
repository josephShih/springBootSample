package com.springBoot.sample.demo.repository.sampleDB.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springBoot.sample.demo.repository.sampleDB.model.User;

@Repository
public interface UpdateUserRepository extends JpaRepository<User, String> {
	
	// update by SQL
	@Modifying
	@Query(value="UPDATE user SET password = ?1  WHERE pid = ?2", nativeQuery = true)
	int updatePasswordBySQL(String pid, String password);
	
}
