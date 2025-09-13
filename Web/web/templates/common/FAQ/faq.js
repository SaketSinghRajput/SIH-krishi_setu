document.addEventListener("DOMContentLoaded", function() {
    const faqQuestions = document.querySelectorAll('.faq-question-box');
    const faqAnswers = document.querySelectorAll('.faq-answer-box');

    faqQuestions.forEach((question, idx) => {
        question.addEventListener('click', function() {
            // Toggle current answer
            const isVisible = faqAnswers[idx].style.display === 'block';
            faqAnswers.forEach((answer, aidx) => {
                answer.style.display = 'none';
                faqQuestions[aidx].classList.remove('active');
            });
            if (!isVisible) {
                faqAnswers[idx].style.display = 'block';
                faqQuestions[idx].classList.add('active');
            }
        });
    });
});