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
        const result = await axios.post(`${this.API_BASE_URL}/auth/register`, registration);
        return result.data;
    }

    // ログイン
    static async loginUser(loginUser: loginUser) {
        const result = await axios.post(`${this.API_BASE_URL}/auth/login`, loginUser);
        return result.data;
    }
}