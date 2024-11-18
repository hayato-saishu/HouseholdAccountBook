import { createApp } from 'vue'

import App from './App.vue'
import router from './router'
import './style.css';

import { Quasar } from 'quasar'
import quasarLang from 'quasar/lang/ja'

// Import icon libraries
import '@quasar/extras/roboto-font/roboto-font.css'
import '@quasar/extras/material-icons/material-icons.css'
import '@quasar/extras/material-symbols-outlined/material-symbols-outlined.css'
import '@quasar/extras/mdi-v7/mdi-v7.css'

// Import Quasar css
import 'quasar/src/css/index.sass'

const myApp = createApp(App);

myApp.use(router);

myApp.use(Quasar, {
  plugins: {}, // import Quasar plugins and add here
  lang: quasarLang,
});

// Assumes you have a <div id="app"></div> in your index.html
myApp.mount('#app');
