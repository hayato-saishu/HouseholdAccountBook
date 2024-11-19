<template>
  <q-layout view="hHh Lpr lFf">
    <q-page-container>
      <q-page>
        <h2>会員登録</h2>
        <q-form @submit.prevent="submitForm" class="q-pa-md">
          <q-input
            class="q-mt-sm"
            v-model="name"
            dark
            label="名前"
            type="text"
            :rules="nameRules"
            aria-required="true"
            outlined
            error-message="名前は必須です。"
          />
          <q-input
            class="q-mt-sm"
            v-model="email"
            dark
            label="メールアドレス"
            type="email"
            :rules="emailRules"
            aria-required="true"
            outlined
          />
          <q-input
            class="q-mt-sm"
            v-model="password"
            dark
            label="パスワード"
            type="password"
            :rules="passwordRules"
            aria-required="true"
            outlined
          />
          <div class="q-mt-sm">
            <q-btn
              label="登録する"
              style="width: 100%"
              icon-right="mdi-account-plus"
              color="primary"
              type="submit"
            />
          </div>
        </q-form>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import ApiService from "../../service/ApiService";
import registration from "../../components/domains/registration";

export default defineComponent({
  name: "SignUp",
  setup() {
    const name = ref("");
    const email = ref("");
    const password = ref("");

    // 入力ルール
    const nameRules = [(val: string) => !!val];
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

    const submitForm = () => {
      if (!name.value && !email.value && !password.value) {
        console.error("入力が不正です");
        return;
      }

      const registration: registration = {
        name: name.value,
        email: email.value,
        password: password.value,
      };

      try {
        const response = ApiService.registerUser(registration);
        console.log("ログイン成功：", response);
      } catch (error) {
        console.error("ログイン失敗：", error);
      }
    };

    return {
      name,
      email,
      password,
      nameRules,
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
