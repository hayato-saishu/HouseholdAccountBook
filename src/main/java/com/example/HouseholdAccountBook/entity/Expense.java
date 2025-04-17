package com.example.HouseholdAccountBook.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    private String id; // PK
    private String userId; // ユーザーID
    private String categoryId; // カテゴリーID
    private String month; // 経費月
    private String expenseName;
    @NotNull(message = "金額を入力してください。")
    private int amount; // 金額
    private String note; // 備考
    @CreatedDate
    private LocalDateTime createdAt; // 記録作成日時
    @LastModifiedDate
    private LocalDateTime updatedAt; // 記録更新日時
}
