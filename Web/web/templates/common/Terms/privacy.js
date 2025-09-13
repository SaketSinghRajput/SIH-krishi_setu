window.addEventListener('load', () => {
    const cards = document.querySelectorAll('.animate');
    cards.forEach((card, index) => {
        card.style.animationDelay = '${index * 0.2}s';
    });

    document.getElementById('acceptBtn').addEventListener('click', () => {
        alert('Thank you for accepting the terms!');
    });
});