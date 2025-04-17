<template>
  <q-layout view="hHh Lpr lFf">
    <q-page-container>
      <q-page>
        <h2>ログイン</h2>
        <q-form @submit.prevent="submitForm" class="q-pa-md">
          <q-banner v-if="errorMessage" class="q-mt-md bg-red-3 text-negative">
            {{ errorMessage }}
          </q-banner>
          <q-input
            class="q-mt-sm"
            v-model="email"
            label="メールアドレス"
            type="email"
            :rules="emailRules"
            aria-required="true"
            outlined
          />
          <q-input
            class="q-mt-sm"
            v-model="password"
            label="パスワード"
            type="password"
            :rules="passwordRules"
            aria-required="true"
            outlined
          />
          <div class="q-mt-sm">
            <q-btn
              type="submit"
              label="ログイン"
              style="width: 100%"
              icon-right="mdi-account-plus"
              color="primary"
              unelevated
              :loading="isLoading"
            />
          </div>
        </q-form>
        <router-link to="/register">会員登録がまだの方はこちらへ</router-link>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import ApiService from "../../service/ApiService";
import type LoginUser from "../../components/domains/loginUser";
import router from "../../router";

export default defineComponent({
  name: "Login",
  setup() {
    const email = ref("");
    const password = ref("");
    const errorMessage = ref("");
    const isLoading = ref(false);

    // 入力ルール
    const emailRules = [
      (val: string) => !!val || "メールアドレスは必須です。",
      (val: string) =>
        /.+@.+\..+/.test(val) || "正しいメールアドレスを入力してください。",
    ];
    const passwordRules = [
      (val: string) => !!val || "パスワードは必須です。",
      (val: string) =>
        val.length > 5 || "パスワードは6文字以上で入力してください。",
    ];

    // フォーム送信処理
    const submitForm = async () => {
      if (!email.value || !password.value) {
        console.error("入力が不正です");
        return;
      }

      const user: LoginUser = {
        email: email.value,
        password: password.value,
      };

      try {
        errorMessage.value = "";
        isLoading.value = true;
        const response = await ApiService.login(user);
        if (response.statusCode === 200) {
          console.log("ログイン成功:", response);
          // セッションにトークンを保存
          localStorage.setItem("token", response.accessToken);
          router.push("/expenseManager");
        } else if (response.statusCode === 404) {
          errorMessage.value = response.message;
        }
      } catch (error: any) {
        console.log("エラーメッセージ：", error.response);
        errorMessage.value = error.response?.data?.message || error.message;
      } finally {
        isLoading.value = false;
      }
    };

    return {
      email,
      password,
      errorMessage,
      isLoading,
      emailRules,
      passwordRules,
      submitForm,
    };
  },
});
</script>

<style scoped>
.q-page {
  max-width: 800px;
  margin: auto;
}
</style>
