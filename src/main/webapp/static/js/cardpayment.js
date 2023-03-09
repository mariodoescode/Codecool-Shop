const btnMasterCard = document.getElementById('master-card-payment');

btnMasterCard.addEventListener('click', () => {
    const formMasterCard = document.querySelector('div > form');

    if (formMasterCard.style.display === 'none') {
        formMasterCard.style.display = 'block';
        btnPaypal.disabled = true;
    } else {
        formMasterCard.style.display = 'none';
        btnPaypal.disabled = false;
    }
});

const btnPaypal = document.getElementById('paypal-card-payment');

btnPaypal.addEventListener('click', () => {
    const formPaypal = document.getElementById('paypal-form');

    if (formPaypal.style.display === 'none') {
        formPaypal.style.display = 'block';
        btnMasterCard.disabled = true;
    } else {
        formPaypal.style.display = 'none';
        btnMasterCard.disabled = false;
    }
});