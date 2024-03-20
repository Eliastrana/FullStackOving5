import axios from 'axios';
import SessionToken from "@/components/features/SessionToken";

// Create an Axios instance configured for your Spring Boot backend
const apiClient = axios.create({
    baseURL: 'http://localhost:8081/user', // Adjust if your backend URL is different
    withCredentials: false, // This is typically false for APIs, but set to true if your backend expects credentials
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export default {
    // Method for user registration
    postNewUser(user) {
        // Assuming the endpoint for user registration in your Spring Boot app is /user/sign-up
        return apiClient.post('/sign-up', user);
    },

    // Method for user login
    postSignIn(credentials) {
        // Assuming the endpoint for user login in your Spring Boot app is /user/login
        return apiClient.post('/login', credentials);
    },

    async getUserIdByUsername(username) {
        const token = await SessionToken();
        const response = await apiClient.get(`/users/by-username/${username}`, {
            headers: { Authorization: `Bearer ${token}` }
        });
        return response.data.id; // Assuming the ID is directly accessible in the response
    },
};
