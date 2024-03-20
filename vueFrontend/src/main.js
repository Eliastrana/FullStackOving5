import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store'; // This imports the store from './store/index.js'

const app = createApp(App);
app.use(store);
app.use(router);
app.mount('#app');


import axios from 'axios';

axios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
