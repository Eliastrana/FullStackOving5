// in cypress/integration/form_validation.spec.js
describe('Contact Form Validation', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8080/AboutPage'); // Adjust the URL to your local dev server
  });

  it('should disable the submit button if the form is invalid', () => {
    cy.get('#name').clear();
    cy.get('#email').clear();
    cy.get('#message').clear();

    cy.get('#send-button').should('be.disabled');
  });

  it('should enable the submit button if the form is valid', () => {
    cy.get('#name').type('John Doe');
    cy.get('#email').type('john@example.com');
    cy.get('#message').type('Hello, this is a test message.');

    cy.get('#send-button').should('not.be.disabled');
  });
});
