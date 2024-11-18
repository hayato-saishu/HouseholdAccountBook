<template>
  <q-layout view="hHh Lpr lFf">
    <q-page-container>
      <q-page>
        <q-form @submit.prevent="submitForm" class="q-pa-md">
          <q-input
            class="q-mt-sm"
            v-model="name"
            dark
            label="名前"
            type="text"
            :rules="[(val) => !!val]"
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
            :rules="[
              (val) => !!val || 'メールアドレスは必須です。',
              (val) =>
                /.+@.+\..+/.test(val) ||
                '正しいメールアドレスを入力してください。',
            ]"
            aria-required="true"
            outlined
          />
          <q-input
            class="q-mt-sm"
            v-model="password"
            dark
            label="パスワード"
            type="password"
            :rules="[
              (val) => !!val || 'パスワードは必須です。',
              (val) =>
                val.length > 5 || 'パスワードは6文字以上で入力してください。',
            ]"
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
              to="/login"
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
  name: "App",
  setup() {
    const name = ref("");
    const email = ref("");
    const password = ref("");

    const submitForm = () => {
      if (name.value && email.value && password.value) {
        console.log("会員登録情報:", {
          name: name.value,
          email: email.value,
          password: password.value,
        });
        const registration: registration = () => {
          name;
          email;
          password;
        };
        // ここでAPI呼び出しなどを行うことができます
        const response = ApiService.registerUser(registration);
        console.log(response);
      } else {
        console.error("入力が不正です");
      }
    };

    return { name, email, password, submitForm };
  },
});
</script>

<style scoped>
.q-page {
  max-width: 800px;
  margin: auto;
}
</style>
