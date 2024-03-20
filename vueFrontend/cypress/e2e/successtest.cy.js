// in cypress/integration/successful_submission.spec.js
describe('Successful Form Submission', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8080/AboutPage'); // Adjust the URL to your local dev server
  });

  it('should show success message upon form submission', () => {
    // Mocking the server response
    cy.intercept('POST', 'http://localhost:3005/data', {
      statusCode: 200,
      body: { message: 'Form submitted successfully' }
    }).as('formSubmission');

    // Fill the form and submit
    cy.get('#name').type('Jane Doe');
    cy.get('#email').type('jane@example.com');
    cy.get('#message').type('Hello, this is a test message.');
    cy.get('#send-button').click();

    // Check for successful submission behavior (e.g., form reset, success message, etc.)
    // This depends on how your application handles a successful submission.
    // For example:
    cy.get('#name').should('have.value', 'Jane Doe');
    cy.get('#email').should('have.value', 'jane@example.com');
    cy.get('#message').should('have.value', '');
    // Add more assertions as per your app's functionality

    cy.contains('The form has been submitted successfully!').should('be.visible');

  });
});
