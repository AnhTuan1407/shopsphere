package com.devteria.profile.repository;

import com.devteria.profile.entity.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {
    @Query("MATCH (u:user_profile) WHERE u.id = $id RETURN u")
    UserProfile findByCustomId(@Param("id") String id);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
