package com.example.demo.Member;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m FROM Member m WHERE m.username = :username")
    public Member findByUsername(@Param("username") String username);

    boolean existsMemberByUsername(String username);
}
