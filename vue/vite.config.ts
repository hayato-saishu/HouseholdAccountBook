import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { quasar, transformAssetUrls } from "@quasar/vite-plugin";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue({
      template: { transformAssetUrls },
    }),

    // @quasar/plugin-vite options list:
    // https://github.com/quasarframework/quasar/blob/dev/vite-plugin/index.d.ts
    quasar({
      sassVariables: "./src/quasar-variables.sass",
    })
  ],
  server: {
    port: 5173,
    strictPort: true
  },
  resolve: {
    alias: [
      {
        find: 'src/quasar-variables.sass',
        replacement: './src/quasar-variables.sass',
      },
    ]
  }
});
