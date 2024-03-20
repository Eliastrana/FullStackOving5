// File: PrivateCalculatorLogIn.vue

import { createRouter, createWebHistory } from 'vue-router';

// Import your component files

import HomePage from "@/components/HomePage.vue";
import AboutPage from "@/components/AboutPage.vue";
import CalculatorPage from "@/components/calculator/CalculatorPage.vue";
import GalleryView from "@/components/GalleryView.vue";
import PrivateCalculator from "@/components/PrivateCalculator.vue";
import PoopemojiView from "@/components/errorDisplay/PoopemojiView.vue";
import CreateUser from "@/authentication/CreateUser.vue";
import LoginUser from "@/authentication/LoginUser.vue";

// Define your routes
const routes = [
    { path: '/', component: HomePage },
    // { path: '/PrivateCalculator', component: PrivateCalculator },
    // { path: '/PrivateCalculatorCreateUser', component: PrivateCalculatorCreateUser },
    // { path: '/PrivateCalculatorLogIn', component: PrivateCalculatorLogIn },
    { path: '/AboutPage', component: AboutPage },
    { path: '/CalculatorPage', component: CalculatorPage },
    { path: '/GalleryView', component: GalleryView },
    { path: '/PoopemojiView', component: PoopemojiView },

    { path: '/CreateUser', component: CreateUser },
    { path: '/LoginUser', component: LoginUser },



    {
        path: '/PrivateCalculator',
        component: PrivateCalculator,
        beforeEnter: (to, from, next) => {
            // Check if the user is logged in
            const isAuthenticated = localStorage.getItem('userLoggedIn'); // This is just an example
            if (isAuthenticated) {
                next(); // Proceed to the route
            } else {
                next('/login'); // Redirect to login page
            }
        }
    }
];

// Create the router instance and pass the `routes` option
const router = createRouter({
    history: createWebHistory(),
    routes,

});


// In your router/index.js or wherever you've defined your routes


export default router;
