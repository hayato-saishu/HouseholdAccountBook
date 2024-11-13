<template>
  <q-layout view="hHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title>会員登録</q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <q-page>
        <q-form @submit.prevent="submitForm" class="q-pa-md">
          <q-input
            v-model="email"
            label="メールアドレス"
            type="email"
            :rules="['email']"
            aria-required="true"
            autofocus
            outlined
          />
          <q-input
            v-model="password"
            label="パスワード"
            type="password"
            aria-required="true"
            outlined
          />
          <div class="q-mt-md">
            <q-btn label="登録" color="primary" type="submit" />
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
    const email = ref("");
    const password = ref("");

    const submitForm = () => {
      if (email.value && password.value) {
        console.log("会員登録情報:", {
          email: email.value,
          password: password.value,
        });
        const registration: registration = () => {
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

    return { email, password, submitForm };
  },
});
</script>

<style scoped>
.q-page {
  max-width: 400px;
  margin: auto;
}
</style>
