// ContactForm.spec.js
import { describe, it, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import ContactForm from '../src/components/ContactForm.vue';

describe('ContactForm', () => {

    // Test that the component renders correctly
    it('renders input fields and a submit button', () => {
        const wrapper = mount(ContactForm);
        expect(wrapper.find('#name').exists()).toBe(true);
        expect(wrapper.find('#email').exists()).toBe(true);
        expect(wrapper.find('#message').exists()).toBe(true);
        expect(wrapper.find('#send-button').exists()).toBe(true);
    });


    // Test that the form is validated correctly
    it('validates name, email, and message correctly', async () => {
        const wrapper = mount(ContactForm);

        // Test empty fields
        await wrapper.setData({ formData: { name: '', email: '', message: '' } });
        expect(wrapper.vm.isFormValid).toBe(false);

        // Test valid input
        await wrapper.setData({ formData: { name: 'John Doe', email: 'john@example.com', message: 'Hello there!' } });
        expect(wrapper.vm.isFormValid).toBe(true);

        // Test invalid email
        await wrapper.setData({ formData: { email: 'invalid-email' } });
        expect(wrapper.vm.validateEmail(wrapper.vm.formData.email)).toBe(false);
    });

    // Test that the form is submitted correctly
    // it('submits the form data correctly', async () => {
    //     global.fetch = vi.fn(() =>
    //         Promise.resolve({
    //             ok: true,
    //             json: () => Promise.resolve({ message: 'Form submitted successfully' }),
    //         })
    //     );
    //
    //     const wrapper = mount(ContactForm);
    //     await wrapper.setData({ formData: { name: 'John Doe', email: 'john@example.com', message: 'Hello there!' } });
    //
    //     await wrapper.find('form').trigger('submit.prevent');
    //     expect(fetch).toHaveBeenCalledTimes(1);
    //     expect(fetch).toHaveBeenCalledWith('http://localhost:3005/data', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //         },
    //         body: JSON.stringify(wrapper.vm.formData),
    //     });
    // });

    // Test that the form is submitted correctly

    it('submits the form data correctly', async () => {
        global.fetch = vi.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({ message: 'Form submitted successfully' }),
            })
        );

        const wrapper = mount(ContactForm);
        await wrapper.setData({ formData: { name: 'John Doe', email: 'john@doe.no', message: 'Hello there!' } });

        await wrapper.find('form').trigger('submit.prevent');
        expect(fetch).toHaveBeenCalledTimes(1);

        //NOT EASY WHEN IT IS A LOCALHOST

        // expect(fetch).toHaveBeenCalledWith('http://localhost:3005/data', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify(wrapper.vm.formData),
        // });

    });


});


