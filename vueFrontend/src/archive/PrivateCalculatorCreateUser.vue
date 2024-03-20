<!--// File: PrivateCalculatorCreateUser.vue-->

<!--<template>-->
<!--  <div class="contact-form">-->
<!--    <h1>{{ mode === 'createUser' ? 'Create User' : 'Contact Form' }}</h1>-->
<!--    <form @submit.prevent="submitForm">-->

<!--      &lt;!&ndash; Username field, visible only in create user mode &ndash;&gt;-->
<!--      <div v-if="mode === 'createUser'" class="form-control" :class="{ 'error': !validations.username }">-->
<!--        <label for="username">Username</label>-->
<!--        <input type="text" id="username" v-model="contact.username" placeholder="Enter your username" @input="validateForm"/>-->
<!--      </div>-->


<!--      <div class="form-control" :class="{ 'error': !validations.password }">-->
<!--        <label for="password">Password</label>-->
<!--        <input type="password" id="password" v-model="contact.password" placeholder="Enter your password" @input="validateForm"/>-->
<!--      </div>-->

<!--      &lt;!&ndash; Password confirmation field, visible only in create user mode &ndash;&gt;-->
<!--      <div v-if="mode === 'createUser'" class="form-control" :class="{ 'error': !validations.passwordConfirm }">-->
<!--        <label for="passwordConfirm">Confirm Password</label>-->
<!--        <input type="password" id="passwordConfirm" v-model="contact.passwordConfirm" placeholder="Confirm your password" @input="validateForm"/>-->
<!--      </div>-->

<!--      <button ref="submit" id="send-button" :disabled="!isFormValid">{{ mode === 'createUser' ? 'Create Account' : 'Send' }}</button>-->

<!--    </form>-->
<!--  </div>-->
<!--</template>-->


<!--<script>-->
<!--import UserService from '@/services/UserService'; // Adjust the path based on your file structure-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      user: {-->
<!--        username: '',-->
<!--        email: '',-->
<!--        password: ''-->
<!--      },-->
<!--      // Additional component data-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    async signUp() {-->
<!--      try {-->
<!--        const response = await UserService.postNewUser(this.user);-->
<!--        console.log('Sign up successful', response.data);-->
<!--        // Handle successful sign-up, e.g., redirect or show a success message-->
<!--      } catch (error) {-->
<!--        console.error('Sign up failed', error.response.data);-->
<!--        // Handle error, e.g., show an error message-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->



<!--<style scoped>-->

<!--.form-control.error input, .form-control.error textarea {-->
<!--  border: 2px solid #ff6b6b; /* Brighter red for error indication */-->
<!--}-->

<!--.form-control.error label {-->
<!--  color: #ff6b6b;-->
<!--}-->

<!--.form-control.error label::after {-->
<!--  content: ' *';-->
<!--  color: #ff6b6b;-->
<!--}-->

<!--.contact-form {-->
<!--  width: 300px;-->
<!--  margin: 0 auto;-->
<!--  padding: 20px;-->
<!--  background-color: #ffffff; /* White background for a cleaner look */-->
<!--  border: 1px solid #ddd; /* Subtle border */-->
<!--  border-radius: 10px; /* Less pronounced border-radius */-->
<!--  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Light shadow for depth */-->
<!--}-->

<!--.contact-form h1 {-->
<!--  margin-bottom: 20px;-->
<!--  color: #333; /* Darker color for better readability */-->
<!--  font-size: 24px; /* Slightly larger font size */-->
<!--}-->

<!--.contact-form form {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.contact-form form .form-control {-->
<!--  display: flex;-->
<!--  justify-content: center; /* Centers horizontally */-->
<!--  align-items: center; /* Centers vertically */-->
<!--  flex-direction: column;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.contact-form form .form-control label {-->
<!--  margin-bottom: 5px;-->
<!--  color: #333; /* Consistent text color */-->
<!--  font-weight: bold; /* Bold labels */-->
<!--}-->

<!--.contact-form form .form-control input,-->
<!--.contact-form form .form-control textarea {-->
<!--  width: 90%;-->
<!--  padding: 10px;-->
<!--  font-size: 16px;-->
<!--  font-family: Futura, sans-serif;-->
<!--  border: 1px solid #ccc; /* Subtle border */-->
<!--  border-radius: 5px; /* Less pronounced border-radius */-->
<!--  background-color: #f8f8f8; /* Light grey background for input fields */-->
<!--  color: #333; /* Consistent text color */-->
<!--}-->

<!--.contact-form form .form-control input:focus,-->
<!--.contact-form form .form-control textarea:focus {-->
<!--  border-color: #84e188; /* Highlight color on focus */-->
<!--  box-shadow: 0 0 3px #84e188; /* Subtle glow effect on focus */-->
<!--}-->

<!--#send-button {-->
<!--  margin-top: 20px;-->
<!--  border-radius: 5px; /* More subtle border-radius */-->
<!--  padding: 10px 20px; /* Adjust padding for better text display */-->
<!--  font-size: 16px; /* Set a standard font size */-->
<!--  font-weight: bold; /* Make text bold */-->
<!--  color: white; /* Light text color for better contrast */-->
<!--  background-color: #84e188; /* A more vibrant color */-->
<!--  border: none; /* Remove border for a flat design */-->
<!--  cursor: pointer; /* Change cursor to indicate button */-->
<!--  transition: background-color 0.3s, box-shadow 0.3s; /* Smooth transition for hover and focus */-->
<!--  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */-->
<!--}-->

<!--#send-button:hover, #send-button:focus {-->
<!--  background-color: #45a049; /* Slightly darker shade for hover and focus */-->
<!--  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* Larger shadow for hover and focus */-->
<!--}-->

<!--#send-button:disabled {-->
<!--  background-color: #ccc; /* Disabled state color */-->
<!--  cursor: default; /* Default cursor for disabled state */-->
<!--}-->


<!--.error-asterisk {-->
<!--  color: #ff6b6b; /* Red color matching the error indication */-->
<!--}-->


<!--</style>-->

