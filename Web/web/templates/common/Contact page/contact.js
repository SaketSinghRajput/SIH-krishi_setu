document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('form');
  form.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = form.querySelector('input[placeholder="Name"]').value.trim();
    const phone = form.querySelector('input[placeholder="Phone Number"]').value.trim();
    const message = form.querySelector('textarea[placeholder="Message"]').value.trim();

    if (!name) {
      alert('Please enter your name.');
      return;
    }
    if (!phone) {
      alert('Please enter your phone number.');
      return;
    }
    if (!message) {
      alert('Please enter your message.');
      return;
    }

    alert('Thank you for contacting KrishiSetu! Your message has been sent.');
    form.reset();
  });
});