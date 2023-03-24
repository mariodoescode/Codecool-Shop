const addToCartButtons = document.querySelectorAll(".buttonAddToCart");
const inputBoxes = document.querySelectorAll("#input-box");
let itemNumberContainer = document.getElementById("itemNumber");


for(let link of addToCartButtons){
    link.addEventListener('click', addItemToCart);
}

async function addItemToCart(event){
    let prodName = event.target.dataset.name
    let prodPrice = event.target.dataset.price
    let description = event.target.dataset.desc
    let supplier = event.target.dataset.supplier
    let prod_id = event.target.dataset.prod_id
    fetch('/order?prod_name=' + prodName + '&prod_price=' + prodPrice + '&desc=' + description + '&supplier=' + supplier + '&prod_id=' + prod_id)
        .then(response => response.text())
        .then((response) => {
            addItemNumber(response)
        })
        .catch(err => console.log(err))

}

function addItemNumber(itemNumber) {
    itemNumberContainer.innerHTML = '';
    itemNumberContainer.innerHTML += `<p>${itemNumber}</p>`;

}

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


