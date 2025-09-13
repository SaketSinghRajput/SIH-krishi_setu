
document.getElementById('contact-form').addEventListener('submit', function(event) {
  event.preventDefault();

  const name = document.getElementById('name').value;
  const phone = document.getElementById('phone').value;
  const message = document.getElementById('message').value;
  const formMessage = document.getElementById('form-message');

  if (name && phone && message) {
    formMessage.textContent = 'Thank you for your message! We will get back to you shortly.';
    formMessage.classList.add('text-green-600');
    formMessage.classList.remove('text-red-600');
                
                // You can add logic here to send the data to a server
    console.log('Form submitted:', { name, phone, message });
                
                // Reset the form
    this.reset();
    } else {
      formMessage.textContent = 'Please fill out all fields.';
      formMessage.classList.add('text-red-600');
      formMessage.classList.remove('text-green-600');
    }
});
