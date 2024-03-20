

<!--<template>-->
<!--  <div>-->
<!--    <h1>Register User</h1>-->
<!--    <form @submit.prevent="registerUser">-->
<!--      <div>-->
<!--        <label for="username">Username:</label>-->
<!--        <input id="username" v-model="user.username" type="text" required>-->
<!--      </div>-->
<!--      <div>-->
<!--        <label for="password">Password:</label>-->
<!--        <input id="password" v-model="user.password" type="password" required>-->
<!--      </div>-->
<!--      <div>-->
<!--        <label for="email">Email:</label>-->
<!--        <input id="email" v-model="user.email" type="email" required>-->
<!--      </div>-->
<!--      &lt;!&ndash; Add more fields as necessary &ndash;&gt;-->
<!--      <button type="submit">Register</button>-->
<!--    </form>-->
<!--    <p v-if="message">{{ message }}</p>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      user: {-->
<!--        username: '',-->
<!--        password: '',-->
<!--        email: ''-->
<!--        // Add more fields as necessary-->
<!--      },-->
<!--      message: ''-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    async registerUser() {-->
<!--      try {-->
<!--        const response = await axios.post('http://localhost:8081/sign-up', this.user);-->
<!--        this.message = 'Registration successful!';-->
<!--        console.log(response.data);-->

<!--      } catch (error) {-->
<!--        if (error.response && error.response.data) {-->
<!--          // Handle response error from backend here-->
<!--          this.message = 'Error: ' + error.response.data.message;-->
<!--        } else {-->
<!--          this.message = 'Registration failed!';-->
<!--        }-->
<!--        console.error(error);-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->
