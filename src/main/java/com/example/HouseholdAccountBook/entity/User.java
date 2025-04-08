package com.example.HouseholdAccountBook.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class User {

    @Id
    private String id; // PK
    @NotBlank(message = "ユーザー名を入力してください。")
    private String name; // ユーザー名
    @NotBlank(message = "パスワードを入力してください。")
    private String password; // パスワード
    @NotBlank(message = "メールアドレスを入力してください。")
    private String email; // ユーザーのメールアドレス（ユニーク）
    @CreatedDate
    private LocalDateTime createdAt; // アカウント作成日時
    @LastModifiedDate
    private LocalDateTime updatedAt; // アカウント情報更新日時
}
