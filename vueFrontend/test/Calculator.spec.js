// Calculator.spec.js
import { describe, it, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import Calculator from '@/components/calculator/CalculatorApp.vue';


describe('Calculator', () => {
    it('renders correctly', () => {
        const wrapper = mount(Calculator);
        //expect(wrapper.html()).toContain('Calculator Component Markup'); // Replace with actual markup check
    });

    it('renders calculator display and buttons', () => {
        const wrapper = mount(Calculator);
        expect(wrapper.find('#calculator-display').exists()).toBe(true);
        expect(wrapper.findAll('.button-row').length).toBeGreaterThan(0);
        expect(wrapper.find('.button-equals').exists()).toBe(true);
        // Add more checks for specific buttons if needed
    });

    it('handles basic calculations correctly', async () => {
        const wrapper = mount(Calculator);

        // Simulate number and operation input
        await wrapper.find('.button-1').trigger('click');
        await wrapper.find('.button-add').trigger('click');
        await wrapper.find('.button-2').trigger('click');
        await wrapper.find('.button-equals').trigger('click');

        // Check display value
        expect(wrapper.vm.current).toBe('3');
    });

    it('clears the display', async () => {
        const wrapper = mount(Calculator);

        // Simulate some input
        await wrapper.find('.button-9').trigger('click');

        // Clear the display
        await wrapper.find('.button-clear').trigger('click');

        // Check if the display is cleared
        expect(wrapper.vm.current).toBe('');
    });


    it('prevents multiple decimal points in a number', async () => {
        const wrapper = mount(Calculator);

        // Simulate input that tries to add more than one decimal point in a number
        await wrapper.find('.button-1').trigger('click'); // Adds "1"
        await wrapper.find('.button-dot').trigger('click'); // Adds "."
        await wrapper.find('.button-2').trigger('click'); // Adds "2"
        await wrapper.find('.button-dot').trigger('click'); // Tries to add another ".", but should be prevented

        // Simulate continuing the number after attempting to add a second decimal point
        await wrapper.find('.button-3').trigger('click'); // Adds "3"

        // Check if the display value is correct and reflects prevention of the second decimal point
        expect(wrapper.vm.current).toBe('1.23'); // Expect no second decimal point to be added
    });


});
