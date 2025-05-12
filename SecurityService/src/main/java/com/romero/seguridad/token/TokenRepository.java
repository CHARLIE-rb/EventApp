package com.romero.seguridad.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query("""
    SELECT t FROM Token t
     WHERE t.user.id = :userId
       AND t.expired = FALSE
       AND t.revoked = FALSE
  """)
  List<Token> findAllValidTokenByUser(@Param("userId") Integer id);

  Optional<Token> findByToken(String token);
}
