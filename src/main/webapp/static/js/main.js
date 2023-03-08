const addToCartButtons = document.querySelectorAll(".buttonAddToCart");
const inputBoxes = document.querySelectorAll("#input-box");
console.dir(inputBoxes);


addToCartButtons.forEach((addButton) => addButton.addEventListener('click', (event) => {
        console.log(event);
        const url = `/shopping-cart?id=${event.target.dataset.productId}`;
        // fetchProduct(url);
        console.log(fetchProduct(url));
    })
)

inputBoxes.forEach((inputBox) => inputBox.addEventListener("keypress", (event) => {
        if (event.key === "Enter") {

            console.log(event);
            const url = `/shopping-cart?id=${inputBox.attributes['data-product-id'].value}`;
            console.log(url);

            fetchProduct(url);
        }
    })
)

async function fetchProduct(url) {
    const response = await fetch(url);
    return response.json();
}


function myFunctionSup() {
    document.getElementById("myDropdownSup").classList.toggle("show");
}

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        let dropdowns = document.getElementsByClassName("dropdown-content");
        let i;
        for (i = 0; i < dropdowns.length; i++) {
            let openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

const btn = document.getElementById('checkout-button');

btn.addEventListener('click', () => {
    const form = document.querySelector('div > form');
    const showDiv = document.getElementById("containerForm");

    if (showDiv.style.display === 'none') {
        showDiv.style.display = 'block';
    } else {
        showDiv.style.display = 'none';
    }

    if (form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
});
