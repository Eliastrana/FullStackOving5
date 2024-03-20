import axios from 'axios'
import SessionToken from '../components/features/SessionToken.js'


const apiClient = axios.create({
    baseURL: 'http://localhost:8081/calculator',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    // async postExpression(expression) {
    //     return apiClient.post('/evaluate', expression, {
    //         headers: {
    //             Authorization: `Bearer ${await SessionToken()}`
    //         }
    //     })
    // },

    async evaluate(expression) {
        // Adjusted to use a GET request and pass expression as a query parameter
        return apiClient.get('/evaluate', {
            params: { expression }, // Pass expression as a query parameter
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        });
    },

// CalculationService.js
    async saveCalculation(calculationData) {
        // calculationData should be an object that includes userId, expression, and result
        return apiClient.post('/save', calculationData, {
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        });
    },

    async getLog(user) {
        return apiClient.post('/log/load', user, {
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        });
    },

    async getCalculationHistoryByUserId (userId) {
        return apiClient.get(`/calculations/history/${userId}`, {
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        });
    }



}
