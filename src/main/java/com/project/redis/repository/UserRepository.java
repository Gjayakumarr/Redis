package com.project.redis.repository;

import com.project.redis.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details SET is_deleted = true, deleted_on = NOW() WHERE id = :userId", nativeQuery = true)
    public int deleteUser(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details SET is_deleted = false, deleted_on = NULL WHERE id = :userId", nativeQuery = true)
    public int activateUser(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details u SET u.name = :name, u.email = :email, u.phone = :phone WHERE u.id = :id",  nativeQuery = true)
    int updateUserDetails(@Param("id") int id, @Param("name") String name,  @Param("email") String email,  @Param("phone") String phone);

    List<UserDetails> findByIsDeletedFalse();
    List<UserDetails> findByIsDeletedTrue();

}
