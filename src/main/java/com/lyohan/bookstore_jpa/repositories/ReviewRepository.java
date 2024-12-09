package com.lyohan.bookstore_jpa.repositories;


import com.lyohan.bookstore_jpa.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
}
