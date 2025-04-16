import { createMemoryHistory, createRouter } from 'vue-router'

import RegisterPage from '../pages/register/index.vue'
import LoginPage from '../pages/login/index.vue'
import TopPage from '../pages/top/index.vue'
import ExpenseManager from '../pages/expenseManager/index.vue'

const routes = [
  { path: '/', redirect: '/login'},
  { path: '/register', component: RegisterPage },
  { path: '/login', component: LoginPage },
  { path: '/top', component: TopPage },
  { path: '/expenseManager', component: ExpenseManager },
]

const router = createRouter({
  history: createMemoryHistory(),
  routes,
});

export default router;