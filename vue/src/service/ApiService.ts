import axios from 'axios'
import registration from '../components/domains/registration';
import loginUser from '../components/domains/loginUser';

export default class ApiService {
    
    static API_BASE_URL: string = "http://localhost:8080/api";

    static getHeader() {
        return {
            "Content-Type": "application/json"
        };
    }

    // ユーザー登録
    static async registerUser(registration: registration) {
        const response = await axios.post(`${this.API_BASE_URL}/auth/signUp`, registration);
        return response.data;
    }

    // ログイン
    static async login(loginUser: loginUser) {
        const response = await axios.post(`${this.API_BASE_URL}/auth/login`, loginUser);
        return response.data;
    }
}