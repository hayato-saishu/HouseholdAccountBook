import { createMemoryHistory, createRouter } from 'vue-router'

import RegisterPage from '../pages/register/index.vue'
import LoginPage from '../pages/login/index.vue'
import TopPage from '../pages/top/index.vue'

const routes = [
  { path: '/', component: TopPage },
  { path: '/register', component: RegisterPage },
  { path: '/login', component: LoginPage },
]

const router = createRouter({
  history: createMemoryHistory(),
  routes,
});

export default router;