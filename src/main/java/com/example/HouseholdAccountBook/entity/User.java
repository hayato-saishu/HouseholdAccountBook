package com.example.HouseholdAccountBook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id; // PK
    private String name; // ユーザー名
    private String password; // パスワード
    private String email; // ユーザーのメールアドレス（ユニーク）
    @CreatedDate
    private LocalDateTime createdAt; // アカウント作成日時
    @LastModifiedDate
    private LocalDateTime updatedAt; // アカウント情報更新日時
}
