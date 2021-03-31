package com.springBoot.sample.demo.repository.sampleDB.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springBoot.sample.demo.repository.sampleDB.model.User;

@Repository
public interface QueryUserRepository extends JpaRepository<User, String>  {
	
	User findByPid(String pid);
	User findByEmail(String email);
	
	//query by SQL
	@Query(value="SELECT * FROM user WHERE pid = ?", nativeQuery = true)
	User findBySQL(String pid);
	
}
