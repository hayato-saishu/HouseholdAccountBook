import axios from 'axios'
import registration from '../components/domains/registration'
import loginUser from '../components/domains/loginUser'

export default class ApiService {
  static API_BASE_URL: string = 'http://localhost:8080/api'

  static getHeader() {
    return {
      'Content-Type': 'application/json',
    }
  }

  // ---------------- ユーザー認証 ----------------
  static async registerUser(registration: registration) {
    const response = await axios.post(`${this.API_BASE_URL}/auth/signUp`, registration)
    return response.data
  }

  static async login(loginUser: loginUser) {
    const response = await axios.post(`${this.API_BASE_URL}/auth/login`, loginUser)
    return response.data
  }

  // ---------------- 経費API ----------------

  // 今月の経費取得
  static async getExpenses(userId: string, month: string) {
    const response = await axios.get(
      `${this.API_BASE_URL}/expenses/by-user-id-and-month/${userId}`,
      { params: { month } }
    )
    return response.data
  }

  // 経費登録
  static async createExpense(userId: string, categoryId: string, month: string, expenseName: string, amount: string) {
    const response = await axios.post(
      `${this.API_BASE_URL}/expenses/create-expense/${userId}`,
      null,
      {
        params: {
          categoryId,
          month,
          expenseName,
          amount,
        },
      }
    )
    return response.data
  }

  // 経費更新
  static async updateExpense(expenseId: string, categoryId: string, month: string, expenseName: string, amount: string) {
    const response = await axios.put(
      `${this.API_BASE_URL}/expenses/update-expense/${expenseId}`,
      null,
      {
        params: {
          categoryId,
          month,
          expenseName,
          amount,
        },
      }
    )
    return response.data
  }

  // 経費削除
  static async deleteExpense(expenseId: string) {
    const response = await axios.delete(`${this.API_BASE_URL}/expenses/delete-expense/${expenseId}`)
    return response.data
  }
}