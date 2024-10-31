package com.example.HouseholdAccountBook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private String id; // PK
    private String name; // カテゴリー名
    private String description; // カテゴリーの説明
    @CreatedDate
    private LocalDateTime createdAt; // カテゴリー作成日時
    @LastModifiedDate
    private LocalDateTime updatedAt; // カテゴリー更新日時
}
