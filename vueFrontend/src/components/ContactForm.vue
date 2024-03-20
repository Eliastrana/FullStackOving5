<template>
  <div class="contact-form">
    <h1>Contact Form</h1>
    <form @submit.prevent="submitForm">
      <div class="form-control" :class="{ 'error': !validations.name }">
        <label for="name">Name</label>
        <input type="text" id="name" v-model="contact.name" placeholder="Enter your name" @input="validateForm" />
      </div>
      <div class="form-control" :class="{ 'error': !validations.email }">
        <label for="email">Email</label>
        <input type="text" id="email" v-model="contact.email" placeholder="Enter your email" @input="validateForm" />
      </div>
      <div class="form-control" :class="{ 'error': !validations.message }">
        <label for="message">Message</label>
        <textarea id="message" v-model="formData.message" rows="5" placeholder="Enter your message" @input="validateForm" @keyup.enter="clickSendButton" />
      </div>
      <button ref="submit" id="send-button" :disabled="!isFormValid">Send</button>
    </form>
    <contact-confirmation v-if="formData.formSubmitted" :message="confirmationMessage" />
  </div>
</template>



<script>
import ContactConfirmation from './ContactConfirmation.vue';
import { reactive, ref, watch } from "vue";
import { useStore } from "vuex";
import { computed } from "vue";


export default {
  components: {
    ContactConfirmation,
  },

  setup() {
    const store = useStore();
    const contact = reactive({
      name: store.state.contact.name,
      email: store.state.contact.email,
    });

    const formData = reactive({
      message: '',
      formSubmitted: false,
    });

    const validations = reactive({
      name: false,
      email: false,
      message: false,
    });



    const confirmationMessage = ref('The form has been submitted successfully!');

    // Watches to commit changes to the store
    watch(() => contact.name, (newName) => {
      store.commit('updateName', newName);
    });

    watch(() => contact.email, (newEmail) => {
      store.commit('updateEmail', newEmail);
    });

    const isFormValid = computed(() => {
      const nameValid = contact.name.trim().length > 0;
      const emailValid = validateEmail(contact.email);
      const messageValid = formData.message.trim().length > 0;
      return nameValid && emailValid && messageValid;
    });

    function validateEmail(email) {
      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return regex.test(email);
    }



    function validateForm() {
      validations.name = !!contact.name;
      validations.email = validateEmail(contact.email);
      validations.message = !!formData.message;
      return validations.name && validations.email && validations.message;
    }

    async function submitForm() {
      console.log('submitForm called');

      if (!validateForm()) {
        return;
      }

      try {
        const submissionData = {
          name: contact.name,
          email: contact.email,
          message: formData.message,
        };
        const response = await fetch('http://localhost:3005/data', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(submissionData),
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        console.log('Form submitted successfully');
        formData.formSubmitted = true;

        // Reset form if necessary
        //contact.name = '';
        //contact.email = '';
        formData.message = '';
      } catch (error) {
        console.error('There was an error submitting the form:', error);
        formData.formSubmitted = false; // Optionally handle error state
      }
    }

    function resetForm() {
      contact.name = '';
      contact.email = '';
      formData.message = '';
      formData.formSubmitted = false;

      validateForm()
    }

    function clickSendButton() {
      if (isFormValid.value) {
        // Trigger a click event on the Send button
        document.getElementById('send-button').click();
      }
    }

    return {
      contact,
      formData,
      validations,
      confirmationMessage,
      submitForm,
      resetForm,
      isFormValid,
      validateForm,
      clickSendButton,
    };
  },
};
</script>



<style scoped>

.form-control.error input, .form-control.error textarea {
  border: 2px solid #ff6b6b; /* Brighter red for error indication */
}

.form-control.error label {
  color: #ff6b6b;
}

.form-control.error label::after {
  content: ' *';
  color: #ff6b6b;
}

.contact-form {
  width: 300px;
  margin: 0 auto;
  padding: 20px;
  background-color: #ffffff; /* White background for a cleaner look */
  border: 1px solid #ddd; /* Subtle border */
  border-radius: 10px; /* Less pronounced border-radius */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Light shadow for depth */
}

.contact-form h1 {
  margin-bottom: 20px;
  color: #333; /* Darker color for better readability */
  font-size: 24px; /* Slightly larger font size */
}

.contact-form form {
  display: flex;
  flex-direction: column;
}

.contact-form form .form-control {
  display: flex;
  justify-content: center; /* Centers horizontally */
  align-items: center; /* Centers vertically */
  flex-direction: column;
  margin-bottom: 20px;
}

.contact-form form .form-control label {
  margin-bottom: 5px;
  color: #333; /* Consistent text color */
  font-weight: bold; /* Bold labels */
}

.contact-form form .form-control input,
.contact-form form .form-control textarea {
  width: 90%;
  padding: 10px;
  font-size: 16px;
  font-family: Futura, sans-serif;
  border: 1px solid #ccc; /* Subtle border */
  border-radius: 5px; /* Less pronounced border-radius */
  background-color: #f8f8f8; /* Light grey background for input fields */
  color: #333; /* Consistent text color */
}

.contact-form form .form-control input:focus,
.contact-form form .form-control textarea:focus {
  border-color: #84e188; /* Highlight color on focus */
  box-shadow: 0 0 3px #84e188; /* Subtle glow effect on focus */
}

#send-button {
  margin-top: 20px;
  border-radius: 5px; /* More subtle border-radius */
  padding: 10px 20px; /* Adjust padding for better text display */
  font-size: 16px; /* Set a standard font size */
  font-weight: bold; /* Make text bold */
  color: white; /* Light text color for better contrast */
  background-color: #84e188; /* A more vibrant color */
  border: none; /* Remove border for a flat design */
  cursor: pointer; /* Change cursor to indicate button */
  transition: background-color 0.3s, box-shadow 0.3s; /* Smooth transition for hover and focus */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
}

#send-button:hover, #send-button:focus {
  background-color: #45a049; /* Slightly darker shade for hover and focus */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* Larger shadow for hover and focus */
}

#send-button:disabled {
  background-color: #ccc; /* Disabled state color */
  cursor: default; /* Default cursor for disabled state */
}


.error-asterisk {
  color: #ff6b6b; /* Red color matching the error indication */
}


</style>
