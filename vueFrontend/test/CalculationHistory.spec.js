import { mount } from '@vue/test-utils';
import CalculatorHistory from '/src/components/calculator/CalculatorHistory.vue';

describe('CalculatorHistory', () => {
    it('renders the calculation history correctly', () => {
        // Define a sample history array
        const history = ['Calculation 1', 'Calculation 2', 'Calculation 3'];

        // Mount the CalculatorHistory component with the history prop
        const wrapper = mount(CalculatorHistory, {
            props: {
                history,
            },
        });

        // Check if the component exists
        expect(wrapper.exists()).toBe(true);

        // Check if the history records are rendered
        const historyItems = wrapper.findAll('li');
        expect(historyItems.length).toBe(history.length);

        // Check if each history record text matches the expected values
        historyItems.forEach((item, index) => {
            expect(item.text()).toBe(history[index]);
        });
    });
});
