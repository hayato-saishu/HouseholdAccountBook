<template>
    <q-layout view="hHh Lpr lFf">
      <q-page-container>
        <q-page>
          <h2>今月の経費</h2>
  
          <q-form @submit.prevent="submitExpense" class="q-pa-md">
            <q-banner v-if="errorMessage" class="q-mt-md bg-red-3 text-negative">
              {{ errorMessage }}
            </q-banner>
  
            <q-input v-model="categoryId" label="カテゴリーID" outlined class="q-mt-sm" />
            <q-input v-model="month" label="月 (例: 2025-04)" outlined class="q-mt-sm" />
            <q-input v-model="expenseName" label="費用名" outlined class="q-mt-sm" />
            <q-input v-model="amount" label="金額" type="number" outlined class="q-mt-sm" />
  
            <div class="q-mt-md row q-gutter-sm">
              <q-btn label="登録" icon="add" color="primary" @click="submitExpense" />
              <q-btn label="更新" icon="edit" color="secondary" @click="updateExpense" :disable="!selectedExpenseId" />
              <q-btn label="削除" icon="delete" color="negative" @click="deleteExpense" :disable="!selectedExpenseId" />
            </div>
          </q-form>
  
          <q-separator spaced />
          <h3 class="q-mt-md">登録済み経費一覧</h3>
          <q-list bordered>
            <q-item
              v-for="expense in expenses"
              :key="expense.id"
              clickable
              @click="selectExpense(expense)"
            >
              <q-item-section>
                <q-item-label><strong>{{ expense.expenseName }}</strong></q-item-label>
                <q-item-label caption>金額: ¥{{ expense.amount }} / 月: {{ expense.month }}</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-page>
      </q-page-container>
    </q-layout>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue'
import ApiService from '../../service/ApiService'
  
  // ログイン済みユーザーのID（仮置き）
  const userId = 'demo-user-id'
  
  const expenses = ref<any[]>([])
  const selectedExpenseId = ref('')
  const categoryId = ref('')
  const month = ref('')
  const expenseName = ref('')
  const amount = ref('')
  const errorMessage = ref('')
  
  // 今月のデータをロード
  const loadExpenses = async () => {
    try {
      const now = new Date()
      const currentMonth = `${now.getFullYear()}-${String(now.getMonth() + 1).padStart(2, '0')}`
      month.value = currentMonth
      const response = await ApiService.getExpenses(userId, currentMonth)
      if (response.status !== 200) {
        errorMessage.value = '経費の取得に失敗しました。'
        return
      }
      expenses.value = response.data
    } catch (e) {
      errorMessage.value = '経費の取得に失敗しました。'
      console.error(e)
    }
  }
  
  // 経費を登録
  const submitExpense = async () => {
    try {
      await ApiService.createExpense(
        userId,
        categoryId.value,
        month.value,
        expenseName.value,
        amount.value
      )
      await loadExpenses()
      clearForm()
    } catch (e) {
      errorMessage.value = '登録に失敗しました。'
      console.error(e)
    }
  }
  
  // 経費を更新
  const updateExpense = async () => {
    if (!selectedExpenseId.value) return
    try {
      await ApiService.updateExpense(
        selectedExpenseId.value,
        categoryId.value,
        month.value,
        expenseName.value,
        amount.value
      )
      await loadExpenses()
      clearForm()
    } catch (e) {
      errorMessage.value = '更新に失敗しました。'
      console.error(e)
    }
  }
  
  // 経費を削除
  const deleteExpense = async () => {
    if (!selectedExpenseId.value) return
    try {
      await ApiService.deleteExpense(selectedExpenseId.value)
      await loadExpenses()
      clearForm()
    } catch (e) {
      errorMessage.value = '削除に失敗しました。'
      console.error(e)
    }
  }
  
  // 編集モードに入る
  const selectExpense = (expense: any) => {
    selectedExpenseId.value = expense.id
    categoryId.value = expense.categoryId
    month.value = expense.month
    expenseName.value = expense.expenseName
    amount.value = expense.amount
  }
  
  // 入力フォームをクリア
  const clearForm = () => {
    selectedExpenseId.value = ''
    categoryId.value = ''
    month.value = ''
    expenseName.value = ''
    amount.value = ''
    errorMessage.value = ''
  }
  
  onMounted(() => {
    loadExpenses()
  })
  </script>
  
  <style scoped>
  .q-page {
    max-width: 800px;
    margin: auto;
  }
  </style>
  