import { createStore } from 'vuex';

export default createStore({
    state: {
        contact: {
            name: '',
            email: ''
        }
    },

    mutations: {
        // Mutation to update the name
        updateName(state, name) {
            state.contact.name = name;
        },

        // Mutation to update the email
        updateEmail(state, email) {
            state.contact.email = email;
        },

        // Keeping your existing mutation in case it's used elsewhere
        updateContact(state, contact) {
            state.contact.name = contact.name;
            state.contact.email = contact.email;
        },

        updateContactForm(state, formData) {
            state.contactForm = formData;
        },
    },
    actions: {},
    modules: {},
});